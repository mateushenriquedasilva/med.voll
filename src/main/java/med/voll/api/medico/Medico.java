package med.voll.api.medico;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
import med.voll.api.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    public Medico(DadosCadastroMedico dados) {
    	this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }
    
    public void atualizarInformacoes(DadosAtualizarcaoMedico dados) {
    	if (dados.nome() != null) {
    		this.nome = dados.nome();    		
    	}
    	
    	if (dados.telefone() != null) {
    		this.telefone = dados.telefone();
    	}
    	
    	if (dados.endereco() != null) {
    		this.endereco = new Endereco(dados.endereco());
    	}
    }
    
    public void excluir() {
    	this.setAtivo(false);
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded // Usamos uma classe separada para informar os dados, mas o decorator faz com que fique tudo agrupado na tabela
    private Endereco endereco;
}
