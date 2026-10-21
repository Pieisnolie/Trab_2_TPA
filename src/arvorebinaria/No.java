package arvorebinaria;

public class No<T> {

    private T valor;
    private No<T> esquerda;
    private No<T> direita;
    private int altura;

    public No(T valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
        this.altura = 0;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public No<T> getDireita() {
        return direita;
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }

    public int getAltura() {return altura;}

    public void setAltura(int altura) {this.altura = altura;}
    }