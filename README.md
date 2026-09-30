# Sistema de Gerenciamento de Contatos

Projeto em **Java** para gerenciamento de contatos utilizando listas encadeadas e árvores binárias, permitindo comparar diferentes estruturas de dados e critérios de organização.

## Introdução

O objetivo deste trabalho é implementar e utilizar estruturas de **árvores binárias** em um sistema de gerenciamento de contatos, permitindo comparar seu funcionamento com as listas encadeadas desenvolvidas no trabalho anterior.

O projeto utiliza recursos de **Generics** e **Comparator**, permitindo que as estruturas armazenem diferentes tipos de objetos e utilizem diferentes critérios de indexação.

O sistema permite trabalhar com diferentes estruturas de dados para armazenar e pesquisar os contatos:

* Lista encadeada ordenada.
* Lista encadeada não ordenada.
* Árvore binária.

A utilização dessas estruturas permite analisar como a organização dos dados influencia a complexidade e o tempo de execução das operações.

## Funcionalidades

O sistema permite:

* Carregar contatos a partir de um arquivo `entrada.txt`.
* Adicionar novos contatos.
* Pesquisar contatos por nome.
* Pesquisar contatos por telefone.
* Remover contatos pelo telefone.
* Alterar nome e telefone de contatos existentes.
* Impedir o cadastro de telefones duplicados.
* Utilizar listas ordenadas ou não ordenadas.
* Utilizar uma árvore binária para armazenar os contatos.
* Utilizar diferentes critérios de comparação por nome ou telefone.
* Exibir o tempo de execução das operações de leitura, busca e remoção.
* Exibir a quantidade total de contatos ao encerrar o programa.

## Estrutura

O projeto utiliza estruturas de dados genéricas baseadas na interface `IColecao`.

São utilizadas diferentes estruturas para armazenar e manipular os contatos:

* **Lista encadeada ordenada** — mantém os elementos organizados de acordo com um `Comparator`.
* **Lista encadeada não ordenada** — adiciona os elementos sem manter uma ordem específica.
* **Árvore binária** — organiza os elementos de acordo com um `Comparator`.

A árvore binária implementada especializa a classe `ArvoreBinariaBase`, disponibilizada no repositório utilizado como referência para o trabalho.

A biblioteca de árvores utiliza **Generics**, permitindo que a estrutura trabalhe com diferentes tipos de objetos, e **Comparator**, permitindo definir diferentes critérios de indexação.

## Árvore Binária

A biblioteca implementada possui suporte às principais operações de uma árvore binária, incluindo:

* Inserção de elementos.
* Pesquisa de elementos.
* Remoção de elementos.
* Cálculo da altura da árvore.
* Caminhamento em ordem.
* Caminhamento em nível.

O caminhamento em ordem também é utilizado pela implementação do método `toString()` da árvore.

A estrutura recebe um `Comparator` em seu construtor, permitindo definir como os elementos serão comparados durante as operações.

Por exemplo, é possível utilizar diferentes árvores para os mesmos contatos, sendo uma organizada por **nome** e outra por **telefone**.

## AVL

O projeto também contempla a implementação de uma **árvore AVL**, que mantém seu balanceamento por meio de rotações após as operações de inserção e remoção.

O balanceamento da árvore busca evitar que sua estrutura se aproxime de uma lista encadeada, mantendo uma altura menor e, consequentemente, melhorando o desempenho das operações de busca, inserção e remoção.

## Dependências

O projeto utiliza apenas recursos padrão do Java e as classes desenvolvidas no próprio projeto.

É necessário ter:

* **Java JDK 11 ou superior**.

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

### 3. Compile o projeto

Compile o projeto utilizando o JDK configurado no ambiente.

Caso esteja utilizando uma IDE, basta importar o projeto e executar a classe `Main`.

## Arquivo de Entrada

Para utilizar a opção de carregamento de dados, o programa espera encontrar um arquivo chamado `entrada.txt` no diretório de execução.

Cada linha deve conter um **nome** e um **telefone**, separados por `;`.

### Exemplo de `entrada.txt`

```text
Gabriel;27999999999
Dominic;27988888888
Marcos;27977777777
Ryan;27966666666
Lucas;27955555555
Bruno;27944444444
Ana;27933333333
```

Linhas vazias são ignoradas e telefones duplicados não são cadastrados.

## Gerador de Arquivos

O projeto possui a classe `GeradorArquivos`, utilizada para gerar arquivos com grandes quantidades de contatos para testes de desempenho.

A quantidade de contatos é definida no método `main()` da classe `GeradorArquivos`. Por exemplo:

```text
gerarArquivo(50000);
```

A execução desse comando gera um arquivo chamado:

```text
entrada50000.txt
```

Os contatos são gerados automaticamente seguindo o formato:

```text
Contato000001;27900000001
Contato000002;27900000002
Contato000003;27900000003
```

Para utilizar o arquivo gerado no sistema principal, é necessário renomeá-lo para:

```text
entrada.txt
```

## Execução

Ao iniciar o programa, o usuário deve escolher qual estrutura deseja utilizar.

As opções disponíveis são:

```text
Lista ordenada? (S/N)
```

Caso seja escolhida a opção correspondente à utilização de árvores, o sistema utilizará a árvore binária para armazenar os contatos.

O menu principal apresenta as operações disponíveis:

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

Ao selecionar a opção `0`, o programa informa a quantidade total de contatos cadastrados antes de ser encerrado.

## Tecnologias

* **Java**
* **Generics**
* **Comparator**
* **Lista Encadeada**
* **Árvore Binária**
* **Árvore AVL**
* **HashSet**
* **Scanner**
* **BufferedReader**
* **Files**
* **Path**
* Comparadores para ordenação por **nome** e **telefone**

## Organização dos Dados

O sistema utiliza diferentes critérios para trabalhar com os contatos. Os contatos podem ser organizados e pesquisados utilizando o **nome** ou o **telefone** como referência.

Para a organização por nome, é utilizado um comparador que compara os nomes dos contatos.

Para a organização por telefone, é utilizado um comparador que compara os números de telefone.

Esses comparadores podem ser utilizados tanto pelas listas quanto pelas árvores, permitindo que as estruturas utilizem diferentes critérios de indexação.

## Complexidade e Desempenho

O uso de diferentes estruturas de dados permite analisar como a topologia da estrutura influencia a complexidade das operações.

Em uma árvore binária, o desempenho das operações depende diretamente de sua altura. Uma árvore mais equilibrada tende a apresentar operações mais eficientes, enquanto uma árvore degenerada pode se aproximar do comportamento de uma lista encadeada.

Os tempos de execução das operações podem ser utilizados para comparar o comportamento das listas e das árvores com diferentes quantidades de contatos.
