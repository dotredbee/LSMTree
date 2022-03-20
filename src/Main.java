import java.util.List;
import java.util.ListIterator;
public class Main {
	public static void main(String[] args) { 
		
//		�׽�Ʈ ���̽�
		Element[] testcase = {
			new Element("1", "one"),
			new Element("2", "two"),
			new Element("3", "three"),
			new Element("4", "four"),
			new Element("5", "five")
		};
		
//		Ʈ������ �׽�Ʈ & find �׽�Ʈ
		TreeNode tree = TreeNode.create(testcase);
		
		Element data1 = TreeNode.find(tree, "3");
		
		System.out.println(data1.getKey() + " " + data1.getVal());
		
		Element data2 = TreeNode.find(tree, "6");
		
		System.out.println(data2.getKey() + " " + data2.getVal());
		
//		update & insert(upsert) �׽�Ʈ 
		tree = TreeNode.upsert(tree, new Element(
			"6",
			"six"
		));
		tree = TreeNode.upsert(tree, new Element(
				"7",
				"seven"
		));
		
		System.out.println("============ AFTER ============");
		data1 = TreeNode.find(tree, "6");
		data2 = TreeNode.find(tree, "1");
		System.out.println(data1.getKey() + " " + data1.getVal());
		System.out.println(data2.getKey() + " " + data2.getVal());
		
//		traverse �׽�Ʈ (Ʈ�� ��� ��� ���)
		List<Element> elements =  TreeNode.traverse(tree);
		
		System.out.println(elements.size());
		ListIterator<Element> iter = elements.listIterator();
		
		while(iter.hasNext()) {
			Element element = iter.next();
			if(element != null) {
				System.out.println(element.getKey());
			}
		}
		
		
		System.out.println("============ JUST TESTCASES =============");
		Element e1 = TreeNode.justSmallerOrEqual(tree, "8");
		
		System.out.println(e1.getKey());
		
		Element e2 = TreeNode.justLarger(tree, "8");
		
		System.out.println(e2.getKey());
	}
}
