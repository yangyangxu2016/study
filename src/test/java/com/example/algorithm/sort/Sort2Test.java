package com.example.algorithm.sort;

import com.example.DemoApplicationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class Sort2Test extends DemoApplicationTest {

//    @Autowired
//    Sort2 sort2;

    Sort2 sort2 = new Sort2();

    int[] a = null;
    int[] b = new int[]{1, 2, 3, 4, 5, 6};

    @Before
    public void setUp() {
        a = new int[]{4, 5, 6, 1, 2, 3};
        log.info("排序前：{}", Arrays.toString(a));

    }

    @After
    public void tearDown() {
        log.info("排序后：{}", Arrays.toString(a));
    }


    @Test
    public void quickSort() {
        sort2.quickSort(a);
    }
}