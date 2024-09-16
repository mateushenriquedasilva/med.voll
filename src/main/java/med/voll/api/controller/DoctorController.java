package med.voll.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.UpdateDoctorDTO;
import med.voll.api.dto.CreateDoctorDTO;
import med.voll.api.dto.DoctorDetailsDTO;
import med.voll.api.dto.DoctorsListDTO;
import med.voll.api.domain.models.Doctor;
import med.voll.api.repository.DoctorRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateDoctorDTO data, UriComponentsBuilder uriBuilder) {
        Doctor doctor = new Doctor(data);

        repository.save(doctor);
        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailsDTO> update(@RequestBody @Valid UpdateDoctorDTO data) {
    	Doctor doctor = repository.getReferenceById(data.id());
    	doctor.update(data);
    	
    	return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
    	Doctor doctor = repository.getReferenceById(id);
    	doctor.delete();
    	
    	return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<Page<DoctorsListDTO>> findDoctor(@PageableDefault(size = 10, sort = {"email"}) Pageable pagination) {
    	var page =  repository.findAllByAtivoTrue(pagination).map(DoctorsListDTO::new);
    	return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorsListDTO> findDoctorById(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorsListDTO(doctor));
    }
}
