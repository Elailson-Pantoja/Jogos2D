package jogo2DAsteroide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private JFrame parentFrame;
     //String naveSelecionada;
    //private JPanel gamePanel;

    public Menu(JFrame frame) {
        this.parentFrame = frame;
        //this.naveSelecionada = naveSelecionada;

        setLayout(new GridLayout(5, 1));
        setBackground(Color.BLACK); //fundo do menu

        parentFrame.setTitle("Jogo Asteroides");

        JButton startButton = new JButton("Iniciar Jogo");
        startButton.setBackground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton modelNaveButton = new JButton("Modelo de Nave"); 
        modelNaveButton.setBackground(Color.WHITE);
        modelNaveButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton recordButton = new JButton("Recordes");
        recordButton.setBackground(Color.WHITE);
        recordButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton missionButton = new JButton("Missões");
        missionButton.setBackground(Color.WHITE);
        missionButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(Color.WHITE);
        voltarButton.setFont(new Font("Arial", Font.BOLD, 20));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelecionarNivel(parentFrame);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.getContentPane().removeAll();

                MenuJogo menu = new MenuJogo(parentFrame);
                menu.setBounds(0, 0, parentFrame.getWidth(), parentFrame.getHeight()); // Garante que o menu ocupe todo o espaço
                
                parentFrame.getContentPane().add(menu);
                parentFrame.revalidate(); 
                parentFrame.repaint();
            }
        });

        modelNaveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                parentFrame.getContentPane().removeAll(); // Remove o painel atual
                parentFrame.add(new EscolherNave(frame));
                parentFrame.setVisible(true);
            
            }
        });

        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Abre a janela de recordes
                Recordes dialog = new Recordes(parentFrame);
                dialog.setVisible(true); // Exibe a janela
            }
        });

        missionButton.addActionListener(new ActionListener() {
            String nome = "Implementar a missões";
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, nome, "Tela de Missões", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //add(new JLabel("Destruir Asteroides", SwingConstants.CENTER));
        add(startButton);
        add(modelNaveButton);
        add(missionButton);
        add(recordButton);
        //add(exitButton);
        add(voltarButton);
   }
}