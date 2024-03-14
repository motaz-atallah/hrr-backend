package com.management.hrr.service.implementations;

import com.management.hrr.dto.reservation.BaseReservationDto;
import com.management.hrr.dto.reservation.CreateReservationDto;
import com.management.hrr.dto.reservation.ReservationDto;
import com.management.hrr.dto.reservation.UpdateReservationDto;
import com.management.hrr.entity.Reservation;
import com.management.hrr.exception.ConflictException;
import com.management.hrr.exception.ResourceNotFoundException;
import com.management.hrr.filiters.ReservationFilter;
import com.management.hrr.repository.ReservationRepository;
import com.management.hrr.repository.RoomRepository;
import com.management.hrr.service.interfaces.IReservationService;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.management.hrr.mapper.ReservationMapper.INSTANCE;

@Service
@AllArgsConstructor
public class ReservationService implements IReservationService {
    private ReservationRepository repository;
    private RoomRepository roomRepository;

    @Override
    public ReservationDto create(CreateReservationDto reservation) {
        ValidateDto(reservation, null);
        Reservation entity = INSTANCE.createReservationDtoToReservation(reservation);
        Reservation savedEntity = repository.save(entity);
        return INSTANCE.reservationToReservationDto(savedEntity);
    }

    @Override
    public ReservationDto update(long id, UpdateReservationDto reservation) {
        if(!repository.existsById(id))
            throw new ResourceNotFoundException("Reservation", "Id", id);
        ValidateDto(reservation, id);
        Reservation entity = INSTANCE.updateReservationDtoToReservation(reservation);
        entity.setId(id);
        Reservation savedEntity = repository.save(entity);
        return INSTANCE.reservationToReservationDto(savedEntity);
    }

    @Override
    public ReservationDto getById(long id) {
        Reservation entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Reservation", "Id", id));
        return INSTANCE.reservationToReservationDto(entity);
    }

    @Override
    public void delete(long id) {
        if(!repository.existsById(id))
            throw new ResourceNotFoundException("Reservation", "Id", id);
        repository.deleteById(id);
    }

    @Override
    public List<ReservationDto> getAll(ReservationFilter filter) {
        Specification<Reservation> spec = getSpecification(filter);
        List<Reservation> entities = repository.findAll(spec);
        return INSTANCE.reservationsToReservationDtos(entities);
    }

    private void ValidateDto(BaseReservationDto reservation, Long reservation1) {
        boolean exists = roomRepository.existsById(reservation.getRoomId());
        if(!exists) {
            throw new ResourceNotFoundException("Room", "Id", reservation.getRoomId());
        }
        if (!isRoomVacancy(reservation.getRoomId(), reservation.getStartDate(), reservation.getEndDate(), reservation1)) {
            throw new ConflictException("Room");
        }
    }

    private boolean isRoomVacancy(long roomId, LocalDate startDate, LocalDate endDate, Long reservationId) {
        Specification<Reservation> combinedSpec = (root, query, criteriaBuilder) -> {
            // Room ID specification
            Predicate roomPredicate = criteriaBuilder.equal(root.get("room").get("id"), roomId);
            // Reservation ID specification
            Predicate idPredicate = reservationId != null ? criteriaBuilder.notEqual(root.get("id"), reservationId) : criteriaBuilder.conjunction();
            // Date specification
            Predicate datePredicate = criteriaBuilder.or(
                    criteriaBuilder.greaterThan(root.get("startDate"), endDate),
                    criteriaBuilder.lessThan(root.get("endDate"), startDate)
            );
            // Combine all predicates with AND
            return criteriaBuilder.and(roomPredicate, idPredicate, criteriaBuilder.not(datePredicate));
        };

        return !repository.exists(combinedSpec);
    }

    private Specification<Reservation> getSpecification(ReservationFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getGuestName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("guestName"), filter.getGuestName()));
            }
            if (filter.getCreatedBy() != null) {
                predicates.add(criteriaBuilder.equal(root.get("createdBy"), filter.getCreatedBy()));
            }
            if (filter.getRoomId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("room").get("id"), filter.getRoomId()));
            }
            if(filter.getCurrentReservation() != null && filter.getCurrentReservation()) {
                predicates.add(
                        criteriaBuilder.and(
                                criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), LocalDate.now()),
                                criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), LocalDate.now())
                        )
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
