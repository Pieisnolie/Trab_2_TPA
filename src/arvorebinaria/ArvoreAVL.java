package arvorebinaria;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparador) {
        // Aproveita o construtor da árvore binária,
        // que inicializa o comparador e a raiz.
        super(comparador);
    }

    @Override
    public boolean adicionar(T novoValor) {

        // Chama o metodo recursivo para inserir o valor.
        // A raiz recebe o retorno porque pode mudar após uma rotação.
        raiz = adicionar(raiz, novoValor);

        return true;
    }

    private No<T> adicionar(No<T> no, T novoValor) {

        // Quando encontra uma posição vazia, cria o novo nó.
        if (no == null) {
            return new No<>(novoValor);
        }

        // Compara o valor novo com o valor do nó atual.
        int comparacao =
                comparador.compare(novoValor, no.getValor());

        if (comparacao < 0) {

            // Se for menor, continua a inserção na subárvore esquerda.
            // O retorno atualiza a referência do filho esquerdo.
            no.setEsquerda(
                    adicionar(no.getEsquerda(), novoValor)
            );

        } else {

            // Se for maior ou igual, continua na subárvore direita.
            no.setDireita(
                    adicionar(no.getDireita(), novoValor)
            );
        }

        // Depois da inserção, verifica se a árvore precisa de rotações.
        return balancear(no);
    }

    @Override
    public boolean remover(T valor) {

        // Verifica primeiro se o valor existe na árvore.
        // Se não existir, não há necessidade de realizar a remoção.
        if (pesquisar(valor) == null) {
            return false;
        }

        // Realiza a remoção recursiva.
        // A raiz pode mudar caso seja necessário realizar rotações.
        raiz = remover(raiz, valor);

        return true;
    }

    private No<T> remover(No<T> no, T valor) {

        // Condição de parada: chegou a uma posição vazia.
        if (no == null) {
            return null;
        }

        // Compara o valor procurado com o nó atual.
        int comparacao =
                comparador.compare(valor, no.getValor());

        if (comparacao < 0) {

            // Se for menor, procura na subárvore esquerda.
            no.setEsquerda(
                    remover(no.getEsquerda(), valor)
            );

        } else if (comparacao > 0) {

            // Se for maior, procura na subárvore direita.
            no.setDireita(
                    remover(no.getDireita(), valor)
            );

        } else {

            // O valor foi encontrado.

            // CASO 1: Nó sem filho à esquerda.
            // Retorna o filho direito para ocupar sua posição.
            if (no.getEsquerda() == null) {
                return no.getDireita();
            }

            // CASO 2: Nó sem filho à direita.
            // Retorna o filho esquerdo para ocupar sua posição.
            if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            // CASO 3: Nó com dois filhos.
            // Encontra o menor valor da subárvore direita,
            // chamado de sucessor em ordem.
            No<T> sucessor = menorNo(no.getDireita());

            // Substitui o valor do nó pelo valor do sucessor.
            no.setValor(sucessor.getValor());

            // Remove o sucessor de sua posição original.
            no.setDireita(
                    remover(no.getDireita(), sucessor.getValor())
            );
        }

        // Após remover, atualiza as alturas e verifica
        // se é necessário realizar rotações para balancear a árvore.
        return balancear(no);
    }

    private No<T> menorNo(No<T> no) {

        // Começa pelo nó recebido.
        No<T> atual = no;

        // O menor valor de uma subárvore está no nó mais à esquerda.
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }

        return atual;
    }

    private int alturaNo(No<T> no) {

        // Considera que um nó inexistente possui altura -1.
        if (no == null) {
            return -1;
        }

        // Retorna a altura armazenada no próprio nó.
        return no.getAltura();
    }

    private void atualizarAltura(No<T> no) {

        // Obtém as alturas das subárvores esquerda e direita.
        int alturaEsquerda = alturaNo(no.getEsquerda());
        int alturaDireita = alturaNo(no.getDireita());

        // A altura do nó é 1 + a maior altura entre seus filhos.
        // O resultado é armazenado no próprio nó.
        no.setAltura(1 + Math.max(alturaEsquerda, alturaDireita));
    }

    private int fatorBalanceamento(No<T> no) {

        // Um nó inexistente não possui desequilíbrio.
        if (no == null) {
            return 0;
        }

        // Calcula a diferença entre as alturas das subárvores.
        // Positivo: mais pesada à esquerda.
        // Negativo: mais pesada à direita.
        // Zero: subárvores com a mesma altura.
        return alturaNo(no.getEsquerda())
                - alturaNo(no.getDireita());
    }

    private No<T> rotacaoDireita(No<T> no) {

        // O filho esquerdo se torna a nova raiz dessa subárvore.
        No<T> novaRaiz = no.getEsquerda();

        // Guarda o filho direito da nova raiz temporariamente,
        // pois ele precisará mudar de posição durante a rotação.
        No<T> temporario = novaRaiz.getDireita();

        // O nó original passa a ser filho direito da nova raiz.
        novaRaiz.setDireita(no);

        // O filho direito temporário passa a ser filho esquerdo
        // do nó original.
        no.setEsquerda(temporario);

        // Atualiza primeiro a altura do nó que desceu.
        atualizarAltura(no);

        // Depois atualiza a altura da nova raiz.
        atualizarAltura(novaRaiz);

        // Retorna a nova raiz da subárvore após a rotação.
        return novaRaiz;
    }

    private No<T> rotacaoEsquerda(No<T> no) {

        // O filho direito se torna a nova raiz dessa subárvore.
        No<T> novaRaiz = no.getDireita();

        // Guarda o filho esquerdo da nova raiz temporariamente.
        No<T> temporario = novaRaiz.getEsquerda();

        // O nó original passa a ser filho esquerdo da nova raiz.
        novaRaiz.setEsquerda(no);

        // O filho esquerdo temporário passa a ser filho direito
        // do nó original.
        no.setDireita(temporario);

        // Atualiza primeiro a altura do nó que desceu.
        atualizarAltura(no);

        // Depois atualiza a altura da nova raiz.
        atualizarAltura(novaRaiz);

        // Retorna a nova raiz da subárvore após a rotação.
        return novaRaiz;
    }

    private No<T> balancear(No<T> no) {

        // Atualiza a altura do nó antes de verificar seu equilíbrio.
        atualizarAltura(no);

        // Calcula a diferença entre as alturas das subárvores.
        int fator = fatorBalanceamento(no);

        // FATOR MAIOR QUE 1:
        // A subárvore esquerda está mais alta que a direita.
        if (fator > 1) {

            // Caso esquerda-direita (LR).
            // A subárvore esquerda está pesada para a direita.
            // Primeiro realiza uma rotação à esquerda no filho esquerdo.
            if (fatorBalanceamento(no.getEsquerda()) < 0) {
                no.setEsquerda(
                        rotacaoEsquerda(no.getEsquerda())
                );
            }

            // Realiza a rotação à direita para corrigir o desequilíbrio.
            // Também resolve diretamente o caso esquerda-esquerda (LL).
            return rotacaoDireita(no);
        }

        // FATOR MENOR QUE -1:
        // A subárvore direita está mais alta que a esquerda.
        if (fator < -1) {

            // Caso direita-esquerda (RL).
            // A subárvore direita está pesada para a esquerda.
            // Primeiro realiza uma rotação à direita no filho direito.
            if (fatorBalanceamento(no.getDireita()) > 0) {
                no.setDireita(
                        rotacaoDireita(no.getDireita())
                );
            }

            // Realiza a rotação à esquerda para corrigir o desequilíbrio.
            // Também resolve diretamente o caso direita-direita (RR).
            return rotacaoEsquerda(no);
        }

        // Se o fator estiver entre -1 e 1, o nó já está balanceado.
        // Retorna o próprio nó sem realizar rotações.
        return no;
    }
}
