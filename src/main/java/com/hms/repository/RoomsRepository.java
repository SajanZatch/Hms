package com.hms.repository;

import com.hms.entity.Property;
import com.hms.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


@EnableJpaRepositories
public interface RoomsRepository extends JpaRepository<Rooms, Long> {

    @Query("select r from Rooms r where r.date between :startDate and :endDate and r.roomType=:type and r.property=:property")
    List<Rooms> findByRoomTypeAndProperty(
            @Param("startDate") LocalDate fromDate,
            @Param("endDate") LocalDate toDate,
            @Param("type") String roomType,
            @Param("property") Property property
    );












}