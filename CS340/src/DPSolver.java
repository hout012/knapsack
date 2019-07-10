import java.util.ArrayList;
import java.util.List;
public class DPSolver {
	ArrayList<Item> result = new ArrayList<Item>();
	List<Item> items = new ArrayList<Item>();
	private int W = 10;
	private int n = 0;
	
	public DPSolver() {
		
	}
	
	public void createItem(String name,float weight,int price) {
		Item i = new Item(name,price,weight);
		items.add(i);
	}
	
	void knapSackDp() {
		this.n = items.size();
		int K[][] = new int[n + 1][W + 1]; 
		int i,w;
		// Build DP Table K[i][w]
        for (i = 0; i <= n; i++) { 
            for (w = 0; w <= W; w++) { 
                if (i == 0 || w == 0) 
                    K[i][w] = 0; 
                else if (items.get(i - 1).getWeight() <= w) 
                    K[i][w] = Math.max(items.get(i - 1).getValue() +  
                              K[i - 1][(int) (w - items.get(i - 1).getWeight())], K[i - 1][w]); 
                else
                    K[i][w] = K[i - 1][w]; 
            } 
        } 
        int res =  K[n][W];
        System.out.println(res);
        //Start Backtracking
        w = W; 
        for (i = n; i > 0 && res > 0; i--) { 
            if (res == K[i - 1][w]) {
                continue; // we didn't picked the items
            }
            else {
            	//Items get picked
                System.out.println("Item name: "+items.get(i-1).getName()+" Price: "+items.get(i-1).getValue() + " Weight:"+items.get(i-1).getWeight()); 
                result.add(items.get(i-1));
                res = res - items.get(i - 1).getValue(); 
                w = w - (int)items.get(i - 1).getWeight(); 
            }
        }  
	}

	
	void setmaxWeight(int w) {
		this.W = w;
	}
	
	int getmaxWeightaxWeight() {
		return this.W;
	}
	
	ArrayList<Item> getResult(){
		return this.result;
	}
	
	void setItems(List<Item> items) {
		this.items = items;
	}

	
	
	
	
	
	
}
