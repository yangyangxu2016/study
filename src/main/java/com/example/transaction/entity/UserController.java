//package com.example.transaction.entity;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//
//@Slf4j
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("wrong1")
//    public int wrong1(@RequestParam("name") String name) {
//        return userService.createUserWrong1(name);
//    }
//
//    @GetMapping("wrong2")
//    public int wrong2(@RequestParam("name") String name) {
//        return userService.createUserWrong2(name);
//    }
//
//
//    @GetMapping("right2")
//    public int right2(@RequestParam("name") String name) {
//        try {
//            userService.createUserPublic(new UserEntity(name));
//        } catch (Exception e) {
//            log.error("create user fail", e.getMessage());
//        }
//        return userService.getUserCount(name);
//    }
//
//    public static void main(String[] args) {
//        String dirPath = "E:/uinv_git/workspace/dcv/dcv-web/src/main/webapp/rsm/cli/read/GalileoResource";
//        File dir = new File(dirPath);
//        System.out.println(dir.isDirectory());
//        if (!dir.isDirectory() && !dir.mkdirs()) {
//            System.out.println("d");
//        }
//    }
//}
