package br.com.gymtrack.gymlog.dao;

import br.com.gymtrack.gymlog.database.Database;
import br.com.gymtrack.gymlog.model.Treino;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreinoDAO {

    public void criarTabelas() {
        String sqlTreino = "CREATE TABLE IF NOT EXISTS treinos ("
                + " id_treino INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " id_aluno INTEGER NOT NULL,"
                + " nome TEXT NOT NULL,"
                + " series INTEGER,"
                + " repeticoes INTEGER,"
                + " data TEXT NOT NULL,"
                + " FOREIGN KEY (id_aluno) REFERENCES alunos(id_aluno)"
                + ");";

        try (Connection conn = Database.connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sqlTreino);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela de treinos: " + e.getMessage());
        }
    }

    public void adicionarTreino(int idAluno, String nomeTreino, int series, int repeticoes, LocalDate data) {
        String sql = "INSERT INTO treinos(id_aluno, nome, series, repeticoes, data) VALUES(?,?,?,?,?)";
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAluno);
            pstmt.setString(2, nomeTreino);
            pstmt.setInt(3, series);
            pstmt.setInt(4, repeticoes);
            pstmt.setString(5, data.toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar treino: " + e.getMessage());
        }
    }

    public List<Treino> listarTreinos(int idAluno) {
        List<Treino> treinos = new ArrayList<>();
        String sql = "SELECT * FROM treinos WHERE id_aluno = ?";
        try (Connection conn = Database.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAluno);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                treinos.add(new Treino(
                        rs.getInt("id_treino"),
                        rs.getString("nome"),
                        rs.getInt("series"),
                        rs.getInt("repeticoes"),
                        LocalDate.parse(rs.getString("data"))
                ));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar treinos: " + e.getMessage());
        }
        return treinos;
    }
}

