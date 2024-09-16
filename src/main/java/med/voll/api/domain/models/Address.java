package med.voll.api.domain.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.AddressDTO;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    public Address(AddressDTO address) {
        this.logradouro = address.logradouro();
        this.bairro = address.bairro();
        this.cep = address.cep();
        this.cidade = address.cidade();
        this.uf = address.uf();
        this.numero = address.numero();
        this.complemento = address.complemento();
    }
    private String logradouro; 
    private String bairro;
    private String cep; 
    private String cidade;
    private String uf; 
    private String numero;
    private String complemento;
}
