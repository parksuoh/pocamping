package com.camping.camping.repositories;

import com.camping.camping.domains.Cart;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CartRepositoryTest {


    @Autowired
    private CartRepository cartRepository;


    @Test
    void findTop1ByUser_Id_test() {

//        Long id = 4L;
//
//        Cart top1ByUserId = cartRepository.findTop1ByUser_Id(id);
//
//        assertThat(top1ByUserId).isNotNull();
    }

}