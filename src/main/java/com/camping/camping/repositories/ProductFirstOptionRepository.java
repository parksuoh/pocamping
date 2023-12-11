package com.camping.camping.repositories;

import com.camping.camping.domains.Product;
import com.camping.camping.domains.ProductFirstOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductFirstOptionRepository extends JpaRepository<ProductFirstOption, Long>{

    Optional<ProductFirstOption> findById(Long id);

    List<ProductFirstOption> findByProduct_Id(Long productId);

}
