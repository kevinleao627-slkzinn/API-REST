package one.digitalinovation.gof.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import one.digitalinovation.gof.model.Endereco;
/**
 * Cliente HTTP declarativo para integração com a API ViaCEP.
 *
 * Utiliza Spring Cloud OpenFeign para realizar chamadas HTTP
 * de forma simplificada, sem necessidade de usar RestTemplate
 * ou WebClient manualmente.
 *
 * A anotação @FeignClient define:
 * - name: nome interno do cliente
 * - url: URL base da API externa
 */
@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {
    
    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}