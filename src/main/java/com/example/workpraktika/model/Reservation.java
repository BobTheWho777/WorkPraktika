package com.example.workpraktika.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Reservations")
@Getter
@Setter
@RequiredArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "organizations_id")
    private Organization organization;


    private String floor;
    private String reservations;
    private String numberOfPeople;
    private String dateReservation;
    private String dateIn;
    private String dateOut;

    @ManyToOne
    @JoinColumn(name = "additional_service_id")
    private additionalService additionalService;

    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    @Column(name = "total_debt", nullable = false, length = 50)
    private String totalDebt;
}
