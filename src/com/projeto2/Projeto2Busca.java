package com.projeto2;

import com.projeto1.*;
import com.util.*;

/**
 * PROJETO 2 – Experimento com Sistemas de Busca
 *
 * Algoritmos comparados: - Busca Sequencial: O(n) - Busca Binaria: O(log n)
 * (requer array ordenado) - Busca em BST: O(log n) caso medio
 *
 * Metodologia: - 3 tamanhos: 1000, 10000, 100000 - 30 execucoes por tamanho -
 * Busca por um elemento que esta no meio do conjunto
 */
public class Projeto2Busca {

	private static final int[] TAMANHOS = { 1000, 10000, 100000 };
	private static final int REPETICOES = 30;

	public static void executar() {
		System.out.println("=".repeat(76));
		System.out.println("  PROJETO 2 - SISTEMAS DE BUSCA");
		System.out.println("  Complexidades teoricas:");
		System.out.println("    Sequencial:  O(n)");
		System.out.println("    Binaria:     O(log n)  (array ordenado)");
		System.out.println("    Busca BST:   O(log n) caso medio");
		System.out.println("=".repeat(76));

		for (int n : TAMANHOS) {
			System.out.println("\n  Tamanho n = " + n);
			Stats.separador();
			testarSequencial(n);
			testarBinaria(n);
			testarBST(n);
		}

		System.out.println();
	}

	private static void testarSequencial(int n) {
		long[] tempos = new long[REPETICOES];

		for (int r = 0; r < REPETICOES; r++) {
			int[] dados = Stats.gerarAleatorios(n);
			int alvo = dados[n / 2];

			long inicio = System.nanoTime();
			BuscaSequencial.buscar(dados, alvo);
			tempos[r] = System.nanoTime() - inicio;
		}
		Stats.imprimirResultadoNs("Sequencial", n, tempos);
	}

	private static void testarBinaria(int n) {
		long[] tempos = new long[REPETICOES];

		for (int r = 0; r < REPETICOES; r++) {
			int[] dados = Stats.gerarOrdenado(n);
			int alvo = dados[n / 2];

			long inicio = System.nanoTime();
			BuscaBinaria.buscar(dados, alvo);
			tempos[r] = System.nanoTime() - inicio;
		}
		Stats.imprimirResultadoNs("Binaria", n, tempos);
	}

	private static void testarBST(int n) {
		BSTTree bst = new BSTTree();
		long[] tempos = new long[REPETICOES];

		for (int r = 0; r < REPETICOES; r++) {
			int[] dados = Stats.gerarAleatorios(n);

			bst.limpar();
			for (int v : dados)
				bst.inserir(v);

			int alvo = dados[n / 2];

			long inicio = System.nanoTime();
			bst.buscar(alvo);
			tempos[r] = System.nanoTime() - inicio;
		}
		Stats.imprimirResultadoNs("Busca BST", n, tempos);
	}
}
