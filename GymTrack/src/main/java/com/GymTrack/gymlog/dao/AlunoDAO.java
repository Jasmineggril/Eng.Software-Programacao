package br.com.gymtrack.gymlog.dao;

import br.com.gymtrack.gymlog.database.Database;
import br.com.gymtrack.gymlog.model.Aluno;
import java.sql.*;

public class AlunoDAO {

    public void criarTabelaAluno() {
        String sql = "CREATE TABLE IF NOT EXISTS alunos ("
                + " id_aluno INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " nome TEXT NOT NULL UNIQUE,"
                + " data_nascimento TEXT"
                + ");";
        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela de alunos: " + e.getMessage());
        }
    }


    public Aluno adicionarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos(nome, data_nascimento) VALUES(?,?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, aluno.getNome());
            pstmt.setString(2, aluno.getDataNascimento().toString());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        aluno.setId(rs.getInt(1)); // Define o ID gerado no objeto
                        return aluno; // Retorna o aluno completo
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar aluno: " + e.getMessage());
        }
        return null; // Retorna nulo em caso de falha
    }

    public Aluno buscarAlunoPorNome(String nome) {
        String sql = "SELECT * FROM alunos WHERE nome = ?";
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Aluno(rs.getInt("id_aluno"), rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return null;
    }
}