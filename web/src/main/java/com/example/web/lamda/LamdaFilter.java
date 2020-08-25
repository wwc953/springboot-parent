package com.example.web.lamda;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Description: TODO  filter对原始 Stream 进行某项测试, 通过测试的元素被留下来生成一个新 Stream
 * @author: wangwc
 * @date: 2020/8/22 10:13
 */
public class LamdaFilter {

    public static void main(String[] args) {
        testFilter1();

    }

    /**
     * 留下偶数
     */
    public static void testFilter1() {
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n % 2 == 0).toArray(Integer[]::new);
        System.out.println(Arrays.toString(evens));
    }

}
