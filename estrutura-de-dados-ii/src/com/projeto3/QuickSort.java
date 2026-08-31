package com.projeto3;

import java.util.Random;

/**
 * Quick Sort
 *
 * Seleciona um pivô, particiona o array e ordena as partes recursivamente.
 *
 * Complexidade: Melhor/Médio caso: O(n log n) – partições equilibradas Pior
 * caso: O(n²) – array já ordenado com pivô fixo
 *
 * Implementação: pivô aleatório (evita pior caso em arrays ordenados na
 * prática).
 */
public class QuickSort {

	private static final Random rand = new Random();

	public static void ordenar(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private static void quickSort(int[] arr, int esq, int dir) {
		if (esq >= dir)
			return;
		int p = particionar(arr, esq, dir);
		quickSort(arr, esq, p - 1);
		quickSort(arr, p + 1, dir);
	}

	private static int particionar(int[] arr, int esq, int dir) {
		int idx = esq + rand.nextInt(dir - esq + 1);
		trocar(arr, idx, dir);

		int pivo = arr[dir];
		int i = esq - 1;

		for (int j = esq; j < dir; j++) {
			if (arr[j] <= pivo) {
				i++;
				trocar(arr, i, j);
			}
		}
		trocar(arr, i + 1, dir);
		return i + 1;
	}

	private static void trocar(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
