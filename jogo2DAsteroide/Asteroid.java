package jogo2DAsteroide;

import java.awt.*;

public class Asteroid {
    private int x, y;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 70;
    private static final int SPEED = 3;
    private Image asteroidImage;

    public Asteroid(int x, int y) {
        this.x = x;
        this.y = y;
        asteroidImage = Toolkit.getDefaultToolkit().getImage("resources/images/asteroide1.png");
    }

    public void update() {
        y += SPEED;
    }

    public void draw(Graphics g) {
        /*g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);*/
        g.drawImage(asteroidImage, x, y, WIDTH, HEIGHT,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getY() {
        return y;
    }
}