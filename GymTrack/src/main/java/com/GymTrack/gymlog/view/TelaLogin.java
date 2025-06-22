package br.com.gymtrack.gymlog.view;
import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {
    private JButton btnEntrar;
    private JButton btnCriarConta;

    public TelaLogin() {
        setTitle("GymTrack - Bem-vindo");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(28, 28, 28));

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/dumbbell.png"));
            Image image = icon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
            JLabel lblIcon = new JLabel(new ImageIcon(image));

            GridBagConstraints gbcIcon = new GridBagConstraints();
            gbcIcon.gridwidth = GridBagConstraints.REMAINDER;
            gbcIcon.insets = new Insets(10, 0, 10, 0);
            add(lblIcon, gbcIcon);
        } catch (Exception e) {
            System.err.println("Ícone não encontrado. Verifique a pasta resources/images.");
        }

        JLabel lblTitle = new JLabel("GymTrack", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSubtitle = new JLabel("A solução ideal para seus treinos", SwingConstants.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitle.setForeground(Color.LIGHT_GRAY);

        btnEntrar = new JButton("ENTRAR");
        btnCriarConta = new JButton("CRIAR CONTA");

        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        btnEntrar.setFont(btnFont);
        btnCriarConta.setFont(btnFont);
        Dimension btnSize = new Dimension(250, 50);
        btnEntrar.setPreferredSize(btnSize);
        btnCriarConta.setPreferredSize(btnSize);

        btnEntrar.setBackground(new Color(230, 126, 34));
        btnEntrar.setForeground(Color.WHITE);
        btnCriarConta.setBackground(new Color(52, 52, 52));
        btnCriarConta.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(20, 0, 5, 0);
        add(lblTitle, gbc);
        gbc.insets = new Insets(0, 0, 30, 0);
        add(lblSubtitle, gbc);
        gbc.insets = new Insets(10, 0, 10, 0);
        add(btnEntrar, gbc);
        add(btnCriarConta, gbc);
    }

    public JButton getBtnEntrar() { return btnEntrar; }
    public JButton getBtnCriarConta() { return btnCriarConta; }
}