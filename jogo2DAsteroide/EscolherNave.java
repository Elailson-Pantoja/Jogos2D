package jogo2DAsteroide;

import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class EscolherNave extends JPanel{
    //private String naveSelecionada;
    //private Ship ship;
    //private String nivel;
     //JPanel gamePanel;
     //private Ship ship;

    public EscolherNave(JFrame frame) {
        //this.gamePanel = gamePanel;
        //this.nivel = nivel;

        setLayout(new GridLayout(3, 1));
        
        JButton nave1lButton = criarBotaoComImagem("resources/images/nave-espacial.png");
        JButton nave2lButton = criarBotaoComImagem("resources/images/nave-espacial (1).png");
        JButton nave3lButton = criarBotaoComImagem("resources/images/nave-espacial (2).png");
        JButton nave4lButton = criarBotaoComImagem("resources/images/nave-espacial (3).png");
        JButton nave5lButton = criarBotaoComImagem(            "resources/images/nave-espacial (4).png");
        JButton nave6lButton = criarBotaoComImagem("resources/images/nave-espacial (5).png");
        JButton nave7lButton = criarBotaoComImagem("resources/images/nave-espacial (6).png");
        JButton nave8lButton = criarBotaoComImagem("resources/images/nave-espacial (7).png");
        JButton nave9lButton = criarBotaoComImagem("resources/images/nave-espacial (8).png");

        nave3lButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //frame.getContentPane().setLayout(new BorderLayout());
                frame.getContentPane().removeAll();
                //parentFrame.add(new Menu(parentFrame));
                frame.getContentPane().add(new Menu(frame));
                frame.revalidate();
                frame.repaint();
            }
                
        });

        add(nave1lButton);
        add(nave2lButton);
        add(nave3lButton);
        add(nave4lButton);
        add(nave5lButton);
        add(nave6lButton);
        add(nave7lButton);
        add(nave8lButton);
        add(nave9lButton);

        setVisible(true); 
    }

    private JButton criarBotaoComImagem(String caminhoDaImagem){
        int largura = 100;
        int altura = 100;
        JButton botao = new JButton();

        //imagem
        ImageIcon icon = new ImageIcon(caminhoDaImagem);
        Image img = icon.getImage().getScaledInstance(altura, largura, Image.SCALE_SMOOTH); //redimenciona a imagem
        botao.setIcon(new ImageIcon(img)); //define a imagem como ícone do botão 

        //botão
        //botao.setPreferredSize(new Dimension(largura, altura));
        botao.setBackground(Color.WHITE);
        botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        return botao;
    }

/*        public static void main(String[] args) {
            JFrame frame = new JFrame("Destruir Asteroides");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            
            // Exibe o menu inicial 
            //Menu EscolherNave = new EscolherNave(frame);
            frame.add(new EscolherNave(frame));
            frame.setVisible(true);
            
        }
*/
}
