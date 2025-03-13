package jogo2DLabirinto;

import javax.swing.*;

import jogo2DAsteroide.MenuJogo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLabirinto extends JPanel{

    private JFrame parentFrame;
    Model model = new Model();
    View view = new View(model);
    Controller controller = new Controller(model, view);
    
    public MenuLabirinto(JFrame frame) {
        this.parentFrame = frame;

        //parentFrame.setTitle("Labirinto");

        //this.parentFrame = frame;
        setLayout(new GridLayout(4, 1));
        setBackground(Color.BLACK); //fundo do menu

        JButton startButton = new JButton("Iniciar Jogo");
        startButton.setBackground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton exitButton = new JButton("Sair");
        exitButton.setBackground(Color.WHITE);
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton recordButton = new JButton("Recordes");
        recordButton.setBackground(Color.WHITE);
        recordButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton missionButton = new JButton("Missões");
        missionButton.setBackground(Color.WHITE);
        missionButton.setFont(new Font("Arial", Font.BOLD, 20));

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setBackground(Color.WHITE);
        voltarButton.setFont(new Font("Arial", Font.BOLD, 20));


        /*frame = new JFrame("Labirinto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);*/

        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //Redimensionar o jogo para caber no frame
               //dimensões do frame
               parentFrame.setSize(800,600);
               parentFrame.setLocationRelativeTo(null);
               parentFrame.getContentPane().removeAll(); // Remove o painel atual

                //Definir o tamanho do jogo para caber no frame
                view.setPreferredSize(parentFrame.getSize());

                parentFrame.getContentPane().add(view);
                parentFrame.addKeyListener(controller);
                parentFrame.requestFocusInWindow(); // garantir a Captura de eventos do teclado

                //atualizar o frame
                //parentFrame.revalidate();
                //parentFrame.repaint();
                parentFrame.setVisible(true);
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

        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                // Abre a janela de recordes
                //Recordes dialog = new Recordes(parentFrame);
                //dialog.setVisible(true); // Exibe a janela
                String nome = "Implementar a Recordes";

                JOptionPane.showMessageDialog(null, nome, "Tela de Missões", JOptionPane.INFORMATION_MESSAGE);

            }
        });

        missionButton.addActionListener(new ActionListener() {
            String nome = "Implementar a missões";
            @Override
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, nome, "Tela de Missões", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(startButton);
        add(missionButton);
        add(recordButton);
        add(voltarButton);
   }

}
