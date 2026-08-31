package com.projeto3;

/**
 * Merge Sort
 *
 * Divide o array ao meio recursivamente e combina as metades ordenadas.
 * Complexidade: O(n log n) em todos os casos (melhor, médio e pior) Espaço
 * auxiliar: O(n)
 */
public class MergeSort {

	public static void ordenar(int[] arr) {
		if (arr.length <= 1)
			return;
		int[] aux = new int[arr.length];
		mergeSort(arr, aux, 0, arr.length - 1);
	}

	private static void mergeSort(int[] arr, int[] aux, int esq, int dir) {
		if (esq >= dir)
			return;
		int meio = esq + (dir - esq) / 2;
		mergeSort(arr, aux, esq, meio);
		mergeSort(arr, aux, meio + 1, dir);
		mesclar(arr, aux, esq, meio, dir);
	}

	private static void mesclar(int[] arr, int[] aux, int esq, int meio, int dir) {
		for (int k = esq; k <= dir; k++)
			aux[k] = arr[k];

		int i = esq, j = meio + 1;
		for (int k = esq; k <= dir; k++) {
			if (i > meio)
				arr[k] = aux[j++];
			else if (j > dir)
				arr[k] = aux[i++];
			else if (aux[j] < aux[i])
				arr[k] = aux[j++];
			else
				arr[k] = aux[i++];
		}
	}
}
