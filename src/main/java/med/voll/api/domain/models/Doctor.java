package med.voll.api.domain.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.enums.Specialty;
import med.voll.api.dto.CreateDoctorDTO;
import med.voll.api.dto.UpdateDoctorDTO;

@Table(name = "doctor")
@Entity(name = "Doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    public Doctor(CreateDoctorDTO data) {
    	this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.telephone = data.telephone();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void update(UpdateDoctorDTO data) {
    	if (data.name() != null) {
    		this.name = data.name();
    	}

    	if (data.telephone() != null) {
    		this.telephone = data.telephone();
    	}

    	if (data.address() != null) {
    		this.address = new Address(data.address());
    	}
    }

    public void delete() {
    	this.setActive(false);
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean active;
    private String name;
    private String email;
    private String crm;
    private String telephone;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;
}
