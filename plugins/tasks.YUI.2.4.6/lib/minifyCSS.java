import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import com.yahoo.platform.yui.compressor.CssCompressor;


public class minifyCSS {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if (args.length!=0){
			
			 File folder = new File(args[0]);
			minifyFolder(folder);
			
		}else{
			System.out.println("No css folder specyfied");
		}	
	}
	
	
	private static void minifyFolder(File folder) throws IOException{
		String fileName;

	
		File[] listOfFiles = folder.listFiles(); 
		try{
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith("css")) 
				{
					FileReader reader = new FileReader(listOfFiles[i]);
					BufferedReader br = new BufferedReader(reader); 
					StringBuilder css = new StringBuilder();
					String line = null;
					while((line = br.readLine()) != null) {
						css.append(line);
						css.append(System.getProperty("line.separator"));
					}
					reader.close();
					String compressedCSS = comprCSS(css.toString());
					char buffer[] = new char[compressedCSS.length()];
					compressedCSS.getChars(0, compressedCSS.length(), buffer, 0);
					FileWriter writer = new FileWriter(listOfFiles[i]);
					for (int j=0; j < buffer.length; j++) {
						writer.write(buffer[j]);
					}
					writer.close(); 
					fileName = listOfFiles[i].getName();
					System.out.println("minifying :"+fileName);
				}else if(listOfFiles[i].isDirectory()){
					minifyFolder(listOfFiles[i]);
				}
				
			}
		}catch (Exception e) {
			System.out.println("wrong css folder. The folder you are trying to look is:");
			System.out.println(folder.getCanonicalPath());
		}
	
	
	}
	
	
	private  static String comprCSS(String css) throws IOException
	{
		StringReader reader = new StringReader(css);
		CssCompressor cssCompressor = new CssCompressor(reader);
		StringWriter writer = new StringWriter();
		cssCompressor.compress(writer, -1);
		String s = writer.toString();
		return s;
	}
	

}
