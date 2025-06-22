package br.com.gymtrack.gymlog.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    private JTable tabelaTreinos;
    private DefaultTableModel tableModel;
    private JButton btnAdicionarTreino;
    private JButton btnAtualizar;

    public TelaPrincipal(String nomeAluno) {
        setTitle("GymTrack");
        try {
            setIconImage(new ImageIcon(getClass().getResource("/images/dumbbell.png")).getImage());
        } catch (Exception e) {
            System.err.println("Ícone não encontrado.");
        }
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents(nomeAluno);
    }

    private void initComponents(String nomeAluno) {
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitle = new JLabel("Treinos de " + nomeAluno, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome do Treino", "Séries", "Reps", "Data"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaTreinos = new JTable(tableModel);
        tabelaTreinos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaTreinos.setRowHeight(25);

        tabelaTreinos.getColumnModel().getColumn(0).setMaxWidth(50);
        tabelaTreinos.getColumnModel().getColumn(2).setMaxWidth(80);
        tabelaTreinos.getColumnModel().getColumn(3).setMaxWidth(80);
        tabelaTreinos.getColumnModel().getColumn(4).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(tabelaTreinos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdicionarTreino = new JButton("Adicionar Novo Treino");
        btnAtualizar = new JButton("Atualizar Lista");
        btnAdicionarTreino.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        panelBotoes.add(btnAdicionarTreino);
        panelBotoes.add(btnAtualizar);
        add(panelBotoes, BorderLayout.SOUTH);
    }

    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAdicionarTreino() { return btnAdicionarTreino; }
    public JButton getBtnAtualizar() { return btnAtualizar; }
}