package br.edu.unifacisa.atendimento;

import br.edu.unifacisa.atendimento.db.ConexaoBD;
import br.edu.unifacisa.atendimento.model.Consulta;
import br.edu.unifacisa.atendimento.model.Medico;
import br.edu.unifacisa.atendimento.model.Paciente;
import br.edu.unifacisa.atendimento.model.Perfil;
import br.edu.unifacisa.atendimento.model.Secretaria;
import br.edu.unifacisa.atendimento.model.StatusConsulta;
import br.edu.unifacisa.atendimento.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            new Secretaria("Teste", "teste@clinica.com", "123");
        } catch (IllegalArgumentException e) {
            System.out.println("Validação: " + e.getMessage());
        }

        try (Connection con = ConexaoBD.getConnection()) {
            System.out.println("Conexão realizada com sucesso!");
            Map<Integer, Usuario> usuarios = lerUsuarios(con);
            List<Consulta> consultas = lerConsultas(con, usuarios);

            System.out.println("\n--- Usuários ---");
            for (Usuario u : usuarios.values()) {
                System.out.println(u.getPerfil() + ": " + u);
            }
            System.out.println("\n--- Consultas ---");
            consultas.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ou ler o banco: " + e.getMessage());
        }
    }

    private static Map<Integer, Usuario> lerUsuarios(Connection con) throws SQLException {
        Map<Integer, Usuario> usuarios = new LinkedHashMap<>();
        String sql = "SELECT id, nome, email, senha, perfil, crm, especialidade, cpf, telefone, criado_em FROM usuarios";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                Usuario u = switch (Perfil.deTexto(rs.getString("perfil"))) {
                    case MEDICO -> new Medico(nome, email, senha, rs.getString("crm"), rs.getString("especialidade"));
                    case SECRETARIA -> new Secretaria(nome, email, senha);
                    case PACIENTE -> new Paciente(nome, email, senha, rs.getString("cpf"), rs.getString("telefone"));
                };
                u.setId(rs.getInt("id"));
                u.setCriadoEm(rs.getTimestamp("criado_em").toLocalDateTime());
                usuarios.put(u.getId(), u);
            }
        }
        return usuarios;
    }

    private static List<Consulta> lerConsultas(Connection con, Map<Integer, Usuario> usuarios) throws SQLException {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT id, paciente_id, medico_id, data_hora, status, observacoes FROM consultas";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta c = new Consulta(
                        (Paciente) usuarios.get(rs.getInt("paciente_id")),
                        (Medico) usuarios.get(rs.getInt("medico_id")),
                        rs.getTimestamp("data_hora").toLocalDateTime());
                c.setId(rs.getInt("id"));
                c.setStatus(StatusConsulta.deTexto(rs.getString("status")));
                c.setObservacoes(rs.getString("observacoes"));
                consultas.add(c);
            }
        }
        return consultas;
    }
}
