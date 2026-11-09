package br.edu.unifacisa.atendimento.model;

public class Paciente extends Usuario {
    private String cpf;
    private String telefone;

    public Paciente(String nome, String email, String senha, String cpf, String telefone) {
        super(nome, email, senha);
        setCpf(cpf);
        this.telefone = telefone;
    }

    @Override
    public Perfil getPerfil() {
        return Perfil.PACIENTE;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF deve ter 11 dígitos.");
        }
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return super.toString() + " | CPF " + cpf + " | " + telefone;
    }
}
