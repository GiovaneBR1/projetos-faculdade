package com.projeto2;

/**
 * Busca Sequencial
 *
 * Percorre o array elemento por elemento. Complexidade: O(n) em todos os casos
 */
public class BuscaSequencial {

	public static boolean buscar(int[] arr, int alvo) {
		for (int v : arr) {
			if (v == alvo)
				return true;
		}
		return false;
	}
}
