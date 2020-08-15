package com.example.web;

import com.example.web.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FirstApplication.class)
@AutoConfigureMockMvc
class FirstApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() throws Exception {

        //MockMvcRequestBuilders 构建request请求
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hello")
                //请求参数的类型
                .accept(MediaType.APPLICATION_JSON)
                //请求的参数（可多个）
                .param("sessionid", "ZlbpLxXw");

        //perform：执行一个RequestBuilder请求
        //andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确
        //andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台
        //andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理
        ResultActions resultActions = mvc.perform(requestBuilder)
                //断言：验证
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(content().string("hello hhhh"))
                .andDo(MockMvcResultHandlers.print());

//        MvcResult mvcResult = resultActions.andReturn();
//        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println("return:"+content);
    }

    public void test2() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees<li>.employeeId").isNotEmpty());
    }

    public void test3() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/employees/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(1));
    }

    @Test
    public void testPost() throws Exception {
        User user = new User(1000L, "wangsan", "China");
        String asString = objectMapper.writeValueAsString(user);

        mvc.perform(MockMvcRequestBuilders
                .post("/getUser")
//                .param("user",asString)
                .content(asString)
                .accept(MediaType.APPLICATION_JSON)//属于请求头,客户端希望接受的数据类型
                .contentType(MediaType.APPLICATION_JSON))//属于实体头,(客户端|服务器）发送的实体数据的数据类型
                //.andReturn().getResponse().setCharacterEncoding("UTF-8")
                .andExpect(status().isOk())
                //$: 返回结果
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(100L));


//        {
//            "status":200,
//                "data":{"id":"2","name":"测试"}
//        }
        //$.data.name
    }

    @Test
    public void testPost2() throws Exception {
        User user = new User(1000L, "wangsan", "China");
        String asString = objectMapper.writeValueAsString(user);

        mvc.perform(MockMvcRequestBuilders
                .post("/getUser2")
                .param("haha","6666")
                .content(asString)
                .accept(MediaType.APPLICATION_JSON)//属于请求头,客户端希望接受的数据类型
                .contentType(MediaType.APPLICATION_JSON))//属于实体头,(客户端|服务器）发送的实体数据的数据类型
                //.andReturn().getResponse().setCharacterEncoding("UTF-8")
                .andExpect(status().isOk())
                //$: 返回结果
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(100L));

    }

    @Test
    public void testmap() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .post("/getMapper")
                .param("name","我是hahah")
                .accept(MediaType.APPLICATION_JSON)//属于请求头,客户端希望接受的数据类型
                .contentType(MediaType.APPLICATION_JSON))//属于实体头,(客户端|服务器）发送的实体数据的数据类型
                //.andReturn().getResponse().setCharacterEncoding("UTF-8")
                .andExpect(status().isOk())
                //$: 返回结果
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(100L));

    }

    @Test
    public void testmap2() throws Exception {

        String contentAsString = mvc.perform(MockMvcRequestBuilders
                .post("/getMapper")
                .param("name", "我是hahah")
                .accept(MediaType.APPLICATION_JSON)//属于请求头,客户端希望接受的数据类型
                .contentType(MediaType.APPLICATION_JSON))//属于实体头,(客户端|服务器）发送的实体数据的数据类型
                .andExpect(status().isOk())
                //$: 返回结果
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(100L))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println("======="+contentAsString);
//        assertThat()

    }



}
