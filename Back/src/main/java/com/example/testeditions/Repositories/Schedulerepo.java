package com.example.testeditions.Repositories;

import com.example.testeditions.Entites.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Schedulerepo extends JpaRepository<Schedule,Long> {
    Schedule findByUserId(long id);

    List<Schedule> findByArea(String area);

    List<Schedule> findByDate(String date);

    List<Schedule> findByStationIsNotNull();

    @Query("SELECT s.area, COUNT(s) FROM Schedule s GROUP BY s.area")
    List<Object[]> findSchedulesCountByArea();

    @Query("SELECT s.station FROM Schedule s WHERE s.station IS NOT NULL")
    List<String> findDistinctStations();
}
