package com.management.hrr.service.implementations;

import com.management.hrr.dto.room.CreateRoomDto;
import com.management.hrr.dto.room.RoomDto;
import com.management.hrr.dto.room.UpdateRoomDto;
import com.management.hrr.entity.Reservation;
import com.management.hrr.entity.Room;
import com.management.hrr.exception.ResourceNotFoundException;
import com.management.hrr.filiters.RoomFilter;
import com.management.hrr.repository.RoomRepository;
import com.management.hrr.service.interfaces.IRoomService;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.management.hrr.mapper.RoomMapper.*;

@Service
@AllArgsConstructor
public class RoomService implements IRoomService {
    private RoomRepository repository;

    @Override
    public RoomDto create(CreateRoomDto room) {
        Room entity = INSTANCE.createRoomDTOToRoom(room);
        Room savedEntity = repository.save(entity);
        return INSTANCE.roomToRoomDTO(savedEntity);
    }

    @Override
    public RoomDto update(long id, UpdateRoomDto room) {
        if (repository.existsById(id)) {
            Room entity = INSTANCE.updateRoomDTOToRoom(room);
            entity.setId(id);
            Room savedEntity = repository.save(entity);
            return INSTANCE.roomToRoomDTO(savedEntity);
        } else {
            throw new ResourceNotFoundException("Room","Id", id);
        }
    }

    @Override
    public RoomDto getById(long id) {
        Room entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Room","Id", id));
        return INSTANCE.roomToRoomDTO(entity);
    }

    @Override
    public void delete(long id) {
        Room entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Room","Id", id));
        repository.deleteById(id);
    }

    @Override
    public List<RoomDto> getAll(RoomFilter filter) {
        Specification<Room> spec = getSpecification(filter);
        List<Room> entities = repository.findAll(spec);
        return INSTANCE.roomsToRoomDtos(entities);
    }
    private Specification<Room> getSpecification(RoomFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getRoomNumber() != null) {
                predicates.add(criteriaBuilder.equal(root.get("roomNumber"), filter.getRoomNumber()));
            }
            if (filter.getType() != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), filter.getType()));
            }
            if (filter.getPrice() != null) {
                predicates.add(criteriaBuilder.equal(root.get("price"), filter.getPrice()));
            }
            if (filter.getStartDate() != null && filter.getEndDate() != null) {
                if (!areDatesCorrect(filter.getStartDate(), filter.getEndDate())) {
                    predicates.add(criteriaBuilder.disjunction()); // Add a disjunction predicate
                }
                Join<Room, Reservation> reservationJoin = root.join("reservations", JoinType.LEFT);
                predicates.add(
                        criteriaBuilder.or(
                                criteriaBuilder.isNull(reservationJoin.get("id")),
                                criteriaBuilder.not(
                                        criteriaBuilder.and(
                                                criteriaBuilder.lessThan(reservationJoin.get("startDate"), filter.getEndDate()),
                                                criteriaBuilder.greaterThan(reservationJoin.get("endDate"), filter.getStartDate())
                                        )
                                )
                        )
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private boolean areDatesCorrect(LocalDate startDate, LocalDate endDate) {
        LocalDate now = LocalDate.now();
        return !startDate.isBefore(now) && !endDate.isBefore(startDate);
    }
}
