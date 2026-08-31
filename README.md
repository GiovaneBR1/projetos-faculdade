# Simulação de War Room com Grafos 

**Disciplina:** Estrutura, pesquisa e ordenação de dados

**Curso:** Análise e Desenvolvimento de Sistemas (5º Semestre)  

**Autor:** Giovane Canteri da Silva  

##  Contexto do Problema
Este projeto resolve o problema de otimização de recursos numa War Room que sofreu um ataque cibernético. A rede é representada através de um **Grafo Não Direcionado**. 

O objetivo algorítmico consiste em resolver o problema de **Cobertura de Vértices**: encontrar o menor conjunto de dispositivos (vértices) capazes de monitorizar todas as conexões comprometidas. Como este é um problema da classe **NP-Completo**, a solução foi desenvolvida com foco no rigor matemático e na otimização de recursos computacionais.

## Decisões de Arquitetura e Estrutura de Dados
Para representar a rede, optou-se pela utilização de uma **Lista de Adjacência** (implementada via `Map<Integer, List<Integer>>` no código). 

**Justificação:** Conforme abordado na teoria de grafos, a Matriz de Adjacência possui um custo de memória quadrático O(n^2), o que a torna ineficiente para sistemas reais e de grande escala. A Lista de Adjacência preserva apenas as conexões existentes, garantindo uma estrutura computacional otimizada para grafos esparsos e poupando memória.

## Análise de Complexidade
* **Complexidade de Tempo:** O(2^*E)	
  * **V** = Número de vértices | **E** = Número de arestas.
  * Por ser um problema NP-Completo, foi implementada uma solução baseada em força bruta otimizada através de manipulação de bits. O algoritmo testa todas as 2^V combinações possíveis e, para cada uma, valida as E arestas para garantir a cobertura total.
* **Complexidade de Espaço:** O(V + E)
  * A alocação de memória é eficiente graças à escolha da Lista de Adjacência.

## Como Executar
1. Certifique-se de ter o [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado.
2. Compile os ficheiros `.java` na pasta `src`:
  ```
  javac Main.java Grafo.java
  ```

Execute o programa principal:

  ```
  java Main
  ```

Insira os dados da rede de forma dinâmica pelo terminal.


## Testes e Validação
Abaixo estão duas simulações executadas pelo algoritmo:


Cenário 1: Rede em Estrela (Servidor Central)

Neste cenário, múltiplos dispositivos conectam-se a um único ponto central. O algoritmo detecta corretamente que apenas 1 dispositivo precisa de ser monitorado.


<img width="1498" height="588" alt="image" src="https://github.com/user-attachments/assets/38345966-263e-45c1-b8f7-cefa8d1b1dc1" />




Cenário 2: Rede em Teia (Alta Redundância)

Neste cenário, os servidores estão fortemente interligados. O algoritmo analisa as combinações e conclui o número exato de pontos necessários para cobrir toda a malha.
<img width="1549" height="647" alt="image" src="https://github.com/user-attachments/assets/8814cb98-886d-4473-a61a-48582b8918cf" />
