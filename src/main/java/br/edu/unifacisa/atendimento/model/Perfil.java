package br.edu.unifacisa.atendimento.model;

public enum Perfil {
    MEDICO, SECRETARIA, PACIENTE;

    public String getValorBanco() {
        return name().toLowerCase();
    }

    public static Perfil deTexto(String texto) {
        return valueOf(texto.toUpperCase());
    }
}
