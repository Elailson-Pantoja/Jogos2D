package jogo2DAsteroide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelecionarNivel extends JFrame { 
    private JFrame parentFrame;

    public SelecionarNivel(JFrame frame) {
        this.parentFrame = frame;
        
        setTitle("Seleção de Nível");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton facilButton = new JButton("Fácil");
        facilButton.setBackground(Color.decode("#32cd32"));
        facilButton.setFont(new Font("Roboto", Font.CENTER_BASELINE, 16));

        JButton medioButton = new JButton("Médio");
        medioButton.setBackground(Color.decode("#fde910"));
        medioButton.setFont(new Font("Roboto", Font.CENTER_BASELINE, 16));

        JButton dificilButton = new JButton("Difícil");
        dificilButton.setBackground(Color.decode("#ff6347"));
        dificilButton.setFont(new Font("Roboto", Font.CENTER_BASELINE, 16));

        facilButton.addActionListener(new NivelSelecionado("Facil"));
        medioButton.addActionListener(new NivelSelecionado("Medio"));
        dificilButton.addActionListener(new NivelSelecionado("Dificil"));

        add(facilButton);
        add(medioButton);
        add(dificilButton);

        setVisible(true); 
    }

    private class NivelSelecionado implements ActionListener {
        private String nivel;

        public NivelSelecionado(String nivel) {
            this.nivel = nivel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.getContentPane().removeAll();
            parentFrame.add(new GamePanel(parentFrame, nivel));
            parentFrame.revalidate();
            parentFrame.repaint();
            dispose(); 
        }
    }
}
