package med.voll.api.dto;

import med.voll.api.domain.models.Doctor;
import med.voll.api.domain.enums.Specialty;

public record DoctorsListDTO(Long id, String nome, String email, String crm, Specialty specialty) {
	public DoctorsListDTO(Doctor doctor) {
		this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
	}
}
