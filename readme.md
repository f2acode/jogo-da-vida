# Descrição do Jogo da Vida :earth_americas:

John Horton Conway inventou o jogo da vida em 1970, quando ainda era um jovem matemático na Universidade de Cambridge, Inglaterra. Através de Martin Gardner e de sua coluna na revista Scientific American (outubro/1970 e janeiro/1971), o jogo foi popularizado entre os programadores do mundo inteiro e passou a ser um dos maiores consumidores de ciclos de CPU nos anos 70.

Este jogo simula a evolução de uma sociedade de organismos vivos, onde o objetivo é dada a configuração inicial de uma população, simular o movimento desta população ao longo de várias gerações, de acordo com certas regras para nascimento, sobrevivência e morte de cada indivíduo.

## Do tabuleiro e das regras

O jogo da vida é um jogo que pode ser simulado numa tela de computador. Ele é jogado em tabuleiro, idealmente infinito. Cada uma das células do tabuleiro pode estar em um de dois estados: viva ou morta. O estado do jogo é definido pelo estado das suas células.

Neste universo bi-dimensional e discreto, o tempo flui em passos discretos. A situação do universo em um dado instante é chamada de geração. Uma geração define como será a próxima e assim por diante, de acordo com algumas regras, numa evolução determinística.

As regras do jogo da vida que determinam a evolução de uma geração para a sua sucessora são simples e locais, isto é, a situação seguinte de uma célula só depende dela e de suas oito vizinhas.

A evolução das figuras no universo do jogo da vida lembra o crescimento de uma colônia de bactérias. Isto pode ser utilizado como uma analogia para facilitar a memorização das regras do jogo:

* Se a celula está viva e tem menos de dois vizinhos, ela morre de solidão. Se ela tem mais de três, ela morre por problemas devido à superpopulação.

* Uma célula morta rodeada por três células vivas resultará em uma célula viva na próxima geração.

* Uma célula viva, adjacente a duas ou três células vivas, permanece viva.

Uma aspecto importante a considerar é que todas as células vão de geração a geração ao mesmo tempo.

### O papel do jogador

Enquanto nos outros jogos o "jogador" tem participação importante no desenrolar do jogo, isto não acontece no jogo da vida. Aqui o jogador simplesmente define uma configuração inicial e daí mecanicamente, o jogo se desenvolve sozinho.

**Objetivo:** Cada grupo deverá criar um algoritmo em C, Java ou C# para simular a evolução de uma população para uma certa quantidade de gerações. O algoritmo deve explorar a capacidade de processamento paralelo que os computadores possuem. Ou seja, o algoritmo precisar se paralelo e utilizar threads para o seu processamento.

### Entrada

A entrada será através de um arquivo, composto pelos seguintes itens:

* Quantidade de linhas da matriz MxM, onde M é a sua quantidade de linhas (matriz quadrada). O tamanho máximo de M será 1000.

* M linhas com M caracteres em cada linha, onde cada caractere pode ser 0 ou 1 (a própria matriz).

### Funcionamento

Seu algoritmo deve calcular as gerações seguintes, até atingit a geração solicitada através da linha de comando.

Para utilizar processamento paralelo, o grupo deve executar a aplicação com 1 (execução sequencial), 2, 3 e 4 threads. Além disso, o grupo deve medir o tempo de processamento de cada execução.

### Tabela com resultados

| Tamanho da Matriz  | Número de gerações | Threads | Tempo |
| :-------------: |:-------------:| :-----:|:-----:|
| 1000 x 1000 | 100 | Sequencial <br> 2 <br> 4 | - <br> - <br> - |
| 1000 x 1000 | 200 | Sequencial <br> 2 <br> 4 | - <br> - <br> - |
| 1000 x 1000 | 500 | Sequencial <br> 2 <br> 4 | - <br> - <br> - |
| 1000 x 1000 | 1000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 1000 x 1000 | 2000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 1000 x 1000 | 3000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 2000 x 2000 | 100 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 2000 x 2000 | 200 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 2000 x 2000 | 500 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 2000 x 2000 | 1000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 2000 x 2000 | 2000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 2000 x 2000 | 3000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 3000 x 3000 | 100 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 3000 x 3000 | 200 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 3000 x 3000 | 500 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 3000 x 3000 | 1000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 3000 x 3000 | 2000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|
| 3000 x 3000 | 3000 | Sequencial <br> 2 <br> 4 | - <br> - <br> -|

### Saida

A saida do algoritmo é um arquivo contendo a configuração da população após todas as gerações terem ocorrido.