import java.util.ArrayList;
import java.util.List;

public class test {
	public static void main(String[] args) {
		DPSolver solve = new DPSolver();
		List <Item> items = new ArrayList<Item>();
		items.add(new Item("Item1",40,2));
		items.add(new Item("Item2",50,3.14));
		items.add(new Item("Item3",100,1.98));
		items.add(new Item("Item4",95,5));
		items.add(new Item("Item5",30,3));
		solve.setItems(items);
		solve.knapSackDp();
	}
}
