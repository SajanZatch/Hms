package com.hms.repository;

import com.hms.entity.Bookings;
import com.hms.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;

@EnableJpaRepositories
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

}