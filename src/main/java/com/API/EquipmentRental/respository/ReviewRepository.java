package com.API.EquipmentRental.respository;

import com.API.EquipmentRental.model.Equipment;
import com.API.EquipmentRental.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
//JPQL DAJEMY NAZWY Z JAVA A W NATIVEQUERY Z BAZY DANYCH

    @Query("SELECT r FROM Review r " +
            "JOIN r.user u " +
            "WHERE u.id = :userId " +
            "ORDER BY r.rating DESC")
    Page<Review> findReviewFromUser(@Param("userId") UUID userId, Pageable pageable);

//OFFSET ZNACZY ŻE POMIMIE LICZBE REKORDOW KTORA SIE WSKAZE
    @Query(value = "SELECT * FROM reviews " +
            "WHERE userid = :userId " +
            "ORDER BY rating DESC " +
            "LIMIT :size OFFSET :offset",
            nativeQuery = true)
    List<Review> findReviewFromUser(@Param("userId") UUID userId, @Param("size") int size, @Param("offset") int offset);


    @Query("SELECT r FROM Review r " +
            "JOIN r.equipment e " +
            "WHERE e.id = :equipmentId " +
            "ORDER BY r.rating DESC")
    Page<Review> findReviewFromEquipment(@Param("equipmentId") UUID equipmentId, Pageable pageable);


    @Query(value = "SELECT * FROM reviews " +
            "WHERE equipmentid = :equipmentId " +
            "ORDER BY rating DESC " +
            "LIMIT :size OFFSET :offset",
            nativeQuery = true)
    List<Review> findReviewFromEquipment(@Param("equipmentId") UUID equipmentId, @Param("size") int size, @Param("offset") int offset);

    @Query(value = "SELECT AVG(r.rating) FROM reviews r " +
            "WHERE equipmentid = :equipmentId",
            nativeQuery = true)
    Double countAvgRating(@Param("equipmentId") UUID equipmentId);


    @Query("SELECT AVG(r.rating) FROM Review r" +
            " WHERE e.id = :equipmentId")
    Double countAverageRating(@Param("equipmentId") UUID equipmentId);
}

