package jogo2DLabirinto;

// Model.java
public class Model {
    private char[][] labirinto;
    private int posX, posY; // Posição do jogador

    public Model() {
        // Labirinto representado por uma matriz de caracteres
        labirinto = new char[][]{
            {'#', ' ', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', ' ', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'}
        };
        posX = 1;
        posY = 0;
    }

    public char[][] getLabirinto() {
        return labirinto;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void moverPara(int x, int y) {
        if (labirinto[y][x] != '#') {
            posX = x;
            posY = y;
        }else{
            //Só conta os movimentos.
        }
    }

    public boolean venceu() {
        return posX == 5 && posY == 5; // Posição de saída do labirinto
    }
}
