import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
public class DPSolver {
	private int maxWeight = 0;
	private int maxProfit = 0;
	ArrayList<Integer> result = new ArrayList<Integer>();
	int[] store;
	int n = 0;
	
//	ArrayList<Integer> wt = new ArrayList<Integer>();
//	ArrayList<Integer> val = new ArrayList<Integer>();
//	ArrayList<String> name = new ArrayList<String>();
	ArrayList<Item> items = new ArrayList<Item>();
	
	public DPSolver() {
		// TODO Auto-generated constructor stub
	}
	
	public void createItem(String name,float weight,int price) {
		Item itema = new Item(name,price,weight);
		items.add(itema);
		
	}
	
	void knapSackDp(int W, int n) {
		int K[][] = new int[n + 1][W + 1]; 
		int i,w;
		// Build DP Table K[i][w]
        for (i = 0; i <= n; i++) { 
            for (w = 0; w <= W; w++) { 
                if (i == 0 || w == 0) 
                    K[i][w] = 0; 
                else if (wt.get(i - 1) <= w) 
                    K[i][w] = Math.max(val.get(i - 1) +  
                              K[i - 1][w - wt.get(i - 1)], K[i - 1][w]); 
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
                System.out.println("Item name: "+name.get(i-1)+" Price: "+wt.get(i-1) + " "); 
                result.add(i-1);
                res = res - val.get(i - 1); 
                w = w - wt.get(i - 1); 
            }
        }  
	}

	
	void setmaxWeight(int w) {
		this.maxWeight = w;
	}
	
	int getmaxWeightaxWeight() {
		return this.maxWeight;
	}
	
	ArrayList<Integer> getWeightList(){
		return this.wt;
	}
	
	ArrayList<Integer> getPriceList(){
		return this.val;
	}
	ArrayList<String> getNameList(){
		return this.name;
	}
	
	ArrayList<Integer> getResult(){
		return this.result;
	}
	
	

	
	
	
	
	
	
}
