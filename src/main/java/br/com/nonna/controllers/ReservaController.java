package br.com.nonna.controllers;

import br.com.nonna.model.Reserva;
import br.com.nonna.service.ReservaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

// @CrossOrigin autoriza que uma página aberta em outra porta consuma esta API.
// Sem isso o navegador barra a resposta.
@CrossOrigin
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reserva> listar() {
        return service.listar();
    }

    // UUID no parâmetro: o Spring converte o texto da URL para o tipo automaticamente.
    @GetMapping("/{id}")
    public Reserva buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    // @RequestBody pega o JSON que chegou e preenche o objeto pelos setters.
    // O id é gerado pela aplicação, não pelo cliente.
    @PostMapping
    public void criar(@RequestBody Reserva r) {
        service.criar(r);
    }

    @PutMapping("/{id}")
    public void alterar(@PathVariable UUID id, @RequestBody Reserva r) {
        service.alterar(id, r);
    }

    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable UUID id) {
        service.cancelar(id);
    }
}
