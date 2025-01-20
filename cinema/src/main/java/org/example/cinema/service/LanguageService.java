package org.example.cinema.service;

import org.example.cinema.dto.response.LanguageResponse;
import org.example.cinema.model.Language;

import java.util.List;

public interface LanguageService {
    List<LanguageResponse> getAll();

    Language findById(Long langId);
}
