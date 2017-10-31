/* Import TreeAVL.java */
//import br.uece.avl.TreeAVL;

public class OpenHashingAvl {

	private TreeAVL[] vetor;
	public final int SIZE = 5; //7
	public final int LIMITE_FATOR_CARGA = 3; //10

	public OpenHashingAvl() {
		vetor = new TreeAVL[SIZE];
	}

	public void add(int value) {


		int posicao = geraPosicao(value);
		if (vetor[posicao] != null && !vetor[posicao].find(value)
				&& fatorCarga() <= LIMITE_FATOR_CARGA) {
			vetor[posicao].add(value);
			System.out.println("Elemento " + value + " adicionado na posicao "
					+ posicao + "\n");

		} else if (vetor[posicao] == null) {
			vetor[posicao] = new TreeAVL();
			vetor[posicao].add(value);
			System.out.println("Elemento " + value + " adicionado na posicao "
					+ posicao + "\n");

		} else if (vetor[posicao].find(value)) {
			addMetodoQuadratico(value);
		}
	}
	/*
	 * Abstracao ao metodo gerarPosicaoQuadratica. O metodo addMetodoQuadratico
	 * envia o valor para gerar a nova posicao com base na funcao quadratica e
	 * faz uma nova verificacao para saber se a nova posicao gerada eh nula ou
	 * ja instanciada. E por fim o valor eh adicionado
	 */
	private void addMetodoQuadratico(int value) {
		int posicao = gerarPosicaoQuadratica(value);

		if (vetor[posicao] == null) {
			vetor[posicao] = new TreeAVL();
			vetor[posicao].add(value);
			System.out.println("Elemento " + value + " adicionado na posicao "
					+ posicao + "\n");
		} else if (!vetor[posicao].find(value)) {
			vetor[posicao].add(value);
			System.out.println("Elemento " + value + " adicionado na posicao "
					+ posicao + "\n");
		}
	}

	/*
	 * Primeiro metodo para gerar posicao com base no valor e tamanho do vetor
	 */
	private int geraPosicao(int value) {
		return value % vetor.length;
	}

	private int gerarPosicaoQuadratica(int value) {
		int tentativa = 1;
		int posicao = -1;
		for (int i = 0; i < SIZE/2+1; i++) {
			posicao = (value + (tentativa * tentativa)) % vetor.length;

			if(vetor[posicao] != null &&vetor[posicao].find(value)) {
				return posicao;
			}
			if (vetor[posicao] == null || !vetor[posicao].find(value)) {
				return posicao;
			} else {
				tentativa++;
			}
		}

		if (tentativa == SIZE/2+1) {
			System.out.println("Nao foi possivel inserir o elemento " + value);
		}
		return posicao;

	}

	public void remove(int value) {
		int posicao = geraPosicao(value);
		if(vetor[posicao].find(value)) {
			vetor[posicao].remove(value);
			if(vetor[posicao].height() <0) {
				vetor[posicao] = null;
			}
		} else {
			posicao = gerarPosicaoQuadratica(value);
			vetor[posicao].remove(value);
		}
	}
	public int busca(int value) {
		int posicao = geraPosicao(value);
		if(vetor[posicao]!= null &&vetor[posicao].find(value)) {
			return posicao;
		} else {
			posicao = gerarPosicaoQuadratica(value);
			return posicao;
		}
	}
	/*
	 * Refere-se a quantidade de elementos na maior arvore
	 */
	public int fatorCarga() {
		TreeAVL[] aux = vetor;
		int cont = 0;
		for (int i = 0; i < aux.length; i++) {
			if (aux[i] != null) {
				cont = Math.max(cont, (aux[i].height() + 1));
			}
		}
		return cont;
	}

	public Double fatorBalanceamento() {
		int somatorioCarga = 0;
			for (int i = 0; i < vetor.length; i++) {
				if(vetor[i] != null){
					somatorioCarga += vetor[i].height() + 1;					
				}
			}

			Double valor = (double) somatorioCarga / (vetor.length * fatorCarga());
			return valor;
	}
	
	public int larguraOcupada() {
		int largura = 0;
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {
				largura++;
			}
		}
		return largura;
	}

	public void imprime() {
		for (int i = 0; i < vetor.length; i++) {
			System.out.printf("Indice " + i + "=> ");
			TreeAVL aux = vetor[i];
			if (aux != null) {
				aux.print();
			}
			System.out.println("");
		}

	}

	public TreeAVL[] getVetor() {
		return vetor;
	}
}
