package one.digitalinovation.gof.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

//Características dos clientes
public class ClienteDto {

    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 números")
    private String cep;

    public ClienteDto() {
    }

    public ClienteDto(Long id, String nome, String cep) { 
        this.id = id;
        this.nome = nome;
        this.cep = cep;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}