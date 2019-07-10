import java.util.Comparator;

public class Item {
	private String name;
	private int value;
	private double weight;
	
	public Item(String name,int value, double d) {
		this.name = name;
		this.value = value;
		this.weight = d;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String s) {
		this.name = s;
	}
	
	public int getValue() {
		return this.value ;
	}
	
	public void setValue(int i) {
		this.value = i;
	}
	
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(Double double1) {
		this.weight = double1;	
	}

	public static Comparator<Item> byRatio() {
		return new Comparator<Item>() {
		   public int compare(Item i1, Item i2) {
		      return Double.compare(i2.getRatio(), i1.getRatio());
		   }
		};
		}
	public double getRatio() {
		return this.value/this.weight;
	}

}
