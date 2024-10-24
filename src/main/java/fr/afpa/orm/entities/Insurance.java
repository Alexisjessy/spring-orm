package fr.afpa.orm.entities;

import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
=======
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

>>>>>>> 0f9a292d2045b83a746bf1b2bda385d1385bf3c8
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    public enum InsuranceName {
        HABITATION,
        SANTE,
        VIE,
        AUTOMOBILE,
        SCOLAIRE,
        RESPONSABILITE_CIVILE_PERSONNELLE,
        RESPONSABILITE_CIVILE_PROFESSIONNELLE
    }
    
    private String name;

<<<<<<< HEAD
    
    @ManyToMany(mappedBy = "insurances", fetch = FetchType.EAGER,cascade = CascadeType.ALL )
     @Fetch(FetchMode.JOIN)
=======
    @JsonIgnore
    @ManyToMany(mappedBy = "insurances",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
>>>>>>> 0f9a292d2045b83a746bf1b2bda385d1385bf3c8
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
