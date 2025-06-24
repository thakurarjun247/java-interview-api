package backend.interview.api.repository;

import backend.interview.api.model.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WidgetRepository extends JpaRepository<Widget, Long> {
    Optional<Widget> findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
}
