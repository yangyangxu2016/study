package com.example.algorithm.sort;

import com.example.DemoApplicationTest;
import org.junit.After;
import org.junit.Assert;
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
        Assert.assertArrayEquals(b,a);
    }

    @Test
    public void bubbleSort() {
        sort2.bubbleSort(a);
        Assert.assertArrayEquals(b,a);
    }

    @Test
    public void selectSort() {
        sort2.selectSort(a);
        Assert.assertArrayEquals(b,a);
    }

    @Test
    public void insertSort() {
        sort2.insertSort(a);
        Assert.assertArrayEquals(b,a);
    }
}