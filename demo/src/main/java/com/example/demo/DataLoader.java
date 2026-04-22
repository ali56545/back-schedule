package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(
            TeacherRepository teacherRepo,
            RoomRepository roomRepo,
            SubjectRepository subjectRepo,
            ScheduleRepository scheduleRepo,
            StudentGroupRepository groupRepo
    ) {
        return args -> {

            // если уже есть данные — не дублируем
            if (scheduleRepo.count() > 0) return;

            // ================= TEACHERS =================
            Teacher t1 = new Teacher();
            t1.setName("Данияр Сатыбалдиев");

            Teacher t2 = new Teacher();
            t2.setName("Айбек Токтосунов");

            teacherRepo.save(t1);
            teacherRepo.save(t2);

            // ================= ROOMS =================
            Room r1 = new Room();
            r1.setNumber("112");

            Room r2 = new Room();
            r2.setNumber("113");

            roomRepo.save(r1);
            roomRepo.save(r2);

            // ================= SUBJECTS =================
            Subject s1 = new Subject();
            s1.setName("Math");

            Subject s2 = new Subject();
            s2.setName("Physics");

            subjectRepo.save(s1);
            subjectRepo.save(s2);

            // ================= GROUPS =================
            StudentGroup g1 = new StudentGroup();
            g1.setName("cs-23");

            StudentGroup g2 = new StudentGroup();
            g2.setName("it-22");

            groupRepo.save(g1);
            groupRepo.save(g2);

            // ================= SCHEDULE =================
            Schedule sc1 = new Schedule();
            sc1.setTime("08:30");
            sc1.setTeacher(t1);
            sc1.setRoom(r1);
            sc1.setSubject(s1);
            sc1.setGroup(g1);

            Schedule sc2 = new Schedule();
            sc2.setTime("10:00");
            sc2.setTeacher(t2);
            sc2.setRoom(r2);
            sc2.setSubject(s2);
            sc2.setGroup(g2);

            scheduleRepo.save(sc1);
            scheduleRepo.save(sc2);
        };
    }
}