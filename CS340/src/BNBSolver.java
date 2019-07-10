import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class BNBSolver {
	   private List<Item> items;
	   private int capacity = 10;

	private class Node implements Comparable<Node> {
	      
	      public int h;
	      List<Item> taken;
	      public double bound;
	      public double value;
	      public double weight;
	      
	      public Node() {
	         taken = new ArrayList<Item>();
	      }
	      
	      public Node(Node parent) {
	         h = parent.h + 1;
	         taken = new ArrayList<Item>(parent.taken);
	         bound = parent.bound;
	         value = parent.value;
	         weight = parent.weight;
	      }
	      
	      // Sort by bound
	      public int compareTo(Node other) {
	         return (int) (other.bound - bound);
	      }
	      
	      public void computeBound() {
	         int i = h;
	         double w = weight;
	         bound = value;
	         Item item;
	         do {
	            item = items.get(i);
	            if (w + item.getWeight() > capacity) break;
	            w += item.getWeight();
	            bound += item.getValue();
	            i++;
	         } while (i < items.size());
	         bound += (capacity - w) * (item.getValue() / item.getWeight());
	      }
	   }
	   
	   public void solve() {
	      
	      Collections.sort(items, Item.byRatio());
	      Node best = new Node();
	      Node root = new Node();
	      root.computeBound();
	      PriorityQueue<Node> q = new PriorityQueue<Node>();
	      q.offer(root);
	      
	      while (!q.isEmpty()) {
	         Node node = q.poll();
	         
	         if (node.bound > best.value && node.h < items.size() - 1) {
	            
	            Node with = new Node(node);
	            Item item = items.get(node.h);
	            with.weight += item.getWeight();
	            
	            if (with.weight <= capacity) {
	            
	               with.taken.add(items.get(node.h));
	               with.value += item.getValue();
	               with.computeBound();
	               
	               if (with.value > best.value) {
	                  best = with;
	               }
	               if (with.bound > best.value) {
	                  q.offer(with);
	               }
	            }
	            
	            Node without = new Node(node);
	            without.computeBound();
	            
	            if (without.bound > best.value) {
	               q.offer(without);
	            }
	         }
	      }
	      for (int i = 0;i<best.taken.size();i++) {
	    	  System.out.println(best.taken.get(i).getName());
	      }

	   }
	   
	   public void setItems(List<Item> items) {
		   this.items = items;
	   }
}
