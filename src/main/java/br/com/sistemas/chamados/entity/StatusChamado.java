package br.com.sistemas.chamados.entity;

public enum StatusChamado {
    ABERTO("Aberto"),
    EM_ATENDIMENTO("Em Atendimento"),
    AGUARDANDO_CLIENTE("Aguardando Cliente"),
    AGUARDANDO_APROVACAO("Aguardando Aprovação"),
    SUSPENSO("Suspenso"),
    CANCELADO("Cancelado"),
    FECHADO("Fechado");

    private final String descricao;

    StatusChamado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}