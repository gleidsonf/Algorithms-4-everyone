
/* Import Primo.java*/
//import br.uece.hashing.util.Primo;


public class ClosedHashingList {
	private Integer[] vetor;
	public static final int SIZE = 23;

	public ClosedHashingList() {
		vetor = new Integer[SIZE];
	}

	public void add(int value) {

		int posicao = geraPosicao(value);

		if (isEmptyPosition(posicao)) {
			vetor[posicao] = value;
		} else {
			/** TODO Descomentar somente a linha referente a funcao desejada */
//			vetor[tentativaLinear(value)] = value;
//			vetor[funcaoQuadratica(value)] = value;
			vetor[duploHashing(value)] = value;
		}

	}

	@SuppressWarnings("unused")
	private int funcaoQuadratica(int value) {
		int tentativa = 1;
		int posicao = -1;
		for (int i = 0; i < vetor.length / 2 + 1; i++) {

			posicao = (value + (tentativa * tentativa)) % SIZE;
			System.out.println("Valor " + value + " - posicao: " + posicao);

			if (vetor[posicao] == null) {
				return posicao;
			} else {
				tentativa++;
			}
		}

		if (tentativa > vetor.length / 2 + 1) {
			System.out.println("Nao foi possivel inserir o elemento " + value);
		}
		return posicao;
	}

	@SuppressWarnings("unused")
	private int tentativaLinear(int value) {
		int tentativa = 1;
		int posicao = -1;
		for (int i = 0; i < vetor.length / 2 + 1; i++) {
			posicao = (value + tentativa) % SIZE;
			if (vetor[posicao] == null) {
				return posicao;
			} else {
				tentativa++;
			}
		}

		if (tentativa > vetor.length / 2 + 1) {
			System.out.println("Nao foi possivel inserir o elemento " + value);
			return -1;
		}
		return posicao;

	}

	public int duploHashing(int value) {
		int tentativa = 1;
		int posicao = value % SIZE;
		int r = Primo.anteriorPrimo(SIZE);

		if (vetor[posicao] != null && vetor[posicao] == value) {
			return posicao;
		}
		for (int i = 0; i < vetor.length / 2; i++) {
			posicao = (tentativa * r) % SIZE;
			System.out.println("POSICAO " + posicao);
			if (vetor[posicao] == null) {
				continue;
			} else if (vetor[posicao] == value) {
				System.out.println("Retorno " + posicao);
				return posicao;
			}
			tentativa++;
		}
		return posicao;
	}

	private boolean isEmptyPosition(int posicao) {
		return vetor[posicao] == null;
	}

	private int geraPosicao(int value) {
		int position = -1;
		// TODO verificar viabilidade de inserir numeros negativos
		if (value < 0)
			position = value * (-1);
		else
			position = value;
		return position % vetor.length;
	}

	public void remove(int value) {
		int posicao = busca(value);
		if (!isEmptyPosition(posicao) && vetor[posicao] == value) {
			vetor[posicao] = null;
		} else {
			System.out.println("Numero não existe!");
		}
	}

	public int busca(int value) {
		int posicao = geraPosicao(value);

		if (vetor[posicao] != null && vetor[posicao] == value) {
			return posicao;
		} else {
			/** TODO Descomentar somente a linha referente a funcao desejada */
//			 return tentativaLinear(value);
//			return funcaoQuadratica(value);
			 return duploHashing(value);
		}
	}

	public void imprime() {
		for (int i = 0; i < vetor.length; i++) {
			System.out.println("Indice " + i + "=> " + vetor[i]);
		}
	}

	public int what() {
		int a = 0;
		for (int i = 0; i < 6; i++) {	
			if (i == 4) {
				return i;
			}
		}
		return a;
	}

	
	public Integer[] getVetor() {
		return vetor;
	}

}
