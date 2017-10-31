public class TreeAVL {

	/* Import NodeAVL if necessary */
	private NodeAVL root;
	static final int ALLOWED_IMBALANCE = 1;

	public TreeAVL() {
		root = null;
	}

	/**
	 * Abstracao para o metodo add(value, node)
	 */
	public void add(int value) {
		root = add(value, root);
	}

	public NodeAVL getRoot() {
		return root;
	}

	/**
	 * No metodo add o valor eh inserido na direita ou na esquerda, de acordo
	 * com seu tamanho, e ao final do metodo eh feito o balanceamento. Eh
	 * preciso retornar esse balanceamento do No, porque no java as variaveis de
	 * instancias dos metodos nao se propagam para alem do seu escopo, logo o
	 * retorno desse balanceamento serve como uma nova instancia arvore.
	 */
	public NodeAVL add(int value, NodeAVL node) {
		if (node == null) {
			return new NodeAVL(value);
		} else if (value < node.getValue()) {
			node.setLeft(add(value, node.getLeft()));
		} else if (value > node.getValue()) {
			node.setRight(add(value, node.getRight()));
		} else
			;
		return balance(node);
	}

	/**
	 * Metodo para balancear uma arvore avl com base na diferenca das alturas,
	 * permitindo o valor maximo de altura 1.
	 */
	private NodeAVL balance(NodeAVL node) {
		if (node == null) {
			return node;
		}

		if ((height(node.getLeft()) - height(node.getRight())) > ALLOWED_IMBALANCE) {
			if (height(node.getLeft().getLeft()) >= height(node.getLeft().getRight())) {
				node = rotateRight(node);
			} else {
				node = doubleRotateRight(node);
			}
		} else {
			if ((height(node.getRight()) - height(node.getLeft())) > ALLOWED_IMBALANCE) {
				if (height(node.getRight().getRight()) >= height(node.getRight().getLeft())) {
					node = rotateLeft(node);
				} else {
					node = doubleRotateLeft(node);
				}
			}
		}
		node.setHeightNode(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
		return node;
	}

	public int height() {
		return height(root);
	}

	public int height(NodeAVL node) {
		return node == null ? -1 : node.getHeightNode();
	}


	public NodeAVL rotateRight(NodeAVL k2) {
		NodeAVL k1 = k2.getLeft();
		k2.setLeft(k1.getRight());
		k1.setRight(k2);
		k2.setHeightNode(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
		k1.setHeightNode(Math.max(height(k1.getLeft()), k2.getHeightNode()) + 1);
		return k1;
	}

	public NodeAVL rotateLeft(NodeAVL k1) {
		NodeAVL k2 = k1.getRight();
		k1.setRight(k2.getLeft());
		k2.setLeft(k1);
		k1.setHeightNode(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
		k2.setHeightNode(Math.max(height(k2.getRight()), k1.getHeightNode()) + 1);
		return k2;
	}

	public NodeAVL doubleRotateRight(NodeAVL k3) {
		k3.setLeft(rotateLeft(k3.getLeft()));
		return rotateRight(k3);
	}

	public NodeAVL doubleRotateLeft(NodeAVL k1) {
		k1.setRight(rotateRight(k1.getRight()));
		return rotateLeft(k1);
	}

	/**
	 * Abstracao para o metodo remove(value, node)
	 */
	public void remove(int value) {
		root = remove(value, root);
	}

	/**
	 * Metodo de exclusao de um valor atraves de comparacoes e ao final, a
	 * arvore eh balanceada
	 */
	public NodeAVL remove(int value, NodeAVL node) {
		if (node == null) {
			return node;
		}

		if (value < node.getValue()) {
			node.setLeft(remove(value, node.getLeft()));
		} else if (value > node.getValue()) {
			node.setRight(remove(value, node.getRight()));
		} else if (node.getLeft() != null && node.getRight() != null) {
			node.setValue(findMin(node.getRight()).getValue());
			node.setRight(remove(node.getValue(), node.getRight()));
		} else {
			node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
		}
		return balance(node);
	}

	/**
	 * Pesquisa a menor arvore, que estara sempre a esquerda.
	 */
	public NodeAVL findMin(NodeAVL node) {
		if (node == null) {
			return node;
		}

		while (node.getLeft() != null) {
			node = node.getLeft();
		}
		return node;
	}

	public boolean find(int value) {
		return find(value, root);
	}

	public boolean find(int value, NodeAVL node) {
		while (node != null) {
			if (value < node.getValue()) {
				node = node.getLeft();
			} else if (value > node.getValue()) {
				node = node.getRight();
			} else {
				return true;
			}
		}
		return false;

	}

	public NodeAVL findNode(int value) {
		return findNode(value, root);
	}

	public NodeAVL findNode(int value, NodeAVL node) {
		while (value != node.getValue()) {
			if (value < node.getValue()) {
				node = node.getLeft();
			} else if (value > node.getValue()) {
				node = node.getRight();
			} else {
				;
			}
		}
		return node;
	}

	public void print() {
		if(root != null) {
			print(root, root.getValue(), 0);
		} else {
			System.out.println("Arvore vazia");
		}
	}
	
	private void print(NodeAVL tree, int value, int direction) {

		if (tree != null) {
			if (direction == 0) {
				System.out.printf("%2d é raiz\n", tree.getValue());
			} else {
				System.out.printf("%2d está a %6s de %2d\n", tree.getValue(), direction == 1 ? "direita" : "esquerda", value);
			}

			print(tree.getLeft(), tree.getValue(), -1);
			print(tree.getRight(), tree.getValue(), 1);
		}
	}
	
	public void imprimir(NodeAVL node) {
		if(node != null) {
//			System.out.printf(node.getValue() + " - ");
			imprimir(node.getLeft());
//			System.out.printf(node.getValue() + " - ");
			imprimir(node.getRight());
			System.out.printf(node.getValue() + " - ");
		}
	}
	
	public static void main(String[] args) {

		TreeAVL tree = new TreeAVL();
		int[] s = {1, 6, 7, 15, 11, 12, 17, 19, 36, 22, 25, 39, 40, 42, 47, 62};
//		int[] s = {27, 51, 18, 16, 28, 2, 52,  15,  8, 12, 21, 9};
		for (int i = 0; i < s.length; i++) {
			tree.add(s[i]);
		}
		
		tree.print();
	
	}
}
