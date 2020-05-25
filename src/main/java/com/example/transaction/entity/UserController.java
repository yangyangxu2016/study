package com.example.transaction.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("wrong1")
    public int wrong1(@RequestParam("name") String name) {
        return userService.createUserWrong1(name);
    }

    @GetMapping("wrong2")
    public int wrong2(@RequestParam("name") String name) {
        return userService.createUserWrong2(name);
    }

    @GetMapping("wrong3")
    public int wrong3(@RequestParam("name") String name) {
        return userService.createUserWrong3(name);
    }

    public static void main(String[] args) {
        String dirPath = "E:/uinv_git/workspace/dcv/dcv-web/src/main/webapp/rsm/cli/read/GalileoResource";
        File dir = new File(dirPath);
        System.out.println(dir.isDirectory());
        if(!dir.isDirectory() && !dir.mkdirs()) {
            System.out.println("d");
        }
    }
}
