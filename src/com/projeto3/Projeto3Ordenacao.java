package com.projeto3;

import com.util.Stats;

/**
 * PROJETO 3 – Experimento com Algoritmos de Ordenacao
 *
 * Algoritmos: Merge Sort, Quick Sort
 *
 * Casos testados: Melhor caso -> array ja ordenado crescente (1..n) Caso medio
 * -> array aleatorio Pior caso -> array ordenado decrescente (n..1)
 *
 * Complexidades teoricas: Merge Sort: O(n log n) em todos os casos Quick Sort:
 * O(n log n) medio/melhor | O(n^2) pior caso (Com pivo aleatorio, o pior caso
 * pratico e muito raro)
 *
 * Metodologia: - 3 tamanhos: 1000, 10000, 100000 - 30 execucoes por caso
 */
public class Projeto3Ordenacao {

	private static final int[] TAMANHOS = { 1000, 10000, 100000 };
	private static final int REPETICOES = 30;

	public static void executar() {
		System.out.println("=".repeat(76));
		System.out.println("  PROJETO 3 - ALGORITMOS DE ORDENACAO");
		System.out.println("  Complexidades teoricas:");
		System.out.println("    Merge Sort:  O(n log n) em todos os casos");
		System.out.println("    Quick Sort:  O(n log n) medio  |  O(n^2) pior caso");
		System.out.println("=".repeat(76));

		System.out.println("\n-- Merge Sort --");
		for (int n : TAMANHOS) {
			System.out.println("\n  Tamanho n = " + n);
			Stats.separador();
			testar("MergeSort - Melhor caso", n, "ordenado", true);
			testar("MergeSort - Caso medio", n, "aleatorio", true);
			testar("MergeSort - Pior caso", n, "invertido", true);
		}

		System.out.println("\n-- Quick Sort --");
		for (int n : TAMANHOS) {
			System.out.println("\n  Tamanho n = " + n);
			Stats.separador();
			testar("QuickSort - Melhor caso", n, "ordenado", false);
			testar("QuickSort - Caso medio", n, "aleatorio", false);
			testar("QuickSort - Pior caso", n, "invertido", false);
		}

		System.out.println();
	}

	private static void testar(String nome, int n, String tipo, boolean mergeSort) {
		long[] tempos = new long[REPETICOES];

		for (int r = 0; r < REPETICOES; r++) {
			int[] base = gerarPorTipo(n, tipo);
			int[] arr = Stats.copiar(base);

			long inicio = System.nanoTime();
			if (mergeSort)
				MergeSort.ordenar(arr);
			else
				QuickSort.ordenar(arr);
			tempos[r] = System.nanoTime() - inicio;
		}
		Stats.imprimirResultadoNs(nome, n, tempos);
	}

	private static int[] gerarPorTipo(int n, String tipo) {
		switch (tipo) {
		case "ordenado":
			return Stats.gerarOrdenado(n);
		case "invertido":
			return Stats.gerarInvertido(n);
		default:
			return Stats.gerarAleatorios(n);
		}
	}
}
