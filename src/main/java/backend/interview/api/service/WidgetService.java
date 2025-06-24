package backend.interview.api.service;

import backend.interview.api.dto.WidgetRequestDTO;
import backend.interview.api.dto.WidgetResponseDTO;

import java.util.List;

public interface WidgetService {
    List<WidgetResponseDTO> findAll();
    WidgetResponseDTO findByName(String name);
    WidgetResponseDTO create(WidgetRequestDTO dto);
    WidgetResponseDTO update(String name, WidgetRequestDTO dto);
    void delete(String name);
}
