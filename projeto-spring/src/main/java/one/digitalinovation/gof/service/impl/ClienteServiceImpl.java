package one.digitalinovation.gof.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinovation.gof.DTO.ClienteDto;
import one.digitalinovation.gof.model.Cliente;
import one.digitalinovation.gof.model.ClienteRepository;
import one.digitalinovation.gof.model.Endereco;
import one.digitalinovation.gof.model.EnderecoRepository;
import one.digitalinovation.gof.service.ClienteService;
import one.digitalinovation.gof.service.ViaCepService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    // Busca todos
    @Override
    public Iterable<ClienteDto> buscarTodos() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

        return clientes.stream()
                .map(this::converterParaDto)
                .collect(Collectors.toList());
    }

    //Busca por ID
    @Override
    public ClienteDto buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return converterParaDto(cliente);
    }

    //Insere o cliente
    @Override
    public ClienteDto inserir(ClienteDto clienteDto) {

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());

        salvarClienteComCep(cliente, clienteDto.getCep());

        return converterParaDto(cliente);
    }

    //Atualiza o cliente
    @Override
    public ClienteDto atualizar(Long id, ClienteDto clienteDto) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(clienteDto.getNome());

        salvarClienteComCep(cliente, clienteDto.getCep());

        return converterParaDto(cliente);
    }

    //Deleta o cliente pelo seu ID
    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    // Método principal de salvamento de cliente
    private void salvarClienteComCep(Cliente cliente, String cep) {

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });

        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    //ENTITY → DTO
    private ClienteDto converterParaDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEndereco().getCep()
        );
    }
}