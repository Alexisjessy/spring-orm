package fr.afpa.orm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AccountDto(Long id, BigDecimal balance, LocalDateTime creationTime, UUID clientId) {
    // Le record n'a pas besoin de méthodes getter/setter explicites, elles sont générées automatiquement
}
