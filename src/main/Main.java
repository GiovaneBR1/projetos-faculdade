package main;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Grafo rede = new Grafo();

        System.out.println("Simulação de War Room: Análise de Rede");
        System.out.print("Informe a quantidade de conexões comprometidas: ");
        int qtdArestas = scanner.nextInt();

        System.out.println("\nDigite as conexões no formato: Origem Destino (ex: 1 2)");
        for (int i = 0; i < qtdArestas; i++) {
            System.out.print("Conexão " + (i + 1) + ": ");
            int origem = scanner.nextInt();
            int destino = scanner.nextInt();
            rede.adicionarAresta(origem, destino);
        }

        System.out.println("\n Processando Solução");
        
        
        List<Integer> melhorCobertura = encontrarMenorCobertura(rede);

     
        System.out.println("\n --- RESULTADO ---");
        System.out.println("Vértices escolhidos para monitoramento: " + melhorCobertura);
        System.out.println("Tamanho da cobertura (quantidade de dispositivos): " + melhorCobertura.size());
        
        System.out.println("\n--- Análise de Complexidade ---");
        System.out.println("Tempo: O(2^V * E) -> Onde V é o número de vértices e E o número de arestas.");
        System.out.println("Justificativa: Por ser um problema NP-Completo, utilizamos força bruta otimizada (bitmask). Testamos todas as 2^V combinações possíveis e, para cada uma, validamos as E arestas.");
        System.out.println("Espaço: O(V + E) -> Graças à escolha da Lista de Adjacência para representar a rede.");

        scanner.close();
    }

    
    //cobertura de vértices
    public static List<Integer> encontrarMenorCobertura(Grafo grafo) {
        List<Integer> vertices = new ArrayList<>(grafo.getVertices());
        List<Integer> melhorCobertura = new ArrayList<>(vertices); // Pior caso: monitorar todos

        int n = vertices.size();
        int totalCombinacoes = 1 << n; // Equivalente a 2^N

        // Testa todas as combinações possíveis
        for (int i = 0; i < totalCombinacoes; i++) {
            List<Integer> coberturaAtual = new ArrayList<>();
            
           
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    coberturaAtual.add(vertices.get(j));
                }
            }

            // Se essa combinação cobre todo o grafo E é menor que a melhor que já achamos
            if (verificaCobertura(grafo, coberturaAtual)) {
                if (coberturaAtual.size() < melhorCobertura.size()) {
                    melhorCobertura = new ArrayList<>(coberturaAtual);
                }
            }
        }
        return melhorCobertura;
    }

    // Verifica se os vértices escolhidos "tocam" em todas as arestas do grafo
    private static boolean verificaCobertura(Grafo grafo, List<Integer> cobertura) {
        Set<Integer> setCobertura = new HashSet<>(cobertura);
        
        for (Integer origem : grafo.getVertices()) {
            for (Integer destino : grafo.getAdjacencias().get(origem)) {
                // Se nenhum dos dois lados da aresta está na nossa cobertura, falhou.
                if (!setCobertura.contains(origem) && !setCobertura.contains(destino)) {
                    return false;
                }
            }
        }
        return true; 
    }
}