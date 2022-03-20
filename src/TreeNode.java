import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
public class TreeNode {
	private Element element;
	private TreeNode left;
	private TreeNode right;
	private int size; 
	
	public TreeNode() {}
	public TreeNode(int size) {
		this.size = size;
	}
	public TreeNode(Element element, int size) {
		this.element = element;
		this.size = size;
	}
	public TreeNode(Element element, TreeNode left, TreeNode right, int size) { 
		this.element = element;
		this.left = left;
		this.right = right;
		this.size = size;
	}
	
	
	public static TreeNode create(Element[] elements) {
		int size = elements.length;
		
		if(size == 0) { 
			return null;
		}
		
		int half = size / 2; 
		int rIndex = half + 1;
		
		Element[] lElements = Arrays.copyOfRange(elements, 0, half);
		TreeNode lNode = TreeNode.create(lElements);
		TreeNode rNode = null; 
		
		if(size > rIndex) {
			Element[] rElements = Arrays.copyOfRange(elements, rIndex, size);
			rNode = TreeNode.create(rElements);
		}
		
		
		TreeNode root = new TreeNode(elements[half], lNode, rNode, size);
		
		return root;
	}
	
	public static TreeNode upsert(TreeNode tree, Element element) {
		String key = element.getKey();
		if(tree == null) {
			tree = new TreeNode(element, 1);
			return tree;
		}else if(tree.compareTo(key) > 0) {
			tree.setLeft(upsert(tree.getLeft(), element));
			tree.sizeUp();
			return tree;
		}else if(tree.compareTo(key) < 0) {
			tree.setRight(upsert(tree.getRight(), element));
			tree.sizeUp();
			return tree;
		}else {
			tree.setElement(element);
			return tree;
		}
	}
	
	public static Element find(TreeNode tree, String key) { 
		if(tree == null) { 
			return new Element(null, null);
		}else if(tree.compareTo(key) == 0) {
			return tree.getElement();
		}
		
		if(tree.compareTo(key) > 0) {
			return TreeNode.find(tree.getLeft(), key);
		}else {
			return TreeNode.find(tree.getRight(), key);
		}
	}
	
	public static List<Element> traverse(TreeNode tree) {
		List<Element> elements = new ArrayList<Element>();
		
		
		if(tree == null) {
			return elements;
		}

		List<Element> left = TreeNode.traverse(tree.getLeft());
		List<Element> right = TreeNode.traverse(tree.getRight());
		
		elements = Stream.concat(elements.stream(), left.stream()).collect(Collectors.toList());
		elements.add(tree.getElement());
		elements = Stream.concat(elements.stream(), right.stream()).collect(Collectors.toList());
		
		System.out.println(elements);
		return elements;
	}
	
	public static Element justSmallerOrEqual(TreeNode tree, String key) { 
		if(tree == null) { 
			return new Element();
		}
		Element current = tree.getElement();
		
		if(current.getKey().compareTo(key) <= 0) {
			Element right = TreeNode.justSmallerOrEqual(tree.getRight(), key);
			
			try {
				if(current.getKey().compareTo(right.getKey()) < 0) {
					current = right;
				}
				
			}catch(NullPointerException e) {}
			
			
		}else {
			Element left = TreeNode.justSmallerOrEqual(tree.getLeft(), key);
			
			try {
				if(left.getKey() instanceof String) { 
					current = left;
				}
			}catch(NullPointerException e) {
				return new Element();
			}
			
		}
		
		return current; 
	}
	
	public static Element justLarger(TreeNode tree, String key) {
		if(tree == null) { 
			return new Element();
		}
		
		Element current = tree.getElement();
		
		if(current.getKey().compareTo(key) > 0) {
			Element left = TreeNode.justLarger(tree.getLeft(), key);
			
			try {
				if(current.getKey().compareTo(key) < 0) {
					current = left;
				}
			}catch(NullPointerException e) {}
			
		}else {
			Element right = TreeNode.justLarger(tree.getRight(), key);
			
			try {
				if(right.getKey() instanceof String) {
					current = right;
				}
			}catch(NullPointerException e) {
				return new Element();
			}
			
		}
		
		return current;
		
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	public void sizeUp() {
		this.size++;
	}
	public int compareTo(String key) {
		return element.getKey().compareTo(key);
	}
}
