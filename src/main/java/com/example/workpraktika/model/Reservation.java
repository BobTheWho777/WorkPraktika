package com.example.workpraktika.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Reservations")
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

    @ManyToMany
    @JoinTable(name = "reservation_services",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<additionalService> additionalServices = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "reservation_complaints",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "complaint_id"))
    private List<Complaint> complaints = new ArrayList<>();


    @Column(name = "total_debt", nullable = false, length = 50)
    private String totalDebt;

    public Reservation() {
    }

    public Reservation(Long id, Room room, Guest guest, Organization organization, String floor, String reservations, String numberOfPeople, String dateReservation, String dateIn, String dateOut, List<additionalService> additionalServices, List<Complaint> complaints, String totalDebt) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.organization = organization;
        this.floor = floor;
        this.reservations = reservations;
        this.numberOfPeople = numberOfPeople;
        this.dateReservation = dateReservation;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.additionalServices = additionalServices;
        this.complaints = complaints;
        this.totalDebt = totalDebt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getReservations() {
        return reservations;
    }

    public void setReservations(String reservations) {
        this.reservations = reservations;
    }

    public String getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public List<additionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<additionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public String getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(String totalDebt) {
        this.totalDebt = totalDebt;
    }
}



