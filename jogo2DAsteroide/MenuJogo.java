package jogo2DAsteroide;

import javax.swing.*;
import jogo2DLabirinto.MenuLabirinto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuJogo extends JPanel {
    private JFrame parentFrame;
    private Image backgroundImage;

    public MenuJogo(JFrame frame) {
        this.parentFrame = frame;

        parentFrame.setTitle("Menu do Jogo");
        parentFrame.setSize(800, 600);
        parentFrame.setLayout(null);

        // Carrega a imagem de fundo
        backgroundImage = new ImageIcon("resources/images/fundo_p.jpg").getImage();

        // Define layout manualmente
        setLayout(null);

        // Botão para o jogo Asteroids
        JButton asteroidsButton = new JButton();
        asteroidsButton.setFont(new Font("Arial", Font.BOLD, 18));
        asteroidsButton.setBackground(Color.WHITE);
        asteroidsButton.setBounds(100, 170, 220, 200); 
        ImageIcon iconAsteroid = new ImageIcon("resources/images/jogo_asteroids.png");
        Image imgasteroid = iconAsteroid.getImage().getScaledInstance(220, 200, Image.SCALE_SMOOTH);
        asteroidsButton.setIcon(new ImageIcon(imgasteroid));


        asteroidsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.getContentPane().setLayout(new BorderLayout());
                parentFrame.getContentPane().removeAll();
                parentFrame.getContentPane().add(new Menu(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        // Botão para o jogo Labirinto
        JButton labirintoButton = new JButton();
        labirintoButton.setFont(new Font("Arial", Font.BOLD, 18));
        labirintoButton.setBackground(Color.WHITE);
        labirintoButton.setBounds(450, 170, 220, 200); 
        ImageIcon iconLabirinto = new ImageIcon("resources/images/jogo_labirinto.png");
        Image imgLabirinto = iconLabirinto.getImage().getScaledInstance(220, 200, Image.SCALE_SMOOTH);
        labirintoButton.setIcon(new ImageIcon(imgLabirinto));

        labirintoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.getContentPane().setLayout(new BorderLayout());
                parentFrame.getContentPane().removeAll();
                //parentFrame.add(new MenuLabirinto(parentFrame));
                parentFrame.getContentPane().add(new MenuLabirinto(parentFrame));
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });

        JButton sairButton = new JButton("Sair do Jogo");
        sairButton.setFont(new Font("Arial", Font.BOLD, 18));
        sairButton.setBackground(Color.WHITE);
        sairButton.setBounds(290, 430, 200, 50); 
        /*ImageIcon iconSair = new ImageIcon("resources/images/saida.png");
        Image imgSair = iconSair.getImage().getScaledInstance(220, 200, Image.SCALE_SMOOTH);
        sairButton.setIcon(new ImageIcon(imgSair));*/
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String info = "Equipe Lambuja agradece por se divertir com a gente!\nDesenvolvido por:\nFabricio Farias \nElailson Pantoja \nAzenilton Pantoja \nFrancinaldo Farias \nSilvia Rodrigues\nDisciplinha: Projeto Integrado II";
                JOptionPane.showMessageDialog(null, info, "Informações do Jogo", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
            }
        });
          
        add(asteroidsButton);
        add(labirintoButton);
        add(sairButton);
    }

    // Método para desenhar a imagem de fundo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}



