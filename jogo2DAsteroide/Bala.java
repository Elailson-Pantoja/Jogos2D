package jogo2DAsteroide;

import java.awt.*;

public class Bala {
    private int x, y;
    private static final int WIDTH = 3;
    private static final int HEIGHT = 10;
    private static final int SPEED = 5;
    //private Image balaImage;

    public Bala (int x, int y){
        this.x = x;
        this.y = y;
        //balaImage = Toolkit.getDefaultToolkit().getImage("resources/images/bala.png");
    }
  
    public void update() {
       y -= SPEED;
    }

    public void draw(Graphics g){
        //g.drawImage(balaImage,x, y, WIDTH, HEIGHT, null);
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    public boolean BalaFora(){
        return y + HEIGHT < 0;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

}

