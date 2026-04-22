package com.example.demo.service;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repo;
    private final TeacherRepository teacherRepo;
    private final SubjectRepository subjectRepo;
    private final RoomRepository roomRepo;
    private final StudentGroupRepository groupRepo;

    public ScheduleService(
            ScheduleRepository repo,
            TeacherRepository teacherRepo,
            SubjectRepository subjectRepo,
            RoomRepository roomRepo,
            StudentGroupRepository groupRepo
    ) {
        this.repo = repo;
        this.teacherRepo = teacherRepo;
        this.subjectRepo = subjectRepo;
        this.roomRepo = roomRepo;
        this.groupRepo = groupRepo;
    }

    // GET ALL
    public List<ScheduleDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // CREATE (ВОЗВРАТ К ПРОСТОЙ ЛОГИКЕ)
    public ScheduleDTO create(ScheduleDTO dto) {

        Teacher t = new Teacher();
        t.setName(dto.getTeacher());
        teacherRepo.save(t);

        Subject s = new Subject();
        s.setName(dto.getSubject());
        subjectRepo.save(s);

        Room r = new Room();
        r.setNumber(dto.getRoom());
        roomRepo.save(r);

        StudentGroup g = new StudentGroup();
        g.setName(dto.getGroup());
        groupRepo.save(g);

        Schedule sc = new Schedule();
        sc.setTime(dto.getTime()); // ⚠️ ВАЖНО: у тебя теперь time, не day/timeSlot
        sc.setTeacher(t);
        sc.setSubject(s);
        sc.setRoom(r);
        sc.setGroup(g);

        return toDTO(repo.save(sc));
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // MAPPER
    private ScheduleDTO toDTO(Schedule s) {
        return new ScheduleDTO(
                s.getId(),
                s.getTime(),
                s.getTeacher().getName(),
                s.getSubject().getName(),
                s.getRoom().getNumber(),
                s.getGroup().getName()
        );
    }
}