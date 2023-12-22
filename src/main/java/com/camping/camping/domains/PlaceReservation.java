package com.camping.camping.domains;

import com.camping.camping.domains.vo.Money;
import com.camping.camping.domains.vo.Name;
import com.camping.camping.domains.vo.ReservationStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "place_reservation")
public class PlaceReservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Embedded
    @AttributeOverride(name = "text", column = @Column(name = "place_name"))
    private Name placeName;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "reservation_status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated;

    public PlaceReservation() {}

    public PlaceReservation(User user, String userName, Place place, Name placeName, Money price, LocalDate reservationDate, ReservationStatus reservationStatus) {
        this.user = user;
        this.userName = userName;
        this.place = place;
        this.placeName = placeName;
        this.price = price;
        this.reservationDate = reservationDate;
        this.reservationStatus = reservationStatus;
    }

    public Long id() {return id;}
    public User user() {return user;}
    public String userName() {return userName;}
    public Place place() {return place;}
    public Name placeName() {return placeName;}

    public Money price() {return price;}
    public LocalDate reservationDate() {return reservationDate;};
    public ReservationStatus reservationStatus() {return reservationStatus;}

    public void changeReservationStatus(ReservationStatus reservationStatus){
        this.reservationStatus = reservationStatus;
    }

    public void toRequest() {
        this.reservationStatus = ReservationStatus.REQUEST;
    }

    public void toConfirm() {
        this.reservationStatus = ReservationStatus.CONFIRM;
    }

    public void toCancel() {
        this.reservationStatus = ReservationStatus.RESERVATION_CANCELED;
    }


}
