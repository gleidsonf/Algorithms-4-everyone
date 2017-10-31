package br.uece.avl;

public class NodeAVL {
	
	private int value;
	private NodeAVL left;
	private NodeAVL right;
	private int heightNode;
	
	public NodeAVL(int value) {
		this(value, null, null);
	}
	
	public NodeAVL(int value, NodeAVL left, NodeAVL right) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.heightNode = 0;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public NodeAVL getLeft() {
		return left;
	}
	public void setLeft(NodeAVL left) {
		this.left = left;
	}
	public NodeAVL getRight() {
		return right;
	}
	public void setRight(NodeAVL right) {
		this.right = right;
	}
	@SuppressWarnings("unused")
	public int getHeightNode() {
		if(this == null)
			return -1;
		else
		return heightNode;
	}
	public void setHeightNode(int heightNode) {
		this.heightNode = heightNode;
	}

}
