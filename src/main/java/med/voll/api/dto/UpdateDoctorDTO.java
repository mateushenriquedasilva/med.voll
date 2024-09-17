package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateDoctorDTO(
		@NotNull
		Long id, 
		String name,
		String telephone,
		AddressDTO address) {

}
