package com.camping.camping.domains;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_first_option_id")
    private ProductFirstOption productFirstOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_second_option_id")
    private ProductSecondOption productSecondOption;

    @Column(name = "quantity")
    private Integer quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated;


    public CartItem() {}

    public CartItem(Cart cart, Product product, ProductFirstOption productFirstOption, ProductSecondOption productSecondOption, Integer quantity) {
        this.cart = cart;
        this.product = product;
        this.productFirstOption = productFirstOption;
        this.productSecondOption = productSecondOption;
        this.quantity = quantity;
    }
    public Long id() {return id; }
    public Cart cart() {return cart;}
    public Integer quantity() {return quantity;}

    public void addQuantity (Integer add) {
        this.quantity += add;
    }
    public void changeQuantity (Integer quantity) {this.quantity = quantity;}
}
