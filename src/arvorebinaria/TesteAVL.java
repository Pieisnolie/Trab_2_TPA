package arvorebinaria;

public class TesteAVL {

    public static void main(String[] args) {

        ArvoreAVL<Integer> arvore =
                new ArvoreAVL<>((a, b) -> a.compareTo(b));

        arvore.adicionar(20);
        arvore.adicionar(10);
        arvore.adicionar(30);
        arvore.adicionar(40);

        System.out.println("Antes:");
        System.out.println(arvore.caminharEmNivel());

        arvore.remover(10);

        System.out.println("Depois:");
        System.out.println(arvore.caminharEmNivel());

        System.out.println("Altura: " + arvore.altura());
    }
}