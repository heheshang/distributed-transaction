package com.distributed.transaction;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author ssk www.8win.com Inc.All rights reserved
 * @version v1.0
 * @date 2018-07-31-上午 9:36
 */
public class Test {


    private static IntStream fact(int n) {

        return IntStream.range(n,100);


    }

    public static void main(String[] args) {

        int[] ss = Arrays.stream(new int[] {20, 30, 40, 70})
                .flatMap(Test::fact)
                .distinct()
                .sorted()
                .toArray();

        System.out.println(Arrays.toString(ss));

    }

}
