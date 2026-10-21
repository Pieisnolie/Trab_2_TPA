package arvorebinaria;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparador) {
        super(comparador);
    }

    @Override
    public boolean adicionar(T novoValor) {

        raiz = adicionar(raiz, novoValor);

        return true;
    }

    private No<T> adicionar(No<T> no, T novoValor) {

        if (no == null) {
            return new No<>(novoValor);
        }

        int comparacao =
                comparador.compare(novoValor, no.getValor());

        if (comparacao < 0) {

            no.setEsquerda(
                    adicionar(no.getEsquerda(), novoValor)
            );

        } else {

            no.setDireita(
                    adicionar(no.getDireita(), novoValor)
            );
        }

        return balancear(no);
    }

    @Override
    public boolean remover(T valor) {

        if (pesquisar(valor) == null) {
            return false;
        }

        raiz = remover(raiz, valor);

        return true;
    }

    private No<T> remover(No<T> no, T valor) {

        if (no == null) {
            return null;
        }

        int comparacao =
                comparador.compare(valor, no.getValor());

        if (comparacao < 0) {

            no.setEsquerda(
                    remover(no.getEsquerda(), valor)
            );

        } else if (comparacao > 0) {

            no.setDireita(
                    remover(no.getDireita(), valor)
            );

        } else {

            // Nó sem filho à esquerda
            if (no.getEsquerda() == null) {
                return no.getDireita();
            }

            // Nó sem filho à direita
            if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            // Nó com dois filhos
            No<T> sucessor = menorNo(no.getDireita());

            no.setValor(sucessor.getValor());

            no.setDireita(
                    remover(no.getDireita(), sucessor.getValor())
            );
        }

        return balancear(no);
    }

    private No<T> menorNo(No<T> no) {

        No<T> atual = no;

        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }

        return atual;
    }

    private int alturaNo(No<T> no) {

        if (no == null) {
            return -1;
        }

        return no.getAltura();
    }

    private void atualizarAltura(No<T> no) {

        int alturaEsquerda = alturaNo(no.getEsquerda());
        int alturaDireita = alturaNo(no.getDireita());

        no.setAltura(1 + Math.max(alturaEsquerda, alturaDireita));
    }

    private int fatorBalanceamento(No<T> no) {

        if (no == null) {
            return 0;
        }

        return alturaNo(no.getEsquerda())
                - alturaNo(no.getDireita());
    }

    private No<T> rotacaoDireita(No<T> no) {

        No<T> novaRaiz = no.getEsquerda();
        No<T> temporario = novaRaiz.getDireita();

        novaRaiz.setDireita(no);
        no.setEsquerda(temporario);

        atualizarAltura(no);
        atualizarAltura(novaRaiz);

        return novaRaiz;
    }

    private No<T> rotacaoEsquerda(No<T> no) {

        No<T> novaRaiz = no.getDireita();
        No<T> temporario = novaRaiz.getEsquerda();

        novaRaiz.setEsquerda(no);
        no.setDireita(temporario);

        atualizarAltura(no);
        atualizarAltura(novaRaiz);

        return novaRaiz;
    }

    private No<T> balancear(No<T> no) {

        atualizarAltura(no);

        int fator = fatorBalanceamento(no);

        // Muito pesado para a esquerda
        if (fator > 1) {

            // Caso esquerda-direita
            if (fatorBalanceamento(no.getEsquerda()) < 0) {
                no.setEsquerda(
                        rotacaoEsquerda(no.getEsquerda())
                );
            }

            return rotacaoDireita(no);
        }

        // Muito pesado para a direita
        if (fator < -1) {

            // Caso direita-esquerda
            if (fatorBalanceamento(no.getDireita()) > 0) {
                no.setDireita(
                        rotacaoDireita(no.getDireita())
                );
            }

            return rotacaoEsquerda(no);
        }

        return no;
    }
}
