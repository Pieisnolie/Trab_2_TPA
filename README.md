# Sistema de Gerenciamento de Contatos

Projeto em **Java** para gerenciamento de contatos utilizando listas encadeadas, árvores binárias e árvores AVL, permitindo comparar diferentes estruturas de dados e critérios de organização.

## Introdução

O objetivo deste trabalho é implementar e utilizar estruturas de **árvores binárias e árvores AVL** em um sistema de gerenciamento de contatos, permitindo comparar seu funcionamento com as listas encadeadas desenvolvidas no trabalho anterior.

O projeto utiliza recursos de **Generics** e **Comparator**, permitindo que as estruturas armazenem diferentes tipos de objetos e utilizem diferentes critérios de indexação.

O sistema permite trabalhar com quatro opções de estruturas:

- Lista encadeada não ordenada.
- Lista encadeada ordenada.
- Árvore binária.
- Árvore AVL.

A utilização dessas estruturas permite analisar como a organização e o balanceamento dos dados influenciam a complexidade e o tempo de execução das operações.

## Integrantes

- Dominic Cassidy
- Gabriel Lacerda
- Marcos Paulo

## Funcionalidades

O sistema permite:

- Carregar contatos a partir de um arquivo `.txt` informado pelo usuário.
- Adicionar novos contatos.
- Pesquisar contatos por nome.
- Pesquisar contatos por telefone.
- Remover contatos pelo telefone.
- Alterar nome e telefone de contatos existentes.
- Impedir o cadastro de telefones duplicados.
- Utilizar lista ordenada ou não ordenada.
- Utilizar árvore binária.
- Utilizar árvore AVL.
- Utilizar diferentes critérios de comparação por nome ou telefone.
- Exibir o tempo de execução das operações de leitura, busca e remoção.
- Exibir a quantidade total de contatos ao encerrar o programa.

## Estrutura do Projeto

O projeto utiliza estruturas de dados genéricas baseadas na interface `IColecao`.

```text
src
├── arvorebinaria
│   ├── ArvoreAVL.java
│   ├── ArvoreBinaria.java
│   ├── ArvoreBinariaBase.java
│   └── No.java
│
├── colecao
│   └── IColecao.java
│
├── dominio
│   ├── ComparadorContatoPorNome.java
│   ├── ComparadorContatoPorTelefone.java
│   ├── Contato.java
│   ├── GeradorArquivos.java
│   └── Main.java
│
└── listaencadeada
    ├── ListaEncadeada.java
    └── No.java
```

As estruturas disponíveis são:

- **Lista encadeada ordenada** — mantém os elementos organizados de acordo com um `Comparator`.
- **Lista encadeada não ordenada** — adiciona os elementos sem manter uma ordem específica.
- **Árvore binária** — organiza os elementos de acordo com um `Comparator`.
- **Árvore AVL** — mantém os elementos organizados e realiza balanceamentos após inserções e remoções.

## Árvore Binária

A classe `ArvoreBinaria<T>` especializa a classe `ArvoreBinariaBase<T>` utilizada como base para o trabalho.

A árvore recebe um `Comparator<T>` em seu construtor, que determina como os elementos serão comparados.

A biblioteca implementa as principais operações de uma árvore binária:

- Inserção de elementos.
- Pesquisa de elementos.
- Remoção de elementos.
- Contagem dos nós.
- Cálculo da altura da árvore.
- Caminhamento em ordem.
- Caminhamento em nível.

O caminhamento em ordem também é utilizado pelo método `toString()` definido na classe `ArvoreBinariaBase`.

É possível utilizar árvores diferentes para armazenar os mesmos contatos utilizando critérios diferentes. No sistema são utilizadas:

- uma coleção indexada por **nome**;
- uma coleção indexada por **telefone**.

## Árvore AVL

A classe `ArvoreAVL<T>` herda de `ArvoreBinaria<T>` e acrescenta o balanceamento automático da árvore.

Os métodos `adicionar` e `remover` são sobrescritos para que, após as alterações na estrutura, seja verificado o fator de balanceamento dos nós.

Cada nó da árvore possui:

```text
valor
esquerda
direita
altura
```

A altura armazenada nos nós é utilizada para calcular o fator de balanceamento:

```text
altura da esquerda - altura da direita
```

Uma árvore AVL é considerada balanceada quando esse valor permanece entre:

```text
-1, 0 e 1
```

Quando ocorre um desequilíbrio, são utilizadas rotações para reorganizar a estrutura.

A implementação possui:

- Rotação simples para a direita.
- Rotação simples para a esquerda.
- Rotação esquerda-direita.
- Rotação direita-esquerda.

Por exemplo, a inserção:

```text
10
20
30
```

em uma árvore binária comum poderia resultar em:

```text
10
  \
   20
     \
      30
```

Na AVL, após o balanceamento, a estrutura fica:

```text
    20
   /  \
 10    30
```

Isso evita que a árvore fique degenerada e mantém sua altura menor.

## Comparadores

O projeto possui dois critérios de comparação para os contatos:

```text
ComparadorContatoPorNome
ComparadorContatoPorTelefone
```

O primeiro compara os contatos pelo nome e o segundo pelo telefone.

Esses comparadores podem ser utilizados pelas listas, pela árvore binária e pela árvore AVL.

O uso de `Comparator` permite utilizar a mesma estrutura com diferentes critérios de organização.

## Dependências

O projeto utiliza apenas recursos padrão do Java e as classes desenvolvidas no próprio projeto.

É necessário possuir:

- **Java JDK 11 ou superior**.

Não são necessárias bibliotecas externas.

## Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/Pieisnolie/TRAB_2_TPA.git
```

### 2. Entre na pasta do projeto

```bash
cd TRAB_2_TPA
```

### 3. Execute o projeto

O projeto pode ser aberto em uma IDE Java, como o **IntelliJ IDEA**.

Execute a classe:

```text
src/dominio/Main.java
```

## Arquivos de Entrada

Ao selecionar a opção de carregamento, o programa solicita o nome do arquivo `.txt` que será utilizado.

Exemplo:

```text
entrada25000_degenerada.txt
```

Cada linha deve possuir um **nome** e um **telefone**, separados por `;`.

Exemplo:

```text
Contato000001;27900000001
Contato000002;27900000002
Contato000003;27900000003
```

Linhas vazias são ignoradas e telefones duplicados não são cadastrados.

## Gerador de Arquivos

A classe:

```text
src/dominio/GeradorArquivos.java
```

é utilizada para gerar os arquivos necessários para os testes de desempenho.

Atualmente são utilizados os seguintes tamanhos:

```text
25.000 contatos
50.000 contatos
75.000 contatos
100.000 contatos
```

Para cada tamanho são gerados dois arquivos:

```text
entrada25000_degenerada.txt
entrada25000_balanceada.txt

entrada50000_degenerada.txt
entrada50000_balanceada.txt

entrada75000_degenerada.txt
entrada75000_balanceada.txt

entrada100000_degenerada.txt
entrada100000_balanceada.txt
```

### Arquivos para árvore degenerada

Nos arquivos degenerados, os telefones são gerados em ordem crescente.

Exemplo:

```text
1
2
3
4
5
```

Quando esses valores são inseridos em uma árvore binária comum, a estrutura tende a ficar:

```text
1
 \
  2
   \
    3
     \
      4
```

Dessa forma, é produzida uma árvore completamente degenerada.

Esses arquivos também são utilizados nos testes da **Árvore AVL**, permitindo observar como o balanceamento impede que a estrutura permaneça degenerada.

### Arquivos para árvore balanceada

Nos arquivos destinados à árvore binária balanceada, a geração começa pelo elemento localizado no meio do intervalo e repete o processo para as metades esquerda e direita.

Para sete elementos, por exemplo, a ordem seria:

```text
4
2
1
3
6
5
7
```

Resultando em:

```text
        4
      /   \
     2     6
    / \   / \
   1   3 5   7
```

Essa geração permite comparar o desempenho da árvore binária em diferentes topologias.

## Execução

Ao executar `Main.java`, o usuário deve escolher qual estrutura deseja utilizar.

O programa apresenta:

```text
Escolha a estrutura:
1 - Lista não ordenada
2 - Lista ordenada
3 - Árvore binária
4 - Árvore AVL
Escolha uma opção:
```

Depois da escolha, é apresentado o menu:

```text
===== MENU =====
1 - Carregar dados de arquivo
2 - Adicionar contato
3 - Pesquisar contato por nome
4 - Pesquisar contato por telefone
5 - Remover contato por telefone
6 - Alterar dados de contato
0 - Sair
```

Ao selecionar a opção `0`, o programa informa a quantidade total de contatos antes de encerrar.

## Organização dos Dados

O programa utiliza duas coleções para armazenar os contatos:

- uma indexada pelo nome;
- outra indexada pelo telefone.

O mesmo objeto `Contato` é utilizado nas duas estruturas.

Para a coleção por nome, é utilizado:

```text
ComparadorContatoPorNome
```

Para a coleção por telefone:

```text
ComparadorContatoPorTelefone
```

Dessa forma, o programa consegue realizar buscas utilizando os dois critérios.

## Complexidade

### Árvore Binária

O desempenho da árvore binária comum depende de sua altura.

Em uma árvore perfeitamente balanceada, as operações de inserção, pesquisa e remoção apresentam:

```text
O(log n)
```

No pior caso, em uma árvore completamente degenerada:

```text
O(n)
```

Por isso, a topologia da árvore influencia diretamente o desempenho das operações.

### Árvore AVL

A árvore AVL realiza balanceamentos para evitar que sua altura cresça como em uma árvore degenerada.

Como sua altura permanece proporcional a:

```text
O(log n)
```

as operações de inserção, pesquisa e remoção apresentam complexidade:

```text
O(log n)
```

no pior caso.

As rotações utilizadas durante o balanceamento modificam apenas uma quantidade constante de referências e possuem custo `O(1)`. O percurso realizado até o nó continua limitado pela altura da árvore.

### Quantidade de Nós

O método `quantidadeNos()` percorre todos os elementos da árvore.

Por isso, sua complexidade é:

```text
O(n)
```

independentemente da topologia da árvore.

## Testes de Desempenho

Os testes de desempenho medem:

- Tempo de leitura e montagem da estrutura.
- Busca por telefone.
- Busca por nome.
- Remoção por telefone.

Os testes permitem comparar:

```text
Lista encadeada
Árvore binária degenerada
Árvore binária balanceada
Árvore AVL
```

Para os testes da AVL são utilizados os mesmos arquivos com valores em ordem crescente utilizados anteriormente para produzir árvores binárias degeneradas.

A comparação permite observar o efeito do balanceamento automático no desempenho das operações.

## Tecnologias e Recursos Utilizados

- Java
- Generics
- Comparator
- IColecao
- Lista Encadeada
- Árvore Binária
- Árvore AVL
- ArrayDeque
- Queue
- HashSet
- Scanner
- BufferedReader
- Files
- Path
- IntelliJ IDEA
- Git
- GitHub

## Repositório

O repositório contém a implementação das listas encadeadas, árvores binárias e árvores AVL utilizadas no trabalho, além do sistema de gerenciamento de contatos, gerador de arquivos e código utilizado nos testes de desempenho.