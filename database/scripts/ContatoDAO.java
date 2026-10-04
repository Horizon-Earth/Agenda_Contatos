package com.mycompany.agendacontatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void salvar(Contato contato) throws SQLException {
        String sql = """
                INSERT INTO contatos (nome, telefone, email)
                VALUES (?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, contato.getNome());
            comando.setString(2, contato.getTelefone());
            comando.setString(3, contato.getEmail());

            comando.executeUpdate();
        }
    }

    public List<Contato> listar() throws SQLException {
        String sql = "SELECT id, nome, telefone, email FROM contatos";
        List<Contato> contatos = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                Contato contato = new Contato(
                        resultado.getString("nome"),
                        resultado.getString("telefone"),
                        resultado.getString("email")
                );

                contato.setId(resultado.getInt("id"));
                contatos.add(contato);
            }
        }

        return contatos;
    }

    public void editar(Contato contato) throws SQLException {
        String sql = """
                UPDATE contatos
                SET nome = ?, telefone = ?, email = ?
                WHERE id = ?
                """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, contato.getNome());
            comando.setString(2, contato.getTelefone());
            comando.setString(3, contato.getEmail());
            comando.setInt(4, contato.getId());

            comando.executeUpdate();
        }
    }

    public void remover(Contato contato) throws SQLException {
        String sql = "DELETE FROM contatos WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, contato.getId());

            comando.executeUpdate();
        }
    }
}
