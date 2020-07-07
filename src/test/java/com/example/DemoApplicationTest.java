package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTest {

    public Logger log = LoggerFactory.getLogger(this.getClass());

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }
}