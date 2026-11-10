package br.com.sistemas.chamados.dto;

import java.util.Map;

public record DashboardResponse(
        long totalChamados,
        Map<String, Long> chamadosPorStatus,
        Map<String, Long> chamadosPorPrioridade
){}