package com.example.algorithm.sort;

import com.example.DemoApplicationTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void quickNull() {
        List<Ext> extList = new ArrayList<>();
        List<String> ids = extList.stream().map(ext -> {
            return ext.getId();
        }).collect(Collectors.toList());

    }

    public class Ext {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        List<Ext> extList = null;

        for (Ext ext : extList) {


        }

//        List<String> ids = extList.stream().map(ext -> {
//            return ext.getId();
//        }).collect(Collectors.toList());
    }
}