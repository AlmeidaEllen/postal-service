package br.com.postal_service.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class AddressStatus {
    public static final int DEFAULT_ID = 1;

    @Id
    private int id; 
    private Status status; 

}
