package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Event;
import com.piisw.jpa.entities.RequestEvent;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findByTimeBetweenAndAnalysisRequired(
      LocalDateTime start,
      LocalDateTime end,
      boolean toBeAnalyzed,
      Pageable pageable
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM Event e WHERE e.time < :timeLimit")
    void deleteInBulkBeforDate(@Param("timeLimit") LocalDateTime timeLimit);

    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.analysisRequired = true WHERE TYPE(e) = :clazz AND e.duration > :minDuration")
    void updateInBulkToBeAnalyzedByType(@Param("clazz") Class<? extends Event> clazz,
                                        @Param("minDuration") int minDuration);

    @Query("SELECT new com.piisw.jpa.repositories.ServerStatistic(e.server, COUNT(e)) " +
      "FROM Event e " +
      "GROUP BY e.server")
    List<ServerStatistic> findEventCountByServer();
}
