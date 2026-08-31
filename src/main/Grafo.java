package main;

import java.util.*;

public class Grafo {
    // Lista de Adjacência
    private Map<Integer, List<Integer>> adjacencias;
    private int totalArestas;

    public Grafo() {
        this.adjacencias = new HashMap<>();
        this.totalArestas = 0;
    }

    // Método para conectar dois dispositivos na rede afetada
    public void adicionarAresta(int origem, int destino) {
        // Se o vértice ainda não existe no mapa, inicializa com uma lista vazia
        adjacencias.putIfAbsent(origem, new ArrayList<>());
        adjacencias.putIfAbsent(destino, new ArrayList<>());
        
        //Como o grafo é não direcionado, adicionamos nas duas listas
        adjacencias.get(origem).add(destino);
        adjacencias.get(destino).add(origem);
        
        totalArestas++;
    }

    // Retorna todos os vértices da rede
    public Set<Integer> getVertices() {
        return adjacencias.keySet();
    }

    public Map<Integer, List<Integer>> getAdjacencias() {
        return adjacencias;
    }
    
    public int getTotalArestas() {
        return totalArestas;
    }
}