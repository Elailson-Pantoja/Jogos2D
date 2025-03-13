package jogo2DAsteroide;

import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


class DB_ProjetoII {
    private static final String URL = "jdbc:postgresql://localhost:5432/DB_ProjetoII";
    private static final String user = "postgres";
    private static final String password = "12345";


    public static void salvarJogador(String nome, int pontuacao, String nivel){
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Conexão bem-sucedida!"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        
        String sql = "INSERT INTO jogadores_jogo_asteroid (nome, pontuacao, nivel) VALUES (?,?,?)";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
            PreparedStatement psmt = conn.prepareStatement(sql)){     
            
            psmt.setString(1, nome);
            psmt.setInt(2, pontuacao);
            psmt.setString(3, nivel);
            psmt.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    } 

    public static List<Object[]> getJogadores(){
        List<Object[]> jogadores = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Conexão bem-sucedida!"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return jogadores;
        }

        String sql = "SELECT nome, pontuacao, nivel FROM jogadores_jogo_asteroid ORDER BY pontuacao DESC LIMIT 10";

        try(Connection conn = DriverManager.getConnection(URL, user, password);
            PreparedStatement psmt = conn.prepareStatement(sql)){
            ResultSet rs = psmt.executeQuery();

            //Processar o ResultSet
            while (rs.next()){
                //int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int pontuacao = rs.getInt("pontuacao");
                String nivel = rs.getString("nivel");
                //String dataRegistro = rs.getTime("Data de Registro: " + dataRegistro);

                // Exibir os dados no console
                //System.out.println("ID: " + id);
                System.out.println("Nome: " + nome);
                System.out.println("Pontuação: " + pontuacao);
                System.out.println("Nível: " + nivel);
                //System.out.println("Data de Registro: " + dataRegistro);
                System.out.println("-----------------------------");

                //Adicionar na lista
                jogadores.add(new Object[]{nome, pontuacao, nivel});

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return jogadores;
    }

    public static void fazerLogin (String user_name, String password_user, Login login){
        /*/
        user_name = "abc";
        password_user = "123";

        if(user_name == "abc" &&  password_user == "123"){
            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
            login.abrirTelaMenu(); // Abre o menu do jogo
        }  */     
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Conexão bem-sucedida!"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String sql = "SELECT * FROM login where user_name = ? AND password_user = ?";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
            PreparedStatement psmt = conn.prepareStatement(sql)){     
            
            psmt.setString(1, user_name);
            psmt.setString(2, password_user);

            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {

                String nome_usuario = rs.getString("user_name");
                int senha_usuario = rs.getInt("password_user");

                System.out.println("nome: " + nome_usuario);
                System.out.println("senha: " + senha_usuario);

                JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                login.abrirTelaMenu(); 
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
            }
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void cadastrarUsuario (String usuario, String senha, Cadastro cadastro){
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Conexão bem-sucedida!"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String sql = "INSERT INTO login (user_name, password_user) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
            PreparedStatement psmt = conn.prepareStatement(sql)){     
            psmt.setString(1, usuario);
            psmt.setString(2, senha);
            psmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            cadastro.voltarParaLogin();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
