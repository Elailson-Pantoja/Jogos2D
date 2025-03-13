package jogo2DAsteroide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    private Image backgroundImage;
    private JFrame frame;

    public Login(JFrame frame) {
        this.frame = frame;

        frame.setTitle("Login");
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Carrega a imagem de fundo
        backgroundImage = new ImageIcon("resources/images/fundo_p.jpg").getImage();

        //JLabel user = new JLabel("abc");
        //JLabel pass = new JLabel("123");

        //Painel para login
        JPanel fundoLoginPanel = new JPanel();
        //fundoLoginPanel.setBackground(new Color(220,220,220)); 
        fundoLoginPanel.setBackground(new Color(248,248,255));
        //fundoLoginPanel.setBounds(80,50,240,150);
        fundoLoginPanel.setBounds(280,180,280,200);
        fundoLoginPanel.setLayout(null);

        //Definir possição da label e textField (Usuário)
        JLabel userLabel = new JLabel("Usuário:");
        //userLabel.setForeground(Color.decode("#"));
        //setBounds(x, y, largura, altura) - Define posição e tamanho 
        userLabel.setBounds(30,50,80,25);
        JTextField userField = new JTextField(15);
        userField.setBounds(90,50,130,25);

        //Definir possição da label e textField (senha)
        JLabel passwordLabel = new JLabel("Senha:");
        passwordLabel.setBounds(30,80,80,25);
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(90,80,130,25);

        //Definir posição dos botões
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(40,150,80,25);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(130, 150, 100, 25);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = new String(passwordField.getPassword());
                //String usua = user.getText();
                //String passw = pass.getText();

                //DB_ProjetoII.fazerLogin(usua, passw, Login.this);

                DB_ProjetoII.fazerLogin(user, password, Login.this);
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                cadastrarUsuario();
            }
        });
        fundoLoginPanel.add(userLabel);
        fundoLoginPanel.add(userField);
        fundoLoginPanel.add(passwordLabel);
        fundoLoginPanel.add(passwordField);
        fundoLoginPanel.add(loginButton);
        fundoLoginPanel.add(cadastrarButton);

        frame.add(fundoLoginPanel);
        frame.setVisible(true);

       // add(user);
        //add(pass);
       
    }

    public void abrirTelaMenu() {
        frame.setContentPane(new MenuJogo(frame));
        frame.revalidate();
        frame.repaint();
    }

    private void cadastrarUsuario() {
        frame.setContentPane(new Cadastro(frame));
        frame.revalidate();
        frame.repaint();
    }

    // Método para desenhar a imagem de fundo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    /* 
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tela de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 450);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Login(frame));
        frame.setVisible(true);
    }*/
}
