package fileStuff;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CreateFile {
	
	private static final String MESSAGE = "If you are seeing this you finally did something right";

	public static void main(String[] args) {
		 try {
		     File file = new File("/home/waford/test/newFile.txt");
		     /*If file gets created then the createNewFile() 
		      * method would return true or if the file is 
		      * already present it would return false
		      */
		     
	             boolean fvar = file.createNewFile();
	             FileWriter fw = new FileWriter(file);
	             BufferedWriter bw = new BufferedWriter(fw);
	             bw.write(MESSAGE);
	             bw.close();
		     if (fvar){
		          System.out.println("File has been created successfully");
		     }
		     else{
		          System.out.println("File already present at the specified location");
		     }
	    	} catch (IOException e) {
	    		System.out.println("Exception Occurred:");
		        e.printStackTrace();
		  }
	}

}
