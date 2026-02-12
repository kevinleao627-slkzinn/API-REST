package one.digitalinovation.gof.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import one.digitalinovation.gof.DTO.ClienteDto;
import one.digitalinovation.gof.service.ClienteService;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    //Busca todos
    @GetMapping
    public ResponseEntity<Iterable<ClienteDto>> buscarTodos() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    //Busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    //Cria o cliente
    @PostMapping
    public ResponseEntity<ClienteDto> inserir(@Valid @RequestBody ClienteDto clienteDto) {
        ClienteDto novoCliente = clienteService.inserir(clienteDto);

        return ResponseEntity
                .created(URI.create("/clientes/" + novoCliente.getId()))
                .body(novoCliente);
    }

    //Atualiza o cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDto clienteDto) {

        ClienteDto clienteAtualizado = clienteService.atualizar(id, clienteDto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    //Deleta o cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}