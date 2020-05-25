package com.example.transaction.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * 一个公共方法供controller调用
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
     * 一个公共方法供controller调用
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
    @Transactional
    public void createUserPublic(UserEntity userEntity) {
        userRepository.save(userEntity);
        if (userEntity.getName().contains("test")) {
            throw new RuntimeException("invalid username");
        }
    }

    /**
     * 一个公共方法供controller调用
     *
     * @param name
     * @return
     */
    public int createUserWrong3(String name) {
        try {
            userService.createUserPrivate(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed becauser {} ", ex.getMessage());
        }
        return userRepository.findByName(name).size();
    }

}
