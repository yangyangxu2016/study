package com.example.transaction.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService self;

    /**
     * 1、spring 默认通过动态代理的方式实现 AOP,对目标方法进行增强，private 方法无法被代理到，
     * spring 自然也无法动态增强事务处理逻辑
     *
     * @param name
     * @return
     */
    public int createUserWrong1(String name) {
        try {
            this.createUserPrivate(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed becauser {} ", ex.getMessage());
        }
        return userRepository.findByName(name).size();
    }

    @Transactional
    private void createUserPrivate(UserEntity userEntity) {
        userRepository.save(userEntity);
        if (userEntity.getName().contains("test")) {
            throw new RuntimeException("invalid username");
        }
    }


    /**
     * 2、必须通过代理过的类从外部调用目标方法才能生效
     *
     * @param name
     * @return
     */
    public int createUserWrong2(String name) {
        try {
            this.createUserPublic(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed becauser {} ", ex.getMessage());
        }
        return userRepository.findByName(name).size();
    }


    /**
     * spring 通过 cglb 通过继承方式实现，private 在子类中不可见，自然无法进行事务增强
     * this 代表对象自己，Spring 不可能注入 this，所以通过this方法访问的必然不是代理
     *
     * @param name
     * @return
     */
    public int createUserRight(String name) {
        try {
            //this 代表对象自己，Spring 不可能注入 this，所以通过this方法访问的必然不是代理
//            this.createUserPublic(new UserEntity(name));
            //spring 中 CGLIB 通过继承方式实现，private 在子类中不可见，自然无法进行事务增强
            self.createUserPublic(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed becauser {} ", ex.getMessage());
        }
        return userRepository.findByName(name).size();
    }

    @Transactional
    public void createUserPublic(UserEntity userEntity) {
        userRepository.save(userEntity);
        if (userEntity.getName().contains("test")) {
            throw new RuntimeException("invalid username");
        }
    }

    public int getUserCount(String name) {
        List<UserEntity> userEntities = userRepository.findByName(name);
        if (ObjectUtils.isEmpty(userEntities)) {
            return 0;
        }

        return userEntities.size();
    }

    /**
     * 事务异常不回滚 1、异常必须抛出被捕获，或者手动设置回滚
     *
     * @param userEntity
     */
    @Transactional
    public void createUserPublic1(UserEntity userEntity) {
        try {
            userRepository.save(userEntity);
            throw new RuntimeException("error");
        } catch (Exception ex) {
            log.error("create user fail ", ex.getMessage());

            //手动设置回滚，否则事务不生效
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }


    /**
     * 事务异常不回滚 2、默认捕获 RuntimeException 和 Error ,
     * 如果捕获 CheckedException 需要通过注解设置 rollbackFor=Exception.class
     *
     * @param userEntity
     * @throws IOException
     */
//    @Transactional(rollbackOn = Exception.class)
    @Transactional
    public void createUserPublic2(UserEntity userEntity) throws IOException {
        userRepository.save(userEntity);
        ortherTask();
    }

    private void ortherTask() throws IOException {
        Files.readAllLines(Paths.get("file-not-exist"));
    }


}
