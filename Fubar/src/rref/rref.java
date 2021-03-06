package rref;

public class rref {
	double[][] matrix;

	public rref(double[][] tmp) {
		matrix = tmp;
	}

	public boolean checkMatrix() { // checks if the matrix is the right
									// dimensions for rref
		int row = matrix.length;
		int col = matrix[0].length;
		if((col - 1) != row) {
			return false;
		}
		return true;
	}

	public void solve() {
		for (int i = 0; i < matrix.length; i++) {
			makeOne(i, i);
			makeZero(i, i);
		}
	}

	public double[][] getMatrix() {
		return matrix;
	}

	private void makeOne(int row, int col) {        //makes the specified value a one by dividing the entire row by that value 
		double tmp = matrix[row][col];
		for (int i = 0; i <  matrix[row].length; i++) {
			matrix[row][i] = matrix[row][i] / tmp;
		}
	}

	private void makeZero(int row, int col) { //makes each other value in the column a zero by subtracting 1*coefficent to make the value 0. This is applied to all other numbers in the row
		int length1 = matrix.length;
		for (int i = 0; i < length1; i++) {
			if(i == row){
				continue;
			}
			double coefficent = matrix[i][col];
			int length2 = matrix[row].length;
			for(int j = 0; j < length2; j++){
				matrix[i][j] = matrix[i][j] - ((matrix[row][j]) * coefficent);
			}

		}
	}

}
