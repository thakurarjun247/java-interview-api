package backend.interview.api.controller;

import backend.interview.api.dto.WidgetRequestDTO;
import backend.interview.api.dto.WidgetResponseDTO;
import backend.interview.api.service.WidgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/widgets")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;

    @GetMapping
    public List<WidgetResponseDTO> getAll() {
        return widgetService.findAll();
    }

    @GetMapping("/{name}")
    public WidgetResponseDTO getByName(@PathVariable String name) {
        return widgetService.findByName(name);
    }

    @PostMapping
    public ResponseEntity<WidgetResponseDTO> create(@Valid @RequestBody WidgetRequestDTO dto) {
        return ResponseEntity.ok(widgetService.create(dto));
    }

    @PutMapping("/{name}")
    public ResponseEntity<WidgetResponseDTO> update(@PathVariable String name,
                                                    @Valid @RequestBody WidgetRequestDTO dto) {
        return ResponseEntity.ok(widgetService.update(name, dto));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        widgetService.delete(name);
        return ResponseEntity.noContent().build();
    }
}
