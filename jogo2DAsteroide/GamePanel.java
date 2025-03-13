package jogo2DAsteroide;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.net.URL;

    

public class GamePanel extends JPanel implements Runnable, KeyListener {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private Thread gameThread;
    private Ship ship;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Bala> balas;
    private Random random;
    private boolean running;
    private Clip laserSound;
    private int score = 0;
    private int counter = 0;
    private JFrame parentFrame;
    private String nivel;
    private Image asteroidImage;
    private Image gifFundo;

    // Flags para controlar movimento contínuo
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean movingLow = false;
    private boolean movingUp = false;


    public GamePanel(JFrame parentFrame, String nivel) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //setBackground(Color.BLACK);
        setFocusable(true);
        //requestFocusInWindow();  // Ensure focus for key events
        addKeyListener(this);

        setLayout(null);

        this.parentFrame = parentFrame;
        this.nivel = nivel;

        gifFundo = new ImageIcon("resources/images/a.gif").getImage();

        asteroidImage = Toolkit.getDefaultToolkit().getImage("resources/images/asteroide1.png");

        //String c = "resources/images/nave-espacial (5).png";

        //ship.escolherNave(c);

        ship = new Ship(WIDTH / 2, HEIGHT - 100);
        asteroids = new ArrayList<>();
        balas = new ArrayList<>();
        random = new Random();
        running = true;

         // Carrega o som de laser
        somDeTiro();

        // Inicia o loop do jogo
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    private void somDeTiro() {
        try {
            // Carrega o arquivo de som
            URL soundURL = getClass().getClassLoader().getResource("resources/laser.wav");
            if (soundURL == null) {
                throw new RuntimeException("Arquivo de som não encontrado: resources/laser.wav");
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);
            laserSound = AudioSystem.getClip(); //Cria um Clip para armazenar o som
            laserSound.open(audioInputStream); //Abre o arquivo de áudio no Clip
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar o som de laser!");
        }
    }
    
    private void playTiro() {
        if (laserSound != null) {
            laserSound.setFramePosition(0); // Reinicia o som antes de tocar
            laserSound.start(); // Reproduz o som
        }
    }
     

