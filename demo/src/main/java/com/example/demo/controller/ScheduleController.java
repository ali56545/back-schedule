package com.example.demo.controller;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "*")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<ScheduleDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ScheduleDTO create(@RequestBody ScheduleDTO dto) {
        return service.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}