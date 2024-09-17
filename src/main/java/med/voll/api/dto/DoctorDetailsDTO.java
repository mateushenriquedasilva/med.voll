package med.voll.api.dto;

import med.voll.api.domain.models.Address;
import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.enums.Specialty;

public record DoctorDetailsDTO(Long id, String nome, String email, String crm, Specialty specialty, Address address) {

	public DoctorDetailsDTO(Doctor doctor) {
		this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
	}
}
