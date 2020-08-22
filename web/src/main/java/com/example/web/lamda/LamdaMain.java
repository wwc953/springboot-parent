package com.example.web.lamda;

/**
 * @Description: TODO Java8新语法  方法引用 ::
 * @author: wangwc
 * @date: 2020/8/22 10:51
 */
public class LamdaMain {
    public static void main(String[] args) {
        /**
         * 方法引用使用到的操作符“::”，这个操作符把方法引用分成两边，左边是类名或者某个对象的引用，右边是方法名。引用方法有下面几种方式：
         *
         * （1）对象引用::实例方法名
         *
         * （2）类名::静态方法名
         *
         * （3）类名::实例方法名
         *
         * （4）类名::new
         *
         * （5）类型[]::new
         */


    }


    /**
     * 类名::静态方法名
     */
    public static void test2(){
        /**
         * String::valueOf，等价于 Lambda：s -> String.valueOf(s)
         *
         * Math::pow 等价于lambda表达式 (x, y) -> Math.pow(x, y);
         *
         * Person::compareByAge 就是一个静态方法引用，也是前面我们举得例子。
         *
         * Function<Long, Long> f = Math::abs;
         *
         * Long result = f.apply(-3L);123
         *
         * Math是一个类而abs为该类的静态方法。Function中的唯一抽象方法apply方法参数列表与abs方法的参数列表相同，都是接收一个Long类型参数。
         */
     }

}
