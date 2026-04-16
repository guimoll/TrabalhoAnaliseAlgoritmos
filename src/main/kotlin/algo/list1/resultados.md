# Lista 1: Resultados

## Questão 1: Inserção ao final

**Parte 1:** 20 testes, 100.000 inserções por teste

ArrayList: 2.141405 ms

LinkedList: 2.324500 ms

Mais rápida: ArrayList (1.09×)

**Parte 2:** Impacto da capacidade inicial do ArrayList (10 testes, 100.000 inserções por teste)

initialCapacity = 10: 3.000390 ms

initialCapacity = 1.000: 1.831150 ms

initialCapacity = 100.000: 1.409630 ms

Criar o ArrayList com capacidade suficiente elimina o custo de redimensionamento interno (alocar um array maior e copiar os elementos).

## Questão 2: Inserção em posição aleatória

20 testes, 10.000 inserções por teste

ArrayList: 2.834035 ms

LinkedList: 57.091 ms

Mais rápida: ArrayList (20.14×)

## Questão 3: Remoção de elementos

**Parte 1:** remover sempre o primeiro (20 testes)

ArrayList: 273.648550 ms

LinkedList: 902.265 µs

Mais rápida: LinkedList (303.29×)

**Parte 2:** remover sempre o último (20 testes)

ArrayList: 479.08 µs


LinkedList: 772.75 µs

Mais rápida: ArrayList (1.61×)

## Questão 4: Remoção em índice aleatório

20 testes, remoções até esvaziar

ArrayList: 145.400455 ms

LinkedList: 3.567744760 s

Mais rápida: ArrayList (24.54×)

## Questão 5: Acesso por índice aleatório

20 testes, 10.000 acessos por teste

ArrayList: 446.575 µs

LinkedList: 383.181425 ms

Mais rápida: ArrayList (858.04×)

## Questão 6: Análise Comparativa

**ArrayList foi mais vantajoso em:**

Inserção em posição aleatória: acesso O(1) ao índice permite deslocar elementos via `System.arraycopy`, que é altamente otimizado. A LinkedList precisa percorrer a lista até o índice antes de inserir.

Remoção do último elemento: remoção no final do array é O(1) sem deslocamento; na LinkedList tem a alocação de nós.

Acesso aleatório por índice: ArrayList é mais vantajoso pois a linkedList precisa ir de nó em nó até o indice

**LinkedList foi mais vantajoso em:**

Inserção ao final em sequência: diferença pequena, mas a LinkedList evita realocações periódicas do array interno.

Remoção do primeiro elemento: O(1) ao remover a cabeça da lista encadeada; ArrayList desloca todos os elementos restantes a cada remoção.

**Os resultados experimentais confirmaram a teoria esperada?**

Sim. As diferenças de desempenho batem com as complexidades teóricas:

O(1) vs O(n) explica a vantagem da LinkedList na remoção do primeiro elemento

O(1) de acesso direto explica a vantagem do ArrayList em inserção aleatória e acesso por índice.

A vantagem da LinkedList na inserção ao final foi pequena , pois o custo de realocação do ArrayList é baixo na prática.

**Fatores práticos que podem ter influenciado os resultados:**

JIT e warm-up da JVM: devido ao jit, pode demorar mais no início, justificando a necessidade de executar varias vezes e tirar a média

Overhead de alocação de objetos: cada nó da LinkedList é uma alocação separada no heap, o que pode aumentar o tempo de inserção e remoção.

`System.arraycopy` nativo: operações em bloco no ArrayList são executadas em código nativo, reduzindo o custo de deslocamento de elementos em comparação ao que a complexidade O(n) sugere isoladamente.
