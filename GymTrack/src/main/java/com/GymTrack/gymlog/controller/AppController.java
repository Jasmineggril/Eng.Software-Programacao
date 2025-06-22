package br.com.gymtrack.gymlog.controller;

import br.com.gymtrack.gymlog.dao.AlunoDAO;
import br.com.gymtrack.gymlog.dao.TreinoDAO;
import br.com.gymtrack.gymlog.model.Aluno;
import br.com.gymtrack.gymlog.view.TelaLogin;
import br.com.gymtrack.gymlog.view.TelaPrincipal;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AppController {

    private TelaLogin telaLogin;
    private AlunoDAO alunoDAO;
    private TreinoDAO treinoDAO;

    public AppController() {
        this.alunoDAO = new AlunoDAO();
        this.treinoDAO = new TreinoDAO();
    }

    public void iniciar() {
        alunoDAO.criarTabelaAluno();
        treinoDAO.criarTabelas();
        this.telaLogin = new TelaLogin();
        this.telaLogin.getBtnEntrar().addActionListener(e -> fazerLogin());
        this.telaLogin.getBtnCriarConta().addActionListener(e -> criarConta());
        this.telaLogin.setVisible(true);
    }

    private void fazerLogin() {
        String nome = JOptionPane.showInputDialog(telaLogin, "Digite seu nome de usuário:", "Login", JOptionPane.PLAIN_MESSAGE);
        if (nome != null && !nome.trim().isEmpty()) {
            Aluno aluno = alunoDAO.buscarAlunoPorNome(nome.trim());
            if (aluno != null) {
                telaLogin.dispose();
                abrirTelaPrincipal(aluno);
            } else {
                JOptionPane.showMessageDialog(telaLogin, "Usuário não encontrado. Crie uma conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void criarConta() {
        String nome = JOptionPane.showInputDialog(telaLogin, "Digite um nome para o novo usuário:", "Criar Conta", JOptionPane.PLAIN_MESSAGE);
        if (nome != null && !nome.trim().isEmpty()) {
            Aluno dadosNovoAluno = new Aluno(nome.trim(), LocalDate.now());
            Aluno alunoCriado = alunoDAO.adicionarAluno(dadosNovoAluno);

            if(alunoCriado != null) {
                JOptionPane.showMessageDialog(telaLogin, "Conta criada com sucesso! Adicionando treinos iniciais...", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                adicionarTreinosAleatorios(alunoCriado.getId());
                JOptionPane.showMessageDialog(telaLogin, "Tudo pronto! Agora você pode entrar.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(telaLogin, "Este nome de usuário já existe ou ocorreu um erro.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void adicionarTreinosAleatorios(int idNovoAluno) {
        List<String> nomesDeTreino = Arrays.asList(
                "Treino de Força - Superior", "Foco em Pernas e Glúteos", "Cardio Intenso (HIIT)",
                "Treino Funcional Completo", "Costas e Bíceps - Hipertrofia", "Peito e Tríceps - Definição"
        );

        Random random = new Random();
        int quantidadeDeTreinos = 3 + random.nextInt(3);

        for (int i = 0; i < quantidadeDeTreinos; i++) {
            String nomeAleatorio = nomesDeTreino.get(random.nextInt(nomesDeTreino.size()));
            int seriesAleatorias = 3 + random.nextInt(2); // Gera 3 ou 4 séries
            int repsAleatorias = 8 + random.nextInt(5);  // Gera de 8 a 12 repetições
            LocalDate dataAleatoria = LocalDate.now().minusDays(random.nextInt(30));
            treinoDAO.adicionarTreino(idNovoAluno, nomeAleatorio, seriesAleatorias, repsAleatorias, dataAleatoria);
        }
    }

    private void abrirTelaPrincipal(Aluno aluno) {
        TelaPrincipal view = new TelaPrincipal(aluno.getNome());
        new TreinoController(view, treinoDAO, aluno.getId());
        view.setVisible(true);
    }
}
