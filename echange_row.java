package pivot;

public class echange_row { 
	public static void exchange_row(double[][] M, int k, int l, int m, int n) {     
		if (k<=0 || l<=0 || k>n || l>n || k==l)   
			return;      	
		double tmp;      
		for (int j=0; j<n; j++)      	{      	
			tmp = M[k-1][j];      	
			M[k-1][j] = M[l-1][j];      
			M[l-1][j] = tmp;      	}	    
		}

}
