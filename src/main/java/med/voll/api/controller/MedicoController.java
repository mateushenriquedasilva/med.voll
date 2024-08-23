package med.voll.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosAtualizarcaoMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired // o próprio spring vai instanciar e passar o atributo dentro da nossa classe
    private MedicoRepository repository;
    
    @PostMapping
    @Transactional // Transação ativa com o banco de dados, porque vamos persistir dados no banco.
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }
    
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarcaoMedico dados) {
    	Medico medico = repository.getReferenceById(dados.id());
    	
    	if (medico != null) {
    		Optional.ofNullable(dados.nome()).ifPresent(medico::setNome);
    		Optional.ofNullable(dados.telefone()).ifPresent(medico::setTelefone);
    		Optional.ofNullable(dados.endereco()).ifPresent(endereco -> new Endereco(dados.endereco()));
    	}	
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
    	repository.deleteById(id);
    }
    
    @GetMapping
    public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = {"email"}) Pageable paginacao /*Trabalhando com paginacao*/) {
    	return repository.findAll(paginacao).map(DadosListagemMedicos::new);
    }
}
