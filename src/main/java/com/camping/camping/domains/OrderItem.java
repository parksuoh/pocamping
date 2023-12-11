package com.camping.camping.domains;

import com.camping.camping.domains.vo.FirstOptionName;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.SecondOptionName;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@Table(name = "order_item")
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "product_name"))
    private Name productName;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "product_price"))
    private Money productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_first_option_id")
    private ProductFirstOption productFirstOption;

    @Embedded
    private FirstOptionName productFirstOptionName;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "product_first_option_price"))
    private Money productFirstOptionPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_second_option_id")
    private ProductSecondOption productSecondOption;

    @Embedded
    private SecondOptionName productSecondOptionName;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "product_second_option_price"))
    private Money productSecondOptionPrice;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "unit_price"))
    private Money unitPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_price"))
    private Money totalPrice;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated;

    public OrderItem() {}


    public OrderItem(Order order, Product product, Name productName, Money productPrice, ProductFirstOption productFirstOption, FirstOptionName productFirstOptionName, Money productFirstOptionPrice, ProductSecondOption productSecondOption, SecondOptionName productSecondOptionName, Money productSecondOptionPrice, Money unitPrice, Integer quantity, Money totalPrice) {
        this.order = order;
        this.product = product;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productFirstOption = productFirstOption;
        this.productFirstOptionName = productFirstOptionName;
        this.productFirstOptionPrice = productFirstOptionPrice;
        this.productSecondOption = productSecondOption;
        this.productSecondOptionName = productSecondOptionName;
        this.productSecondOptionPrice = productSecondOptionPrice;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long id() {
        return id;
    }

    public Name productName() {
        return productName;
    }

    public Money productPrice() {
        return productPrice;
    }

    public ProductFirstOption productFirstOption() {
        return productFirstOption;
    }

    public FirstOptionName productFirstOptionName() {
        return productFirstOptionName;
    }

    public Money productFirstOptionPrice() {
        return productFirstOptionPrice;
    }

    public ProductSecondOption productSecondOption() {
        return productSecondOption;
    }

    public SecondOptionName productSecondOptionName() {
        return productSecondOptionName;
    }

    public Money productSecondOptionPrice() {
        return productSecondOptionPrice;
    }

    public Money unitPrice() {
        return unitPrice;
    }

    public Integer quantity() {
        return quantity;
    }

    public Money totalPrice() {
        return totalPrice;
    }
}
