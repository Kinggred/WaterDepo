package Models.Kayak;

import Models.Kayak.DTO.KayakModelDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Entity
@Table(name = "Kayaks")
public class Kayak {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private KayakModel type;

    @OneToMany(mappedBy = "kayak")
    private Set<Rental> rents;

    public UUID getId() {
        return id;
    }

    public void setType(KayakModel type) {
        this.type = type;
    }

    public Set<Rental> getRents() {
        return rents;
    }

    public UUID getType() {
        UUID id = type.getId();
        return id;
    }
}
