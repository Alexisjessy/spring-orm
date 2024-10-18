package fr.afpa.orm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ClientDetailsDto {

    private UUID clientId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private List<AccountDto> accounts;  
    private List<InsuranceDto> insurances;  

    public ClientDetailsDto(UUID clientId, String firstName, String lastName, String email, LocalDate birthday,
                            List<AccountDto> accounts, List<InsuranceDto> insurances) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.accounts = accounts;
        this.insurances = insurances;
    }

    
    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public List<AccountDto> getAccounts() { return accounts; }
    public void setAccounts(List<AccountDto> accounts) { this.accounts = accounts; }

    public List<InsuranceDto> getInsurances() { return insurances; }
    public void setInsurances(List<InsuranceDto> insurances) { this.insurances = insurances; }
}
