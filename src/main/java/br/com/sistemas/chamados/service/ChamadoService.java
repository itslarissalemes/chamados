package br.com.sistemas.chamados.service;

import org.springframework.stereotype.Service;
import br.com.sistemas.chamados.dto.ChamadoResponse;
import br.com.sistemas.chamados.dto.AtribuirTecnicoRequest;
import br.com.sistemas.chamados.dto.AtualizarStatusRequest;
import br.com.sistemas.chamados.repository.ChamadoRepository;
import java.util.Collections;
import java.util.List;

@Service
public class ChamadoService {
    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public ChamadoResponse criar(Object request) {
        return null;
    }

    public List<ChamadoResponse> listar() {
        return Collections.emptyList();
    }

    public ChamadoResponse buscar(Long id) {
        return null;
    }

    public ChamadoResponse atribuirTecnico(Long id, AtribuirTecnicoRequest request) {
        return null;
    }

    public ChamadoResponse atualizarStatus(Long id, AtualizarStatusRequest request) {
        return null;
    }
}