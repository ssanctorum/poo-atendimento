package br.edu.unifacisa.atendimento.model;

public class Secretaria extends Usuario {

    public Secretaria(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    @Override
    public Perfil getPerfil() {
        return Perfil.SECRETARIA;
    }
}
