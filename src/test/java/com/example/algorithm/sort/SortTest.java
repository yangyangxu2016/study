package com.example.algorithm.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Slf4j
class SortTest {


    Sort sort = new Sort();

    int[] a = null;
    int[] b = new int[]{1, 2, 3, 4, 5, 6};

    @BeforeEach
    void setUp() {
        a = new int[]{4, 5, 6, 1, 2, 3};
        log.debug("排序前：{}", Arrays.toString(a));

    }

    @AfterEach
    void tearDown() {
        log.debug("排序后：{}", Arrays.toString(a));
    }

    @Test
    void bubbleSort() {
        sort.bubbleSort(a);
        Assert.assertArrayEquals(b, a);
    }

    @Test
    void insertSort() {
        sort.insertSort(a);
        Assert.assertArrayEquals(b, a);
    }


}