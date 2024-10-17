package fr.afpa.orm.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "creationtime", nullable = false)
    private LocalDateTime creationTime;
    @PrePersist
    public void prePersist() {
        if (this.creationTime == null) {
            this.creationTime = LocalDateTime.now();
        }
    }
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = true)
    private Client client;
    

    
    // public List<Transaction> transactions; 

    public Account() {}

    public Account(BigDecimal balance, LocalDateTime creationTime, Client client) {
        this.balance = balance;
        this.creationTime = creationTime;
        this.client = client;
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
