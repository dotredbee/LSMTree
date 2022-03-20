
public class Node {
	private Element element;
	private Node right;
	private Node left;
	private int size; 
	
	public Node(int size) {
		this.element = null;
		this.right = null;
		this.left = null;
		this.size = size; 
	}
	public Node(Element element, int size) { 
		this.element = element;
		this.right = null;
		this.left = null;
		this.size = size;
	}
	public Node(Element element, Node right, Node left, int size) { 
		this.element = element;
		this.right = right;
		this.left = left;
		this.size = size;
	}
	
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	};
	
	
	public String getKey() {
		return this.element.getKey();
	}
	public void setVal(String val) { 
		this.element.setVal(val);
	}
}
