package jogo2DAsteroide;

import java.util.List;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class Recordes extends JDialog {

    private DefaultTableModel model;
    private JTable table;
    private JComboBox<String> comboBoxNivel;

    public Recordes(JFrame parent) {
        super(parent, "Classificação dos Jogadores", true);

        // Painel superior para filtros
        JPanel painelFiltro = new JPanel();
        painelFiltro.setLayout(new FlowLayout(FlowLayout.LEFT));

        // ComboBox para selecionar o nível
        comboBoxNivel = new JComboBox<>(new String[]{"Todos", "Facil", "Medio", "Dificil"});
        painelFiltro.add(new JLabel("Filtrar por Nível:"));
        painelFiltro.add(comboBoxNivel);

        // Botão para aplicar o filtro
        JButton botaoFiltrar = new JButton("Filtrar");
        botaoFiltrar.addActionListener(e -> aplicarFiltro());
        painelFiltro.add(botaoFiltrar);


        // Criar tabela
        String[] colunas = {"Posição", "Nome", "Pontuação", "Nível"};
        this.model = new DefaultTableModel(colunas, 0); 

        // Cria a tabela com o modelo
        this.table = new JTable(this.model);
        table.setFillsViewportHeight(true);

        // Formatação da tabela
        formatarTabela();

        // Adiciona a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Configura o layout
        setLayout(new BorderLayout());
        add(painelFiltro, BorderLayout.NORTH); // Adiciona o painel de filtro no topo
        add(scrollPane, BorderLayout.CENTER);

        // Configura o tamanho e a posição da janela
        setSize(500, 400);
        setLocationRelativeTo(parent);

        // Atualiza os dados ao abrir o diálogo
        atualizarDados("Todos");
    }

    private void formatarTabela() {
        // Centralizar o texto nas células
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Definir cores e fontes
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25); // Altura das linhas
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(Color.GRAY);

        // Cabeçalho da tabela
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(0, 120, 215)); // Azul
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false); // Impedir reordenação das colunas
    }

    private void atualizarDados(String filtrarNivel) {
        // Limpa os dados antigos
        model.setRowCount(0);

        // Buscar no banco de dados
        List<Object[]> jogadores = DB_ProjetoII.getJogadores();

        // Adicionar os 10 melhores jogadores à tabela com a coluna de ordem
        int posicao = 1;
        for (Object[] jogador : jogadores) {

            String nivel = (String) jogador[2]; // Nível do jogador

            // Aplica o filtro
            if (filtrarNivel.equals("Todos") || nivel.equals(filtrarNivel)) {
                // Adiciona a posição como a primeira coluna
                Object[] linha = new Object[jogador.length + 1];
                linha[0] = posicao + "º"; // Posição (1º, 2º, 3º...)
                System.arraycopy(jogador, 0, linha, 1, jogador.length); // Copia os dados do jogador
                model.addRow(linha);

                posicao++;
                if (posicao > 10) { 
                    break;
            
                }   
            }
        }
    }

    private void aplicarFiltro() {
        // Obtém o nível selecionado no ComboBox
        String nivelSelecionado = (String) comboBoxNivel.getSelectedItem();

        // Atualiza a tabela com base no filtro
        atualizarDados(nivelSelecionado);
    }
}