package com.management.hrr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.management.hrr.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ElementCollection
    @CollectionTable(name = "room_amenities", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "amenity", nullable = false)
    private List<String> amenities;

    @OneToMany(mappedBy = "room")
    @JsonBackReference
    @Column(name = "reservations")
    private List<Reservation> reservations;
}
