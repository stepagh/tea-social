package com.dadaev.tea_social.controller;

import com.dadaev.tea_social.Repository.TeaRepository;
import com.dadaev.tea_social.Service.TeaService;
import com.dadaev.tea_social.dto.TeaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teas")
public class TeaController {

    private TeaService teaService;
    public TeaController(TeaService teaService) {
        this.teaService = teaService;
    }

    @GetMapping
    public ResponseEntity<List<TeaDTO>> getTeas() {
        List<TeaDTO> teas = teaService.loadTeas();
        return ResponseEntity.ok(teas);
    }
}
