package com.example.web.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: TODO
 * @author: wangwc
 * @date: 2020/8/22 9:17
 */
public class LamdaTest {

    /**
     * lambda的基本写法
     * （参数）->{}
     * 一个lambda expression由三部分组成：
     * - 参数：(T args)是这个lambda expression的参数部分，包括参数类型和参数名，可为空，小括号不可缺少
     * - 箭头：->，不可缺少
     * - 代码块：就是用”{}”包含着的代码。当代码块内代码只有一行时，花括号可以省略，且代码块内分号省略
     *
     * @param args
     */
    public static void main(String[] args) {

        testflatMap4();
//        testMap1();
//        testMap2();
//        testMap3();
//        lambdaTestList();
    }

    public static void testflatMap4() {
        /*
        flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，
        最终 output 的新 Stream 里面已经没有 List 了，都是直接的数字
        */
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(2),
                Arrays.asList(1, 3),
                Arrays.asList(5, 4, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(v -> System.out.println(v));
    }

    public static void testMap1() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println(squareNums);
    }

    public static void testMap2() {
        //它的作用就是把 input Stream 的每一个元素，映射成 output Stream 的另外一个元素
        List<String> wordList = Arrays.asList("a", "b");
        List<String> output = wordList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(output);
    }

    public static void testMap3() {
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
//                int i = 1/0;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hahah：" + System.currentTimeMillis());
        }).start();
    }

    public static void lambdaTestList() {
        List<String> list = Arrays.asList("1", "2", "3", "4");
        //正常使用迭代
        for (String play : list) {
            System.out.println(play);
        }
        //lamda
        list.forEach((play) -> System.out.println("lamda" + play));
    }

}
