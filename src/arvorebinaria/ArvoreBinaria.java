package arvorebinaria;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;

public class ArvoreBinaria<T> extends ArvoreBinariaBase<T> {

    // Referência para o primeiro nó da árvore.
    protected No<T> raiz;

    public ArvoreBinaria(Comparator<T> comparador) {
        super(comparador);
        this.raiz = null;
    }

    @Override
    public boolean adicionar(T novoValor) {

        // Cria nó com o valor que será inserido.
        No<T> novo = new No<>(novoValor);

        // Se a árvore estiver vazia, o novo nó será a raiz.
        if (raiz == null) {
            raiz = novo;
            return true;
        }

        // Começa a procura pela posição de inserção a partir da raiz.
        No<T> atual = raiz;

        while (true) {

            // Compara o valor novo com o valor do nó atual.
            int comparacao = comparador.compare(novoValor, atual.getValor());

            if (comparacao < 0) {

                // Se for menor, deve ficar à esquerda.
                // Se não existir filho esquerdo, insere nessa posição.
                if (atual.getEsquerda() == null) {
                    atual.setEsquerda(novo);
                    return true;
                }

                // Se já existir um filho esquerdo, continua a procura por ele.
                atual = atual.getEsquerda();

            } else {

                // Se for maior ou igual, deve ficar à direita.
                // Se não existir filho direito, insere nessa posição.
                if (atual.getDireita() == null) {
                    atual.setDireita(novo);
                    return true;
                }

                // Se já existir um filho direito, continua a procura por ele.
                atual = atual.getDireita();
            }
        }
    }

    @Override
    public T pesquisar(T valor) {

        // A pesquisa começa pela raiz.
        No<T> atual = raiz;

        // Continua enquanto existir um nó para verificar.
        while (atual != null) {

            // Compara o valor procurado com o valor do nó atual.
            int comparacao =
                    comparador.compare(valor, atual.getValor());

            // Se forem iguais, encontrou o valor.
            if (comparacao == 0) {
                return atual.getValor();
            }

            // Se for menor, procura na subárvore esquerda.
            if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                // Se for maior, procura na subárvore direita.
                atual = atual.getDireita();
            }
        }

