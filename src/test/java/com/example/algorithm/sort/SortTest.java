package com.example.algorithm.sort;

import com.example.DemoApplicationTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SortTest extends DemoApplicationTest {

//    @Autowired
//    Sort sort;

    Sort sort = new Sort();

    int[] a = null;
    int[] b = new int[]{1, 2, 3, 4, 5, 6};

    @Before
    public void setUp() {
        a = new int[]{4, 5, 6, 1, 2, 3};
        log.debug("排序前：{}", Arrays.toString(a));

    }

    @After
    public void tearDown() {
        log.debug("排序后：{}", Arrays.toString(a));
    }

    @Test
    public void bubbleSort() {
        sort.bubbleSort(a);
        Assert.assertArrayEquals(b, a);
    }

    @Test
    public void insertSort() {
        sort.insertSort(a);
        Assert.assertArrayEquals(b, a);
    }


    @Test
    public void selectSort() {
        sort.selectSort(a);
        Assert.assertArrayEquals(b, a);
    }

    @Test
    public void mergeSort() {
        sort.mergeSort(a);
        Assert.assertArrayEquals(b, a);
    }

    @Test
    public void quickSort() {
        sort.quickSort(a);
        Assert.assertArrayEquals(b, a);
    }
}