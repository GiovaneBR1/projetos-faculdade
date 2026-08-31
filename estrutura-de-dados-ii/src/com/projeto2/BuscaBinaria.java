package com.projeto2;

/**
 * Busca Binária
 *
 * Requer array ordenado. Divide o intervalo ao meio a cada passo. Complexidade:
 * O(log n)
 */
public class BuscaBinaria {

	public static boolean buscar(int[] arr, int alvo) {
		int esq = 0, dir = arr.length - 1;
		while (esq <= dir) {
			int meio = esq + (dir - esq) / 2;
			if (arr[meio] == alvo)
				return true;
			else if (arr[meio] < alvo)
				esq = meio + 1;
			else
				dir = meio - 1;
		}
		return false;
	}
}
