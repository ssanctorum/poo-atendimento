package br.edu.unifacisa.atendimento.model;

public class Medico extends Usuario {
    private String crm;
    private String especialidade;

    public Medico(String nome, String email, String senha, String crm, String especialidade) {
        super(nome, email, senha);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    @Override
    public Perfil getPerfil() {
        return Perfil.MEDICO;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + crm + " | " + especialidade;
    }
}
