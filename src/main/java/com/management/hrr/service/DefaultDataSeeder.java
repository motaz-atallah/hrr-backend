package com.management.hrr.service;

import com.management.hrr.entity.Room;
import com.management.hrr.enums.RoomType;
import com.management.hrr.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DefaultDataSeeder {

    private final RoomRepository repository;

    @Autowired
    public DefaultDataSeeder(RoomRepository repository) {
        this.repository = repository;
    }

    public void seedData() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(getRoom("101", RoomType.STANDARD, new BigDecimal("100.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning")));
        rooms.add(getRoom("102", RoomType.STANDARD, new BigDecimal("150.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Mini Bar")));
        rooms.add(getRoom("103", RoomType.STANDARD, new BigDecimal("140.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Balcony")));
        rooms.add(getRoom("104", RoomType.STANDARD, new BigDecimal("190.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Mini Bar", "Balcony")));
        rooms.add(getRoom("105", RoomType.STANDARD, new BigDecimal("125.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Kitchenette")));

        rooms.add(getRoom("201", RoomType.DELUXE, new BigDecimal("200.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning")));
        rooms.add(getRoom("202", RoomType.DELUXE, new BigDecimal("250.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Mini Bar")));
        rooms.add(getRoom("203", RoomType.DELUXE, new BigDecimal("340.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Balcony")));
        rooms.add(getRoom("204", RoomType.DELUXE, new BigDecimal("290.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Mini Bar", "Balcony")));
        rooms.add(getRoom("205", RoomType.DELUXE, new BigDecimal("225.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Kitchenette")));

        rooms.add(getRoom("301", RoomType.SUITE, new BigDecimal("300.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning")));
        rooms.add(getRoom("302", RoomType.SUITE, new BigDecimal("350.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Mini Bar", "Double Bed")));
        rooms.add(getRoom("303", RoomType.SUITE, new BigDecimal("440.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Balcony", "Work Desk")));
        rooms.add(getRoom("304", RoomType.SUITE, new BigDecimal("590.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Mini Bar", "Balcony", "Meeting Room Access")));
        rooms.add(getRoom("305", RoomType.SUITE, new BigDecimal("325.00"), Arrays.asList("Free Wi-Fi", "TV", "Air Conditioning", "Kitchenette")));

        repository.saveAll(rooms);
    }

    private Room getRoom(String number, RoomType type, BigDecimal price, List<String> amenities) {
        Room room = new Room();
        room.setRoomNumber(number);
        room.setType(type);
        room.setPrice(price);
        room.setAmenities(amenities);
        return room;
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }
}
