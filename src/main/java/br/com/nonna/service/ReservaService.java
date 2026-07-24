package br.com.nonna.service;

import br.com.nonna.model.Reserva;
import br.com.nonna.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public List<Reserva> listar() {
        return repository.buscarTodas();
    }

    public Reserva buscar(UUID id) {
        return repository.buscarPorId(id);
    }

    public void criar(Reserva r) {
        repository.inserir(r);
    }

    public void alterar(UUID id, Reserva r) {
        repository.atualizar(id, r);
    }

    public void cancelar(UUID id) {
        repository.cancelar(id);
    }
}
