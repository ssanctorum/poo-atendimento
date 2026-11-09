package br.edu.unifacisa.atendimento.model;

public enum StatusConsulta {
    AGENDADA, REALIZADA, CANCELADA;

    public String getValorBanco() {
        return name().toLowerCase();
    }

    public static StatusConsulta deTexto(String texto) {
        return valueOf(texto.toUpperCase());
    }
}
