package com.dadaev.tea_social.Service;

import com.dadaev.tea_social.Repository.TeaRepository;
import com.dadaev.tea_social.dto.CreateReviewRequest;
import com.dadaev.tea_social.dto.TeaDto;
import com.dadaev.tea_social.exceptions.ResourceNotFoundException;
import com.dadaev.tea_social.mapper.TeaMapper;
import com.dadaev.tea_social.model.Tea;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeaService {
    private final TeaRepository teaRepository;
    private final TeaMapper teaMapper;


    public List<TeaDto> loadTeas() {
        return teaRepository.findAll().stream().map(teaMapper::toDto).toList();
    }

    public Tea getTeaFromRequest(CreateReviewRequest request) {
        Tea tea;
        if (request.teaId() != null) {
            long id = request.teaId();
            tea = teaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tea with id: " + id + "not found"));
        }
        else {
            Tea newTea = teaMapper.toEntity(request);
            tea = teaRepository.save(newTea);
        }
        return tea;
    }
}
