package com.example.web.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/common")
@RestController
//@Api(tags = "公共转发")
public class CommonController {

    @Autowired
    WebApplicationContext applicationContext;

    private static ObjectMapper objectMapper;

    static {
        DefaultSerializerProvider.Impl sp = new DefaultSerializerProvider.Impl();
        objectMapper = new ObjectMapper(null, sp, null);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    }

    @Value("${spring.application.name:}")
    String applicationName;

    @Value("${server.port}")
    String port;


    /**
     * 通过方法调用的方式，调用controller
     * @param ao
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws JsonProcessingException
     */
    //    @ApiOperation(value = "本地调用")
    @PostMapping(value = "/localCall", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object applyCreate(TransferStationAO ao) throws InvocationTargetException, IllegalAccessException, JsonProcessingException {
        String str = "";
        RequestMappingHandlerMapping mapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        String uri = "";
        if (!StringUtils.isEmpty(applicationName)) {
            uri = ao.getReqUrl().split(applicationName)[1];
        } else {
            uri = ao.getReqUrl().split(port)[1];
        }
        String reqData = ao.getReqData();
//        reqData = URLDecoder.decode(reqData);
        for (RequestMappingInfo info : handlerMethods.keySet()) {
            Set<String> urls = info.getPatternsCondition().getPatterns();
            for (String url : urls) {
                if (uri.equals(url)) {
                    HandlerMethod handlerMethod = handlerMethods.get(info);
                    Method method = handlerMethod.getMethod();
                    Object contr = applicationContext.getBean(handlerMethod.getBeanType());
                    List<Object> paramList = new ArrayList<>(method.getParameterCount());
                    if ("post".equals(ao.getMethodType().toLowerCase())) {
                        for (Class clazz : method.getParameterTypes()) {
                            Object param = JSON.parseObject(reqData, clazz);
                            paramList.add(param);
                        }
                    } else {
                        JSONObject obj = JSONObject.parseObject(reqData);
                        Parameter[] parameters = method.getParameters();
                        for (int i = 0; i < parameters.length; i++) {
                            paramList.add(obj.get(parameters[i].getName()));
                        }
                    }
                    Object result = method.invoke(contr, paramList.toArray());
                    str = objectMapper.writeValueAsString(result);
                    System.out.println("localhostCall出参:" + str);
                }
            }
        }
        return str;
    }


    public class TransferStationAO {
        private String token;
        private String reqUrl;
        private String reqData;
        private String headerData;
        private String methodType;//get,post

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getReqUrl() {
            return reqUrl;
        }

        public void setReqUrl(String reqUrl) {
            this.reqUrl = reqUrl;
        }

        public String getReqData() {
            return reqData;
        }

        public void setReqData(String reqData) {
            this.reqData = reqData;
        }

        public String getHeaderData() {
            return headerData;
        }

        public void setHeaderData(String headerData) {
            this.headerData = headerData;
        }

        public String getMethodType() {
            return methodType;
        }

        public void setMethodType(String methodType) {
            this.methodType = methodType;
        }
    }

}
