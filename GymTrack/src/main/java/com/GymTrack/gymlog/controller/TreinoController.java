package br.com.gymtrack.gymlog.controller;

import br.com.gymtrack.gymlog.dao.TreinoDAO;
import br.com.gymtrack.gymlog.model.Treino;
import br.com.gymtrack.gymlog.view.TelaPrincipal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class TreinoController {
    private TelaPrincipal view;
    private TreinoDAO dao;
    private int idAlunoLogado;

    public TreinoController(TelaPrincipal view, TreinoDAO dao, int idAlunoLogado) {
        this.view = view;
        this.dao = dao;
        this.idAlunoLogado = idAlunoLogado;

        this.view.getBtnAtualizar().addActionListener(e -> carregarTreinos());
        this.view.getBtnAdicionarTreino().addActionListener(e -> adicionarNovoTreino());
        carregarTreinos();
    }

    private void adicionarNovoTreino() {
        String nomeTreino = JOptionPane.showInputDialog(view, "Digite o nome do novo treino:", "Novo Treino", JOptionPane.PLAIN_MESSAGE);
        if (nomeTreino != null && !nomeTreino.trim().isEmpty()) {
            try {
                String seriesStr = JOptionPane.showInputDialog(view, "Número de Séries:", "Novo Treino", JOptionPane.PLAIN_MESSAGE);
                int series = Integer.parseInt(seriesStr);

                String repsStr = JOptionPane.showInputDialog(view, "Número de Repetições:", "Novo Treino", JOptionPane.PLAIN_MESSAGE);
                int repeticoes = Integer.parseInt(repsStr);

                dao.adicionarTreino(idAlunoLogado, nomeTreino.trim(), series, repeticoes, LocalDate.now());
                JOptionPane.showMessageDialog(view, "Treino adicionado com sucesso!");
                carregarTreinos();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(view, "Por favor, digite um número válido para séries e repetições.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void carregarTreinos() {
        List<Treino> treinos = dao.listarTreinos(idAlunoLogado);
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        for (Treino treino : treinos) {
            model.addRow(new Object[]{
                    treino.getId(),
                    treino.getNome(),
                    treino.getSeries(),
                    treino.getRepeticoes(),
                    treino.getData().toString()
            });
        }
    }
}
