package jogo2DLabirinto;

// View.java
import javax.swing.*;
import java.awt.*;

public class View extends JPanel {
    private Model model;

    public View(Model model) {
        this.model = model;
        this.setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha o labirinto
        char[][] labirinto = model.getLabirinto();
        for (int y = 0; y < labirinto.length; y++) {
            for (int x = 0; x < labirinto[y].length; x++) {
                if (labirinto[y][x] == '#') {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(x * 40, y * 40, 40, 40);
            }
        }

        // Desenha o personagem
        g.setColor(Color.RED);
        g.fillOval(model.getPosX() * 40 + 10, model.getPosY() * 40 + 10, 20, 20);
    }
    
    // Método para atualizar a tela
    public void atualizarTela() {
        //revalidate();
        repaint();
    }
}

