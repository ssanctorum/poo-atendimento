package br.edu.unifacisa.atendimento.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoBD {

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        try (InputStream in = ConexaoBD.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new SQLException("Arquivo db.properties não encontrado.");
            }
            props.load(in);
        } catch (IOException e) {
            throw new SQLException("Erro ao ler db.properties.", e);
        }
        return DriverManager.getConnection(
                props.getProperty("db.url"), props.getProperty("db.usuario"), props.getProperty("db.senha"));
    }
}
