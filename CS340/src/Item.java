
public class Item {
	private String name;
	private int value;
	private float weight;
	
	public Item(String name,int value, float weight) {
		this.name = name;
		this.value = value;
		this.weight = weight;
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
	
	public float getWeight() {
		return this.weight;
	}
	public void setWeight(float f) {
		this.weight = f;	
	}

}
