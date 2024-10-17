package fr.afpa.orm.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Enum représentant les types d'assurance
    public enum InsuranceName {
        HABITATION,
        SANTE,
        VIE,
        AUTOMOBILE,
        SCOLAIRE,
        RESPONSABILITE_CIVILE_PERSONNELLE,
        RESPONSABILITE_CIVILE_PROFESSIONNELLE
    }

    // Stocke le type d'assurance dans la colonne 'name'
    private String name;

    
    @ManyToMany(mappedBy = "insurances")
    private Set<Client> clients = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

   
    public void setName(InsuranceName insuranceName) {
        this.name = insuranceName.name();  
    }

    
    public InsuranceName getInsuranceName() {
        return InsuranceName.valueOf(this.name);
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }
}
