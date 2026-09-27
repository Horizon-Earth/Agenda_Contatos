package br.edu.principal;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Principal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            Agenda agenda = new Agenda();
            TelaAgenda tela = new TelaAgenda(agenda);
            tela.setVisible(true);
        });
    }
}
