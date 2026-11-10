package br.com.sistemas.chamados.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemas.chamados.dto.DashboardResponse;
import br.com.sistemas.chamados.entity.Prioridade;
import br.com.sistemas.chamados.entity.StatusChamado;
import br.com.sistemas.chamados.repository.ChamadoRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final ChamadoRepository repository;

    public DashboardController(ChamadoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public DashboardResponse obterDashboard() {
        long totais = repository.count();

        Map<String, Long> porStatus = Map.of(
                "ABERTO", repository.countByStatus(StatusChamado.ABERTO),
                "EM_ATENDIMENTO", repository.countByStatus(StatusChamado.EM_ATENDIMENTO),
                "FECHADO", repository.countByStatus(StatusChamado.FECHADO)
        );

        Map<String, Long> porPrioridade = Map.of(
                "ALTA", repository.countByPrioridade(Prioridade.ALTA),
                "CRITICA", repository.countByPrioridade(Prioridade.CRITICA)
        );

        return new DashboardResponse(totais, porStatus, porPrioridade);
    }
}