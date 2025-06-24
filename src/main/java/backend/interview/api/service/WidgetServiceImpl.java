package backend.interview.api.service;

import backend.interview.api.dto.WidgetRequestDTO;
import backend.interview.api.dto.WidgetResponseDTO;
import backend.interview.api.exception.DuplicateWidgetNameException;
import backend.interview.api.exception.WidgetNotFoundException;
import backend.interview.api.mapper.WidgetMapper;
import backend.interview.api.model.Widget;
import backend.interview.api.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetServiceImpl implements WidgetService {

    @Autowired
    private WidgetRepository widgetRepository;

    @Override
    public List<WidgetResponseDTO> findAll() {
        return widgetRepository.findAll()
                .stream()
                .map(WidgetMapper::toResponseDTO)
                .toList();
    }

    @Override
    public WidgetResponseDTO findByName(String name) {
        Widget widget = widgetRepository.findByName(name)
                .orElseThrow(() -> new WidgetNotFoundException(name));
        return WidgetMapper.toResponseDTO(widget);
    }

    @Override
    public WidgetResponseDTO create(WidgetRequestDTO dto) {
        if (widgetRepository.existsByName(dto.getName())) {
            throw new DuplicateWidgetNameException(dto.getName());
        }
        Widget saved = widgetRepository.save(WidgetMapper.toEntity(dto));
        return WidgetMapper.toResponseDTO(saved);
    }

    @Override
    public WidgetResponseDTO update(String name, WidgetRequestDTO dto) {
        Widget widget = widgetRepository.findByName(name)
                .orElseThrow(() -> new WidgetNotFoundException(name));
        WidgetMapper.updateFromDTO(dto, widget);
        Widget updated = widgetRepository.save(widget);
        return WidgetMapper.toResponseDTO(updated);
    }

    @Override
    public void delete(String name) {
        if (!widgetRepository.existsByName(name)) {
            throw new WidgetNotFoundException(name);
        }
        widgetRepository.deleteByName(name);
    }
}
