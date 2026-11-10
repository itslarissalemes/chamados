package br.com.sistemas.chamados.service;

import org.springframework.stereotype.Service;
import br.com.sistemas.chamados.dto.ComentarioRequest;
import br.com.sistemas.chamados.dto.ComentarioResponse;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ComentarioService {

    public ComentarioResponse adicionar(Long chamadoId, ComentarioRequest request) {
        return new ComentarioResponse(1L, "Comentário exemplo", LocalDateTime.now(), "Autor", chamadoId);
    }

    public List<ComentarioResponse> listarPorChamado(Long chamadoId) {
        return Collections.emptyList();
    }
}