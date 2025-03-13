package jogo2DLabirinto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Controller.java
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

import javax.swing.*;

public class Controller extends KeyAdapter {
    private Model model;
    private View view;
    private JFrame frame;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
               
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int posX = model.getPosX();
        int posY = model.getPosY();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                model.moverPara(posX, posY - 1);
                System.out.println("Cima");
                break;
            case KeyEvent.VK_DOWN:
                model.moverPara(posX, posY + 1);
                System.out.println("Baixo");
                break;
            case KeyEvent.VK_LEFT:
                model.moverPara(posX - 1, posY);
                System.out.println("Esquerda");
                break;
            case KeyEvent.VK_RIGHT:
                model.moverPara(posX + 1, posY);
                System.out.println("Direita");
                break;
        }
        
        // Atualizar a tela após mover
        view.atualizarTela();
        

        // Se o jogador chegar à posição de saída
        if (model.venceu()) {
            System.out.println("Você venceu!");
            JOptionPane.showMessageDialog(view, "Você venceu!");
            //view.setVisible(false);
            frame.removeAll();
            frame.dispose();
            frame.add(new MenuLabirinto(frame));

        }else{
            System.out.println("Ainda não venceu\n"
                    + "x = " + posX + ""
                            + "\ny = " + posY);
        }
    }

//}



    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        JFrame frame = new JFrame("Labirinto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JButton botao = new JButton("ok");
        frame.add(botao);
        frame.setVisible(true);

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //System.out.println("estou");
                frame.setVisible(false);
                frame.remove(botao);
                frame.add(view);
                frame.addKeyListener(controller);
                frame.setVisible(true);

            }
        });
        /*frame.add(view);
        frame.addKeyListener(controller);*/
        

    }

}