    @Override
    public void run() {
        if(nivel == "Facil"){
            while (running) {        	
                update();
                repaint();
                try {
                    Thread.sleep(17); // Controla a velocidade do jogo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(nivel == "Medio"){
            while (running) {        	
                update();
                repaint();
                try {
                    Thread.sleep(13); // Controla a velocidade do jogo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(nivel == "Dificil"){
            while (running) {        	
                update();
                repaint();
                try {
                    Thread.sleep(9); // Controla a velocidade do jogo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void update() {
        // Move a nave continuamente enquanto a tecla estiver pressionada
    	if (movingLeft) {
            ship.moveLeft();
        }
        if (movingRight) {
            ship.moveRight();
        }
        if (movingLow){
            ship.moveLow();
        }
        if (movingUp){
            ship.moveUp();
        }    

        ship.update();

        // Atualiza as balas
        for (Bala bala : new ArrayList<>(balas)) {
            bala.update();
            if (bala.BalaFora()) {
                balas.remove(bala); // Remove balas fora da tela
            }
        }
        
        spawnAsteroids();
        checkCollisions();
    }

    private void spawnAsteroids() {
        if (random.nextInt(100) < 5) { // Probabilidade de spawnar asteroides
            asteroids.add(new Asteroid(random.nextInt(WIDTH), 0));
        }
        for (Asteroid asteroid : new ArrayList<>(asteroids)) {
            asteroid.update();
            //remove os asteroid que foram desviados e passaram pela tela
            if (asteroid.getY() > HEIGHT) {
                asteroids.remove(asteroid);  
                score += 5;            
            }           
        }
    }

    private void checkCollisions() {
        Rectangle shipBounds = ship.getBounds();
        for (Asteroid asteroid : new ArrayList<>(asteroids)) {
            if (shipBounds.intersects(asteroid.getBounds())) {
                running = false;
                String nick_name = JOptionPane.showInputDialog("Digite Seu Nome de Jogador (Max: 3 letras)");
                 while (nick_name.length() > 3) {
                    if (nick_name.length() <= 3){
                        JOptionPane.showMessageDialog(this,  " Jogador: " + nick_name + "\nPontuação: " + score,"Game Over!",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        nick_name = JOptionPane.showInputDialog(null,"Atenção! Coloque o nome do jogador (Max: 3 letras)", "Atenção",JOptionPane.WARNING_MESSAGE);
                        //nick_name = JOptionPane.showInputDialog("Digite Seu Nome de Jogador (Max: 3 letras)");
                    }
                }

                JOptionPane.showMessageDialog(this,  " Jogador: " + nick_name + "\nPontuação: " + score,"Game Over!",JOptionPane.INFORMATION_MESSAGE );
                
                //Salvar dados no banco de dados
                DB_ProjetoII.salvarJogador(nick_name, score, nivel);

                voltarMenu();
                return;
            }
        }

        // Verifica colisões entre balas e asteroides
        for (Bala bala : new ArrayList<>(balas)) {
            Rectangle balaBounds = bala.getBounds();
            for (Asteroid asteroid : new ArrayList<>(asteroids)) {
                if (balaBounds.intersects(asteroid.getBounds())) {
                    balas.remove(bala);
                    asteroids.remove(asteroid);
                    counter += 1;
                    score += 10;
                    break; 
                }
            }
        }
    }

    //método para voltar ao menu principal
    private void voltarMenu(){
        parentFrame.getContentPane().removeAll();
        parentFrame.add(new Menu(parentFrame));
        parentFrame.revalidate();
        parentFrame.repaint();
    } 

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //desenhar o gif de fundo
        g.drawImage(gifFundo, 0, 0, getWidth(), getHeight(), this);

        ship.draw(g);
        //naveSelecionada.draw();

        for (Asteroid asteroid : asteroids) {
            asteroid.draw(g);
        }
        for (Bala bala : balas){
            bala.draw(g);
        }

        //exibir pontuação na tela
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.BOLD, 20));
        g.drawString("Pontuação: " + score, 20,40);

        //Contador de Asteroids Destruidos
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawImage(asteroidImage, 720, 10, 30, 40, parentFrame);
        g.drawString(""+counter,755,40);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println("Moving....: ");
        if (key == KeyEvent.VK_A) {
            movingLeft = true;  // Ativa o movimento para esquerda
        }
        if (key == KeyEvent.VK_D) {
            movingRight = true; // Ativa o movimento para direita
        }
        if (key == KeyEvent.VK_S){
            movingLow = true;
        }
        if (key == KeyEvent.VK_W){
            movingUp = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            //System.out.println("atirando");
            System.out.println("contando.." + counter);

            if(counter < 10){
                // Cria a bala no centro da nave
                int balaX = ship.getX() + Ship.getWidth() / 2 - Bala.getWidth() / 2;
                int balaY = ship.getY();
                balas.add(new Bala(balaX, balaY));
            }
            if (counter > 10 && counter <= 20){
                //Criar a bala no lado esquerdo da nava
                int balaX2 = ship.getX() - Ship.getWidth()/9 + Bala.getWidth();
                int balaY2 = ship.getY();
                balas.add(new Bala(balaX2, balaY2));

                //Criar a bala no lado direito da nava
                int balaX1 = ship.getX() + Ship.getWidth() - Bala.getWidth();
                int balaY1 = ship.getY();
                balas.add(new Bala(balaX1, balaY1));
            }
            if (counter > 20){
                // Cria a bala no centro da nave
                int balaX = ship.getX() + Ship.getWidth() / 2 - Bala.getWidth() / 2;
                int balaY = ship.getY();
                balas.add(new Bala(balaX, balaY));
                
                //Criar a bala no lado direito da nava
                int balaX1 = ship.getX() + Ship.getWidth() - Bala.getWidth();
                int balaY1 = ship.getY();
                balas.add(new Bala(balaX1, balaY1));

                //Criar a bala no lado esquerdo da nava
                int balaX2 = ship.getX() - Ship.getWidth()/9 + Bala.getWidth();
                int balaY2 = ship.getY();
                balas.add(new Bala(balaX2, balaY2));
            }
     
            // Reproduz o som de laser
            playTiro();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            movingLeft = false; // Para o movimento quando soltar a tecla
        }
        if (key == KeyEvent.VK_D) {
            movingRight = false; // Para o movimento quando soltar a tecla
        }
        if (key == KeyEvent.VK_S){
            movingLow = false;
        }
        if (key == KeyEvent.VK_W){
            movingUp = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Esse método não é necessário para movimento
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }    
}