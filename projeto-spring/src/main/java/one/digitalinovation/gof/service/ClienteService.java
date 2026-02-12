package one.digitalinovation.gof.service;

import one.digitalinovation.gof.DTO.ClienteDto;
/**
 * Interface responsável por definir as regras de negócio
 * relacionadas à entidade Cliente.
 *
 * A camada Service faz a intermediação entre o Controller
 * e o Repository, aplicando validações, integrações externas
 * (como ViaCEP) e regras de negócio antes de acessar o banco.
 */
public interface ClienteService {

    Iterable<ClienteDto> buscarTodos();

    ClienteDto buscarPorId(Long id);

    ClienteDto inserir(ClienteDto clienteDTO);

    ClienteDto atualizar(Long id, ClienteDto clienteDTO);

    void deletar(Long id);
}