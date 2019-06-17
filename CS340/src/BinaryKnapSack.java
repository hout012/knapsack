import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
public class BinaryKnapSack {
	private int maxWeight = 0;
	ArrayList<Integer> result = new ArrayList<Integer>();
	ArrayList<Integer> curstore = new ArrayList<Integer>();
	ArrayList<Integer> maxstore = new ArrayList<Integer>();
	ArrayList<Integer> wt = new ArrayList<Integer>();
	ArrayList<Integer> val = new ArrayList<Integer>();
	ArrayList<String> name = new ArrayList<String>();
	public BinaryKnapSack() {
		// TODO Auto-generated constructor stub
	}
	
	public void createItem(String name,int weight,int price) {
		this.wt.add(weight);
		this.name.add(name);
		this.val.add(price);
		
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

	
	void setMaxWeight(int w) {
		this.maxWeight = w;
	}
	
	int getMaxWeight() {
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
	
/*
	
	public void branchKnap()
	{
		curstore = new int[N+1];
		maxstore = new int[N+1];

		for (int i = 0; i < curstore.length; i++)
		{
			curstore[i] = 0;
			maxstore[i] = 0;
		}
		maxval = 0;
		for (int i = 1; i <= N; i++)
		{
			if (size[i] <= M)
				knapsack(i, 0, 0);
		}
		System.out.println();
		System.out.println("A B&B Knapsack solution = " + maxval);
		System.out.println("   Item  Size  Value  Num. Taken ");
		for (int i = 1; i <= N; i++)
		{
			System.out.println("    " + i + "     " + size[i] + "     "
							   + val[i] + "       " + maxstore[i]);
		}
	}

	// Branch and bound code for knapsack.  Note that this code is in fact quite
	// similar to the code for subset sum.  However, now each item can be selected
	// more than once and we are maximizing the value.  Thus we need some extra
	// variables -- maxvl (reference param) to maintain the current maximum obtained
	// and maxstore to maintain the items selected in the current maximum.  Also note
	// that instead of using the store in a binary way (1 and 0) we are maintaining
	// counts since each item can be selected multiple times.  Notice also that the
	// for loop within the function is almost the same as for subset sum, except that
	// the index starts at lvl instead of lvl + 1.  This allows the same item to be
	// selected recursively multiple times.   Finally, realize that since we are
	// trying to maximize the value, we don't know what our solution is until we are
	// finished (unlike subset sum).  Thus, we have to try all potential solutions
	// before stopping.
	public void knapsack(int lvl, int sum, int vl)
	{
		curstore[lvl]++;        // add another current item to store
		sum += size[lvl];       // increment sum
		vl += val[lvl];         // increment val

		if (sum <= M)
		{
			if (vl > maxval)     // if new maxval is found, update relevant variables
			{
				maxval = vl;
				for (int i = 1; i <= N; i++)
					maxstore[i] = curstore[i];
			}
			for (int i = lvl; i <= N; i++)        // start loop with current item
			{
				if (sum + size[i] <= M)        // only try an item if size will not
				{                              // go over M
					knapsack(i, sum, vl);
				}
			}
		}

		curstore[lvl]--;        // backtrack by decrementing count of cur. item
	}
	*/	
	
	
	
	
	
	/* Gentics Algorithms 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
