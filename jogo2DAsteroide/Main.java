package jogo2DAsteroide;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
       /* JFrame frame = new JFrame("Destruir Asteroides");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Exibe o menu inicial 
       /* Menu menu = new Menu(frame);
        frame.add(menu);
        frame.setVisible(true);*/


        JFrame frame = new JFrame("Tela de Login");/* 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Login(frame));
        frame.setVisible(true);*/

        frame.setContentPane(new Login(frame));
        frame.add(new Login(frame));
        frame.setLocationRelativeTo(null);
        
    }
}
