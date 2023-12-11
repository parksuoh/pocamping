package com.camping.camping.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.camping.camping.domains.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import jakarta.transaction.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    void existsByNmaeTest() {

//        String userName = "user1";
//
//        boolean isUserExist = userRepository.existsByName(userName);
//
//        assertThat(isUserExist).isTrue();

    }

    @Test
    void existsByNmaeTest2() {

//        String userName = "user10";
//
//        boolean isUserExist = userRepository.existsByName(userName);
//
//        assertThat(isUserExist).isFalse();

    }

    @Test
    void findByNameTest() {
//        String userName = "user1";
//
//        User user = userRepository.findByName(userName).get();
//
//        assertThat(user).isNotNull();
    }


}