package com.dadaev.tea_social.controller;

import com.dadaev.tea_social.Service.TeaService;
import com.dadaev.tea_social.dto.TeaDto;
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
    public ResponseEntity<List<TeaDto>> getTeas() {
        List<TeaDto> teas = teaService.loadTeas();
        return ResponseEntity.ok(teas);
    }
}
