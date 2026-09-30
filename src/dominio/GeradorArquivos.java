package dominio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeradorArquivos {

    public static void main(String[] args) {

        gerarDegenerado(25000);
        gerarBalanceado(25000);

        gerarDegenerado(50000);
        gerarBalanceado(50000);

        gerarDegenerado(75000);
        gerarBalanceado(75000);

        gerarDegenerado(100000);
        gerarBalanceado(100000);

        System.out.println("Arquivos gerados com sucesso!");
    }

    private static void gerarDegenerado(int quantidade) {

        String nomeArquivo =
                "entrada" + quantidade + "_degenerada.txt";

        try (BufferedWriter escritor =
                     Files.newBufferedWriter(Path.of(nomeArquivo))) {

            for (int i = 1; i <= quantidade; i++) {
                escreverContato(escritor, i);
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void gerarBalanceado(int quantidade) {

        String nomeArquivo =
                "entrada" + quantidade + "_balanceada.txt";

        try (BufferedWriter escritor =
                     Files.newBufferedWriter(Path.of(nomeArquivo))) {

            escreverBalanceado(escritor, 1, quantidade);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void escreverBalanceado(
            BufferedWriter escritor,
            int inicio,
            int fim) throws IOException {

        if (inicio > fim) {
            return;
        }

        int meio = (inicio + fim) / 2;

        escreverContato(escritor, meio);

        escreverBalanceado(escritor, inicio, meio - 1);
        escreverBalanceado(escritor, meio + 1, fim);
    }

    private static void escreverContato(
            BufferedWriter escritor,
            int numero) throws IOException {

        String nome =
                String.format("Contato%06d", numero);

        String telefone =
                String.format("279%08d", numero);

        escritor.write(nome + ";" + telefone);
        escritor.newLine();
    }
}