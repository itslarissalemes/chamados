package br.com.sistemas.chamados.service;

import org.springframework.stereotype.Service;
import br.com.sistemas.chamados.dto.TecnicoRequest;
import br.com.sistemas.chamados.dto.TecnicoResponse;
import br.com.sistemas.chamados.repository.TecnicoRepository;
import java.util.Collections;
import java.util.List;

@Service
public class TecnicoService {
    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public TecnicoResponse criar(TecnicoRequest request) {
        return null;
    }

    public List<TecnicoResponse> listar() {
        return Collections.emptyList();
    }

    public TecnicoResponse buscar(Long id) {
        return null;
    }

    public TecnicoResponse atualizar(Long id, TecnicoRequest request) {
        return null;
    }

    public void excluir(Long id) {
        // Lógica de exclusão
    }
}