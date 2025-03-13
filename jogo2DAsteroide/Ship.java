package jogo2DAsteroide;

import java.awt.*;

public class Ship {
    private int x, y;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int SPEED = 5;
    //private String nave [];
    private Image shipImage;
    //private String caminho = "";

    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
        //this.nave = nave;
        shipImage = Toolkit.getDefaultToolkit().getImage("resources/images/nave-espacial.png");
        //shipImage = Toolkit.getDefaultToolkit().getImage(caminho);
    }

    /*public Ship(int x, int y, String caminho) {
        this.x = x;
        this.y = y;
        //this.nave = nave;
        //shipImage = Toolkit.getDefaultToolkit().getImage("resources/images/nave-espacial.png");
        shipImage = Toolkit.getDefaultToolkit().getImage(caminho);
        System.out.println("caminho da Classe Ship: " + caminho);
        //this.nave = escolherNave(nave); 
    }*/

    public void update() {
        // Mantém a nave dentro da tela
        x = Math.max(0, Math.min(x, GamePanel.WIDTH - WIDTH));
    }

    public void moveLeft() {
        x -= SPEED;
    }

    public void moveRight() {
        x += SPEED;
    }

    public void moveLow() {
        y += SPEED;
    }

    public void moveUp() {
        y -= SPEED;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public void escolherNave(String naveSelecionada){
       //new Ship(x, y, naveSelecionada);
    }

    public void draw(Graphics g) {
        /*g.setColor(Color.BLUE);
        g.fillRect(x, y, WIDTH, HEIGHT);*/
        g.drawImage(shipImage, x, y, WIDTH, HEIGHT,null);
        //g.drawImage(null, x, y, WIDTH, HEIGHT, null)
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
