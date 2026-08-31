
# Análise Experimental: Estrutura, pesquisa e ordenação de Dados.

Repositório referente à avaliação prática bimestral da disciplina de **Estruturas, Pesquisa e Ordenação de Dados (2026/1)**, ministrada pelo Prof. MSc. Gabriel Passos de Jesus. Este projeto implementa, instrumenta e compara o desempenho de diferentes estruturas de dados e algoritmos clássicos.

---

##  Projetos Implementados

### 1. Árvores de Busca e Balanceamento
Implementação manual e análise comparativa entre as seguintes árvores (com validação das operações de Inserção, Busca e Remoção):
* **BST (Árvore Binária de Busca):** Comportamento O(log n) médio, com análise da degradação para O(n) no pior caso.
* **AVL:** Auto-balanceada por rotações, garantindo altura máxima rigorosa O(log n).
* **Rubro-Negra (LLRB):** Implementação focada em eficiência de inserção/remoção O(log n).
* **Bônus - Caixeiro Viajante:** Solução algorítmica utilizando a heurística do Vizinho Mais Próximo O(n²).

### 2. Algoritmos de Busca
Comparação baseada no número de comparações O(n) vs O(log n):
* Busca Sequencial
* Busca Binária
* Busca em Árvore (BST)

### 3. Benchmark de Ordenação
Análise de esforço computacional (comparações e movimentações) nos cenários de melhor, médio e pior caso:
* **Merge Sort:** Estável, O(n log n) garantido em todos os cenários.
* **Quick Sort:** O(n log n) médio, implementado com **pivô aleatório** para mitigar severamente as chances de cair no pior caso O(n²).

## Descobertas e Resultados

Durante a fase de testes experimentais, destacam-se os seguintes fenómenos computacionais:

*  **O Colapso da BST (StackOverflow):** Foi provado empiricamente que a árvore BST simples colapsa em cenários de Pior Caso. Ao receber determinado volume de dados ordenados, a estrutura degenerou para uma formação linear O(n), excedendo o limite de chamadas recursivas da linguagem Java e resultando em *StackOverflowError*. A AVL e a LLRB mantiveram-se estáveis.
*  **A Otimização do Quick Sort:** Teoricamente, vetores invertidos deveriam forçar o Quick Sort à sua degradação máxima O(n^2). Contudo, a nossa implementação da estratégia de **pivô aleatório** mitigou essa vulnerabilidade matemática, estabilizando o algoritmo num tempo muito veloz, beneficiando ainda das otimizações de previsão de desvios do processador.
*  **O Abismo da Busca:** A Busca Binária localizou valores num vetor de 100.000 posições numa fração de nanosegundos (média de 590 ns), enquanto a Busca Sequencial apresentou um custo computacional massivo (média de 26.117 ns), validando o poder prático do método de divisão e conquista.

---

##  Instruções de Execução

O sistema roda diretamente no console e foi estruturado em pacotes, utilizando boas práticas de organização e de código limpo.

1. Clone o repositório para a sua máquina local/IDE(requer java 11+)
2. Execute, no terminal, o script de automação fornecido no projeto, que compilará os arquivos da pasta `src` e executará o sistema:
```cmd
executar.bat
```
---![Video-Project](https://github.com/user-attachments/assets/8bdcff1e-55cd-4966-9396-718891cc8a7c)

