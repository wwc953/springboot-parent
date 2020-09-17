package com.example.web.yaml;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/9/17 15:47
 */
@Component
public class YamlLoad {
    private Object lock = new Object();
    private Map<String, List<FileInfo>> LOADERS = new ConcurrentHashMap();

    /**
     * 读取配置文件信息
     *
     * @param fileName
     * @return
     */
    public Map<String, List<FileInfo>> loadFile(String fileName) {
        Yaml yaml = new Yaml(new Constructor(FileInfo.class));
        String path = "spi/" + fileName + ".yml";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        Iterable<Object> ita = yaml.loadAll(in);

        List<FileInfo> arrayList = new ArrayList<>();
        ita.forEach(t -> {
            arrayList.add((FileInfo) t);
        });
        Map<String, List<FileInfo>> result = arrayList.stream().collect(Collectors.groupingBy(FileInfo::getName));
        return result;
    }

    /**
     * 获取配置文件缓存
     *
     * @param name
     * @param fName
     * @return
     */
    public List<FileInfo> getExByName(String name, String fName) {
        Objects.requireNonNull(name, "name is null");
        List<FileInfo> fileInfos = LOADERS.get(name);

        if (CollectionUtils.isEmpty(fileInfos)) {
            Objects.requireNonNull(fName, "fName is null");
            synchronized (lock) {
                Map<String, List<FileInfo>> fileMap = loadFile(fName);
                if (fileMap != null) {
                    LOADERS.putAll(fileMap);
                    fileInfos = LOADERS.get(name);
                }
            }
        }
        return fileInfos;
    }

}
