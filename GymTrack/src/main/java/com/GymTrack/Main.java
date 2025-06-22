package br.com.gymtrack.gymlog;
import br.com.gymtrack.gymlog.controller.AppController;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("Component.arc", 999);
            UIManager.put("Button.arc", 999);
            UIManager.put("TextComponent.arc", 999);
            UIManager.put("Component.focusColor", new Color(230, 126, 34));
            UIManager.put("Table.selectionBackground", new Color(52, 73, 94));
            UIManager.put("Table.selectionForeground", Color.WHITE);
            UIManager.put("ProgressBar.foreground", new Color(230, 126, 34));
        } catch (Exception ex) {
            System.err.println("Falha ao iniciar o LookAndFeel.");
        }

        SwingUtilities.invokeLater(() -> {
            new AppController().iniciar();
        });
    }
}