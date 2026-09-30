package arvorebinaria;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

public class ArvoreBinaria<T> extends ArvoreBinariaBase<T> {

    private No<T> raiz;

    public ArvoreBinaria(Comparator<T> comparador) {
        super(comparador);
        this.raiz = null;
    }

    @Override
    public boolean adicionar(T novoValor) {

        No<T> novo = new No<>(novoValor);

        if (raiz == null) {
            raiz = novo;
            return true;
        }

        No<T> atual = raiz;

        while (true) {

            int comparacao =
                    comparador.compare(novoValor, atual.getValor());

            if (comparacao < 0) {

                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(novo);
                    return true;
                }

                atual = atual.getEsquerda();

            } else {

                if (atual.getDireita() == null) {
                    atual.setDireita(novo);
                    return true;
                }

                atual = atual.getDireita();
            }
        }
    }

    @Override
    public T pesquisar(T valor) {

        No<T> atual = raiz;

        while (atual != null) {

            int comparacao =
                    comparador.compare(valor, atual.getValor());

            if (comparacao == 0) {
                return atual.getValor();
            }

            if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        return null;
    }

    @Override
    public boolean remover(T valor) {

        No<T> atual = raiz;
        No<T> pai = null;

        while (atual != null) {

            int comparacao =
                    comparador.compare(valor, atual.getValor());

            if (comparacao == 0) {
                break;
            }

            pai = atual;

            if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        if (atual == null) {
            return false;
        }

        if (atual.getEsquerda() != null && atual.getDireita() != null) {

            No<T> paiSucessor = atual;
            No<T> sucessor = atual.getDireita();

            while (sucessor.getEsquerda() != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.getEsquerda();
            }

            atual.setValor(sucessor.getValor());

            if (paiSucessor == atual) {
                paiSucessor.setDireita(sucessor.getDireita());
            } else {
                paiSucessor.setEsquerda(sucessor.getDireita());
            }

            return true;
        }

        No<T> filho;

        if (atual.getEsquerda() != null) {
            filho = atual.getEsquerda();
        } else {
            filho = atual.getDireita();
        }

        if (pai == null) {
            raiz = filho;
        } else if (pai.getEsquerda() == atual) {
            pai.setEsquerda(filho);
        } else {
            pai.setDireita(filho);
        }

        return true;
    }

    @Override
    public int quantidadeNos() {
        return quantidadeNos(raiz);
    }

    private int quantidadeNos(No<T> no) {

        if (no == null) {
            return 0;
        }

        return 1 + quantidadeNos(no.getEsquerda()) + quantidadeNos(no.getDireita());
    }

    @Override
    public int altura() {
        return altura(raiz);
    }

    private int altura(No<T> no) {

        if (no == null) {
            return -1;
        }

        int alturaEsquerda = altura(no.getEsquerda());
        int alturaDireita = altura(no.getDireita());

        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    @Override
    public String caminharEmOrdem() {

        StringBuilder resultado = new StringBuilder("[");

        caminharEmOrdem(raiz, resultado);

        resultado.append("]");

        return resultado.toString();
    }

    private void caminharEmOrdem(
            No<T> no,
            StringBuilder resultado) {

        if (no == null) {
            return;
        }

        caminharEmOrdem(no.getEsquerda(), resultado);

        if (resultado.length() > 1) {
            resultado.append(",");
        }

        resultado.append(no.getValor());

        caminharEmOrdem(no.getDireita(), resultado);
    }

    @Override
    public String caminharEmNivel() {

        if (raiz == null) {
            return "[]";
        }

        StringBuilder resultado = new StringBuilder("[");
        Queue<No<T>> fila = new ArrayDeque<>();

        fila.add(raiz);

        while (!fila.isEmpty()) {

            int quantidadeNivel = fila.size();

            for (int i = 0; i < quantidadeNivel; i++) {

                No<T> atual = fila.remove();

                if (i > 0) {
                    resultado.append(",");
                }

                resultado.append(atual.getValor());

                if (atual.getEsquerda() != null) {
                    fila.add(atual.getEsquerda());
                }

                if (atual.getDireita() != null) {
                    fila.add(atual.getDireita());
                }
            }

            if (!fila.isEmpty()) {
                resultado.append("\n");
            }
        }

        resultado.append("]");

        return resultado.toString();
    }
}