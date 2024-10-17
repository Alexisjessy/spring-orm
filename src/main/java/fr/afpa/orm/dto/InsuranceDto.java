package fr.afpa.orm.dto;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record InsuranceDto(Long id, String name) {
    // Le record n'a pas besoin de méthodes getter/setter explicites, elles sont générées automatiquement
}
