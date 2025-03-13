package jogo2DAsteroide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cadastro extends JPanel {
    private JTextField userField;
    private JPasswordField passwordField;
    private JFrame frame;

    public Cadastro(JFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("Novo Usuário:");
        userField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Nova Senha:");
        passwordField = new JPasswordField(15);

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton voltarButton = new JButton("Voltar");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                String senha = new String(passwordField.getPassword());
            if (usuario.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }
                DB_ProjetoII.cadastrarUsuario(usuario, senha, Cadastro.this);
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaLogin();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        gbc.gridx = 1;
        add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(cadastrarButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(voltarButton, gbc);
    }

    void voltarParaLogin() {
        frame.setContentPane(new Login(frame));
        frame.add(new Login(frame));
        frame.revalidate();
        frame.repaint();
    }
}
