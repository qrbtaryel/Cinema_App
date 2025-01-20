package org.example.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.response.LanguageResponse;
import org.example.cinema.model.Language;
import org.example.cinema.repository.LanguageRepository;
import org.example.cinema.service.LanguageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;


    @Override
    public List<LanguageResponse> getAll() {
        return languageRepository.findAll().stream()
                .map(language -> LanguageResponse.builder()
                        .id(language.getId())
                        .name(language.getName())
                        .build())
                .toList();
    }

    @Override
    public Language findById(Long langId) {
        return languageRepository.findById(langId).orElse(null);
    }
}
