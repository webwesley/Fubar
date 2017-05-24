package rref;

import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] tmp = { {2 ,-1, 5, 1 , -3}, {3, 2, 2, -6, -32}, {1, 3, 3, -1, -47} , {5, -2, -3, 3, 49}};
		rref test = new rref(tmp);
		if(!test.checkMatrix()){
			System.out.println("Wrong Size");
		} else {
			test.solve();
			for(int i = 0; i < test.getMatrix().length; i++)
			System.out.println(Arrays.toString(test.getMatrix()[i]));	
		}
		
	}

}
