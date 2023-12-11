package com.camping.camping.repositories;

import com.camping.camping.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_IdOrderByIdDesc(Long id);

    List<Order> findAllByOrderByIdDesc();

}
