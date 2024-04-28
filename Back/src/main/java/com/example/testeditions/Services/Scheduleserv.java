package com.example.testeditions.Services;

import com.example.testeditions.Entites.Schedule;
import com.example.testeditions.Repositories.Schedulerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Scheduleserv implements ScheduleInter {

    private final Schedulerepo scheduleRepository;

    @Autowired
    public Scheduleserv(Schedulerepo schedulerepo) {
        this.scheduleRepository = schedulerepo;
    }

    @Override
    public Schedule ajouterschedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule getschedulebyuserid(long id) {
        return scheduleRepository.findByUserId(id);
    }

    @Override
    public Schedule changeavalibilty(long id) {
        Schedule schedule = scheduleRepository.findById(id).orElse(null);
        if (schedule != null) {
            // Toggle the availability
            if ("available".equals(schedule.getAvailability())) {
                schedule.setAvailability("unavailable");
            } else {
                schedule.setAvailability("available");
            }
            return scheduleRepository.save(schedule);
        }
        return null; // Or handle the case where schedule is not found
    }

    @Override
    public List<Schedule> getschedulebyarea(String area) {
        return scheduleRepository.findByArea(area);
    }

    @Override
    public List<Schedule> getschedulebydate(String date) {
        return scheduleRepository.findByDate(date);
    }

    @Override
    public List<String> getschedulesstations() {
        return scheduleRepository.findDistinctStations();
    }

    ;




    public Map<String, Long> getSchedulesCountByArea() {
        List<Object[]> results = scheduleRepository.findSchedulesCountByArea();

        Map<String, Long> schedulesCountByArea = new HashMap<>();
        for (Object[] result : results) {
            String area = (String) result[0];
            Long count = (Long) result[1];
            schedulesCountByArea.put(area, count);
        }
        return schedulesCountByArea;
    }
}
