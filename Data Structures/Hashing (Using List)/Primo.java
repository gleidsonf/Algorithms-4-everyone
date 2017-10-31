public class Primo {
	/**
	 * Esse codigo pode ser melhorado com a implementacao do Crivo de
	 * Eratostenes
	 */
	public static boolean isPrimo(int numero) {
		int cont = 1;

		if (numero <= 9) {
			cont = 0;
		}

		for (int i = 1; i <= 9; i++) {
			if ((numero % i) == 0)
				cont += 1;
		}

		if (cont >= 1 && cont <= 2) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int anteriorPrimo(int tamanho) {
		int novoValor = tamanho-1;
		while (!isPrimo(novoValor)) {
			novoValor--;
		}
		return novoValor;
	}

	public static int proximoPrimo(int tamanho) {
		int novoValor = tamanho * 2;
		System.out.println("Valor 2x o tamanho do vetor: " + novoValor);
		while (!isPrimo(novoValor) && novoValor > 0) {
			novoValor++;
		}
		System.out.println("Valor primo gerado: " + novoValor);
		return novoValor;
	}

}
