package com.example.springcloud.feign;

import feign.Feign;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public class TestFeignClient {

    interface GitHub {
        @RequestLine("GET /user/{username}/{password}")
        List<User> send(@Param("username") String username, @Param("password") String password);

        @RequestLine("GET /queryVariableList")
        List<SysVariable> queryVariableList( );
    }

    public static class User {
        String username;
        String password;

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }


    public static void main(String[] args) {
        GitHub gitHub = Feign.builder().target(GitHub.class, "http://192.168.1.49/tarsier-dcv/dcv/sys/variable/");

        List<SysVariable> xuyy = gitHub.queryVariableList( );
        xuyy.toString();
//        List<User> xuyy = gitHub.send("xuyy", "123");
//        xuyy.forEach(x->{
//            System.out.println(x.toString());
//        });


    }
}
