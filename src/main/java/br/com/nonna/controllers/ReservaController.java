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

// @RequestMapping define o prefixo comum — todos os endpoints ficam em /reservas.
@RestController
@RequestMapping("/reservas")
// @CrossOrigin autoriza que uma página aberta em outra porta (ex.: o front-end
// em localhost:5500) consuma esta API. Sem isso o navegador barra a resposta.
@CrossOrigin
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    // GET /reservas — retorna todas as reservas ordenadas por data e hora.
    @GetMapping
    public List<Reserva> listar() {
        return service.listar();
    }

    // GET /reservas/{id} — retorna uma reserva específica ou null se não existir.
    // O tipo UUID no parâmetro faz o Spring converter o texto da URL automaticamente.
    @GetMapping("/{id}")
    public Reserva buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    // POST /reservas — @RequestBody pega o JSON que chegou e preenche o objeto
    // Reserva pelos setters. O id é gerado pela aplicação, não pelo cliente.
    @PostMapping
    public void criar(@RequestBody Reserva r) {
        service.criar(r);
    }

    // PUT /reservas/{id} — atualiza data, hora e número de pessoas.
    // Nome e telefone não mudam: quem fez a reserva é quem fez a reserva.
    @PutMapping("/{id}")
    public void alterar(@PathVariable UUID id, @RequestBody Reserva r) {
        service.alterar(id, r);
    }

    // DELETE /reservas/{id} — cancela a reserva com aquele id.
    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable UUID id) {
        service.cancelar(id);
    }
}