        // Retorna null quando o valor não existe na árvore.
        return null;
    }

    @Override
    public boolean remover(T valor) {

        No<T> atual = raiz;
        // Guarda o nó anterior ao atual, necessário para reorganizar a árvore.
        No<T> pai = null;

        // Procura o nó que contém o valor que será removido.
        while (atual != null) {

            int comparacao =
                    comparador.compare(valor, atual.getValor());

            // Interrompe a busca quando encontra o valor.
            if (comparacao == 0) {
                break;
            }

            // Guarda o nó atual como pai antes de avançar.
            pai = atual;

            if (comparacao < 0) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        // Se chegou a null, significa que o valor não foi encontrado.
        if (atual == null) {
            return false;
        }

        // CASO 1: O nó possui dois filhos.
        if (atual.getEsquerda() != null && atual.getDireita() != null) {

            // O sucessor é o menor valor da subárvore direita.
            // Para encontrá-lo, começa pelo filho direito e segue à esquerda.
            No<T> paiSucessor = atual;
            No<T> sucessor = atual.getDireita();

            while (sucessor.getEsquerda() != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.getEsquerda();
            }

            // Copia o valor do sucessor para o nó que será removido.
            atual.setValor(sucessor.getValor());

            // Retira o sucessor de sua posição original.
            // Como ele é o menor da subárvore direita, não possui filho esquerdo.
            // Seu possível filho direito ocupa sua posição.
            if (paiSucessor == atual) {
                paiSucessor.setDireita(sucessor.getDireita());
            } else {
                paiSucessor.setEsquerda(sucessor.getDireita());
            }

            return true;
        }

        // CASO 2: O nó possui apenas um filho ou nenhum.
        // Identifica qual filho deverá ocupar a posição do nó removido.
        No<T> filho;

        if (atual.getEsquerda() != null) {
            filho = atual.getEsquerda();
        } else {
            filho = atual.getDireita();
        }

        // Se pai for null, o nó removido é a própria raiz.
        if (pai == null) {
            raiz = filho;
        } else if (pai.getEsquerda() == atual) {
            // Se o nó removido era filho esquerdo, atualiza o ponteiro esquerdo.
            pai.setEsquerda(filho);
        } else {
            // Se era filho direito, atualiza o ponteiro direito.
            pai.setDireita(filho);
        }

        return true;
    }

    @Override
    public int quantidadeNos() {
        // Chama o metodo auxiliar passando a raiz da árvore.
        return quantidadeNos(raiz);
    }

    public int quantidadeNos(No<T> raiz) {

        // Uma árvore vazia possui zero nós.
        if (raiz == null) {
            return 0;
        }

        int quantidade = 0;

        // A fila permite percorrer a árvore por níveis (largura).
        Queue<No<T>> fila = new ArrayDeque<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {

            // Remove o primeiro nó da fila e contabiliza sua existência.
            No<T> atual = fila.remove();
            quantidade++;

            // Adiciona os filhos existentes à fila para serem visitados.
            if (atual.getEsquerda() != null) {
                fila.add(atual.getEsquerda());
            }

            if (atual.getDireita() != null) {
                fila.add(atual.getDireita());
            }
        }

        return quantidade;
    }

    @Override
    public int altura() {
        // Inicia o cálculo da altura pela raiz.
        return altura(raiz);
    }

    private int altura(No<T> no) {

        // Considera que uma árvore vazia possui altura -1.
        // Assim, uma árvore com apenas a raiz possui altura 0.
        if (no == null) {
            return -1;
        }

        // Calcula recursivamente a altura das duas subárvores.
        int alturaEsquerda = altura(no.getEsquerda());
        int alturaDireita = altura(no.getDireita());

        // A altura é o maior caminho entre a raiz e uma folha.
        // Soma 1 para contar o nível do nó atual.
        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    @Override
    public String caminharEmOrdem() {

        // StringBuilder permite montar a representação dos valores.
        StringBuilder resultado = new StringBuilder("[");

        // Inicia o caminhamento recursivo pela raiz.
        caminharEmOrdem(raiz, resultado);

        resultado.append("]");

        return resultado.toString();
    }

    private void caminharEmOrdem(
            No<T> no,
            StringBuilder resultado) {

        // Condição de parada da recursão.
        if (no == null) {
            return;
        }

        // Primeiro visita toda a subárvore esquerda.
        caminharEmOrdem(no.getEsquerda(), resultado);

        // Adiciona uma vírgula antes do valor, exceto no primeiro elemento.
        if (resultado.length() > 1) {
            resultado.append(",");
        }

        // Visita o nó atual.
        resultado.append(no.getValor());

        // Por último, visita toda a subárvore direita.
        // Em uma árvore de busca, esse caminhamento retorna os valores ordenados.
        caminharEmOrdem(no.getDireita(), resultado);
    }

    @Override
    public String caminharEmNivel() {

        // Verifica se a árvore está vazia.
        if (raiz == null) {
            return "[]";
        }

        StringBuilder resultado = new StringBuilder("[");

        // A fila garante que os nós sejam visitados por nível,
        // começando pela raiz.
        Queue<No<T>> fila = new ArrayDeque<>();

        fila.add(raiz);

        while (!fila.isEmpty()) {

            // Guarda quantos nós existem no nível atual.
            // Isso permite separar os níveis na representação.
            int quantidadeNivel = fila.size();

            for (int i = 0; i < quantidadeNivel; i++) {

                // Remove o próximo nó da fila.
                No<T> atual = fila.remove();

                // Se não for o primeiro nó do nível, adiciona uma vírgula.
                if (i > 0) {
                    resultado.append(",");
                }

                // Adiciona o valor do nó atual ao resultado.
                resultado.append(atual.getValor());

                // Adiciona os filhos à fila para serem visitados depois.
                if (atual.getEsquerda() != null) {
                    fila.add(atual.getEsquerda());
                }

                if (atual.getDireita() != null) {
                    fila.add(atual.getDireita());
                }
            }

            // Quebra a linha quando existem outros níveis para visitar.
            if (!fila.isEmpty()) {
                resultado.append("\n");
            }
        }

        resultado.append("]");

        return resultado.toString();
    }
}
