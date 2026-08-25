package com.cael.cicd_demo.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloControllerTest {

    @Test
    void contextLoads() {
    	assertEquals(1, 2);
    }
}