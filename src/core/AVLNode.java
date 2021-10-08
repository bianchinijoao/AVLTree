package core;

public class AVLNode {
    private int altura;
    private int chave;
    private AVLNode esquerda;
    private AVLNode direita;

    public AVLNode(int pessoa) {
        this(pessoa, null, null);
    }

    public AVLNode(int numero, AVLNode esquerda, AVLNode direita) {
        this.chave = numero;
        this.esquerda = esquerda;
        this.direita = direita;
        this.altura = 0;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public AVLNode getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(AVLNode esquerda) {
        this.esquerda = esquerda;
    }

    public AVLNode getDireita() {
        return direita;
    }

    public void setDireita(AVLNode direita) {
        this.direita = direita;
    }
}
