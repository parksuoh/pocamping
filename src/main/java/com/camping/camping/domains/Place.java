package com.camping.camping.domains;

import com.camping.camping.domains.vo.Description;
import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "place")
public class Place {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "name"))
    private Name name;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @Embedded
    private Description description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "place")
    List<PlaceImage> placeImages = new ArrayList<>();

    @OneToMany(mappedBy = "place")
    List<PlaceReservation> placeReservations = new ArrayList<>();

    public Place() {}

    public Place(Name name, Money price, Description description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long id() {return id;}
    public Name name() {return name;}
    public Money price() {return price;}
    public Description description() {return description;}
}
