package com.example.stream;

import javax.xml.stream.XMLOutputFactory;
import java.util.Arrays;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.stream.Collectors;

/**
 * @author xuyangyang
 */
public class StudyToMap {

    public static void main(String[] args) {


        List<Integer> word1 = Arrays.asList(1, 2, 3);
        List<Integer> word2 = Arrays.asList( 4, 5);

        List<int[]> collect = word1.stream().
                flatMap(x -> word2.stream().map(j -> new int[]{x, j}))
                .collect(Collectors.toList());


        collect.forEach(x-> System.out.println(x));

//        我现在的心情很犹豫不决，不知道怎么解决这个问题，
//        首先主从同步，然后分库，根据业务分库，涉及到跨库的关联查询
//        跨库join比较复杂，可以通过数据库中间件屏蔽掉
//        单表 数据量大 可以水平拆分或者垂直拆分，商品表 订单表
//        怎么决定存储到哪个表，如何查询多个表，如何多dao层透明
//        水平拆分最重要的就是分片规则，分区表分区方式  范围分区  2018 2017
//        散列  列表分区  不是时间也不是数值
//        列表
    }
}
