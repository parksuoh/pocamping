package com.camping.camping.repositories;

import com.camping.camping.domains.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>  {

    Cart findTop1ByUser_Id(Long id);

}
