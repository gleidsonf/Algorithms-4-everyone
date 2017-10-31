/* Import NodeAVL.java, TreeAVL.java and Primo.java */
//import br.uece.avl.NodeAVL;
//import br.uece.avl.TreeAVL;
//import br.uece.hashing.util.Primo;

public class HalfOpenHashingAvl {
	private final int SIZE = 5;
	private final int ALLOWED_IMBALANCE = 3; //ALLOWED_IMBALANCE
	TreeAVL[] vetor;
	private boolean changeSize;

	public HalfOpenHashingAvl() {
		vetor = new TreeAVL[SIZE];
		changeSize = false;
	}

public void add(int value) {
		int posicao = geraPosicao(value);

		if (vetor[posicao] == null) {
			vetor[posicao] = new TreeAVL();
			vetor[posicao].add(value);
		} else {
			vetor[posicao].add(value);
		}
		
		if (vetor[posicao] != null && vetor[posicao].height() > ALLOWED_IMBALANCE) {
			rehashing();
			changeSize = true;
		}

	}
	/*
	 * Caso a largura do vetor contenha metade do seu tamanho + 1 elemento, eh
	 * chamado o metodo rehashing, que criara outro vetor com um numero primo
	 * que seja maior que o dobro do tamanho atual.
	 * 
	 * Para isso o vetor atual eh adicionado num temporario. Eh calculado o novo
	 * tamanho do vetor e logo apos cada posicao do vetor eh adicionada ao
	 * metodo percorreArvore, que sera a responsavel por fazer o rehashing
	 * efetivamente.
	 */

	private void rehashing() {
		System.out.println("Metodo rehashing. A arvore ate este momento e': ");
		imprime();
		TreeAVL[] aux = new TreeAVL[vetor.length];
		/*
		 * Salva o vetor principal num temporario
		 */
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {
				aux[i] = vetor[i];
			}
		}
		System.out.println("\n");

		int novoValor = Primo.proximoPrimo(vetor.length);
		vetor = new TreeAVL[novoValor];
		for (int i = 0; i < aux.length; i++) {

			if (aux[i] != null) {
				percorreArvore(aux[i].getRoot());
			}
		}
		System.out.println("----Fim do Rehashing----");
	}

	/*
	 * Um no eh recebido e recursivamente readicionada ao vetor que ja contem um
	 * novo tamanho primo 2x maior que o antigo.
	 */
	private void percorreArvore(NodeAVL node) {
		//

		if (node != null) {
			percorreArvore(node.getLeft());
			System.out.println("Readicionando o elemento " + node.getValue());
			add(node.getValue());
			percorreArvore(node.getRight());
		}
	}

	/*
	 * Primeiro metodo para gerar posicao com base no valor e tamanho do vetor
	 */
	private int geraPosicao(int value) {
		return value % vetor.length;
	}

	/*
	 * Percorre o vetor a procura da posicao. Caso encontre eh chamada o metodo
	 * remover da arvore referente a posicao.
	 */
	public void remove(int value) {
		int posicao = geraPosicao(value);
		if (vetor[posicao].find(value)) {
			vetor[posicao].remove(value);
			System.out.println("Elemento" + value + " removido");
			if (vetor[posicao].height() < 0) {
				vetor[posicao] = null;
			}

		} else {
		}

	}

	/*
	 * Refere-se a quantidade de elementos na maior arvore
	 */
	public int fatorCarga() {
		int cont = 0;
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {

				cont = Math.max(cont, vetor[i].height() + 1);
			}
		}
		return cont;
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
			System.out.println("Indice " + i + ": ");
			TreeAVL aux = vetor[i];
			if (aux != null) {
				aux.print();
			}
			System.out.println("");
		}

	}

	public Double fatorBalanceamento() {
		int somatorioCarga = 0;
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {
				somatorioCarga += vetor[i].height() + 1;
			}
		}

		Double valor = (double) somatorioCarga / (vetor.length * fatorCarga());
		return valor;
	}

	public int busca(int value) {
		int posicao = geraPosicao(value);
		
		return vetor[posicao]!= null && vetor[posicao].find(value) ? posicao : -1;
	}

	
	public TreeAVL[] getVetor() {
		return vetor;
	}
	
	public boolean getChangeSize() {
		return changeSize;
	}
}
