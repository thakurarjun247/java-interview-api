package backend.interview.api.mapper;

import backend.interview.api.dto.WidgetRequestDTO;
import backend.interview.api.dto.WidgetResponseDTO;
import backend.interview.api.model.Widget;

public class WidgetMapper {

    public static Widget toEntity(WidgetRequestDTO dto) {
        Widget w = new Widget();
        w.setName(dto.getName());
        w.setDescription(dto.getDescription());
        w.setPrice(dto.getPrice());
        return w;
    }

    public static WidgetResponseDTO toResponseDTO(Widget widget) {
        return new WidgetResponseDTO(widget.getName(), widget.getDescription(), widget.getPrice());
    }

    public static void updateFromDTO(WidgetRequestDTO dto, Widget widget) {
        widget.setDescription(dto.getDescription());
        widget.setPrice(dto.getPrice());
    }
}
