package fileStuff;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import chap1.BigIntFactorials;

public class WriteOutFactorials {

	private static final String FILENAME = "/home/waford/test/factorial.txt";
	private static final int LIMIT = 10000;
	private static BufferedWriter bw;
	private static FileWriter fw;
	private static List<BigInteger> factorials;

	public static void main(String[] args) {
		File fact = new File(FILENAME);
		getFactorial();
		try {
			fw = new FileWriter(fact);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < LIMIT; i++) {
				bw.write((i) + ": " + factorials.get(i).toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getFactorial() {
		factorials = BigIntFactorials.getCache(LIMIT);
		

	}
}
