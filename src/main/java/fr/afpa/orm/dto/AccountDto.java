package fr.afpa.orm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import fr.afpa.orm.entities.Client;

/**
 * TODO : implémenter un DTO (uniquement à partir de l'implémentation de la relation "OneToMany")
 * 
 * Attention : il faudra peut être 1 DTO par classe métier ?
 * 
 * Plus d'informations sur la pattern DTO : https://medium.com/@zubeyrdamar/java-spring-boot-handling-infinite-recursion-a95fe5a53c92
 */
public class AccountDto {

    private Long id;
    private BigDecimal balance;
    private LocalDateTime creationDate;
     private UUID clientId;


    public AccountDto() {
    }

    
    public AccountDto(Long id, BigDecimal balance, LocalDateTime creationDate, Long clientId) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
        this.clientId = clientId;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}