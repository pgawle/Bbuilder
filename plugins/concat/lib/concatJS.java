import net.htmlparser.jericho.*;

import java.util.*;
import java.io.*;
import java.net.*;

import javax.xml.stream.events.Attribute;


public class concatJS {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if (args.length!=0){
			
			File file = new File(args[1]);
			
			//System.out.println(file);
			//System.out.println(args[1]);

			
			
			//File f = new File(args[0]);
			//File jsNew = new File("eee.txt");
			//minifyFolder(folder);
			//System.out.println(index);
			
			try {
				Source source=new Source(new URL("file:"+file.getPath()));
				
				List sriptList = source.getAllElements(HTMLElementName.SCRIPT);

				PrintWriter pw = new PrintWriter(new FileOutputStream(args[2]+"/BIT.all.js"));
								
				for (int i = 0; i < sriptList.size(); i++) {
					Element element = (Element)sriptList.get(i);
					
					if(element.isEmpty()){
						
						String[] value = element.getStartTag().getAttributes().get("src").getValue().split("http:");

						net.htmlparser.jericho.Attribute att = element.getStartTag().getAttributes().get("name");
						
						String name = "janek";
						
						if(att !=null){
								name = att.getValue();
						}

						//System.out.println(element);
						//System.out.println(args[0]+"/"+element.getStartTag().getAttributes().get("src").getValue());
						
						if(value.length < 2 && !name.equals("keep")){
							String jsFileUrl = args[0]+"/"+element.getStartTag().getAttributes().get("src").getValue();
							jsFileUrl = jsFileUrl.replace("//","/");
							jsFileUrl = jsFileUrl.replace("./","");
							//System.out.println(name);
							//System.out.println(jsFileUrl);
								
							
						File jsFile = new File(jsFileUrl);
						//System.out.println(jsFile);
	
						// System.out.println("Processing " + jsFile.getPath() + " (bzzzz) ");
                        BufferedReader br = new BufferedReader(new FileReader(jsFile
																			  .getPath()));
                        String line = br.readLine();
                        while (line != null) {
							pw.println(line);
							line = br.readLine();
                        }
                        br.close();

													//System.out.println(jsFile.getCanonicalPath());
						}
						//						System.out.println(element.getStartTag().getAttributes().get("src").getValue().split("\\./")[1]);
					}
					
							
				}
				pw.close();
				
			
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("wrong file path. The path you are trying to look is:");
				System.out.println(e);
			}
			
		
			
		}else{
			System.out.println("No css folder specyfied");
		}	
	}
	
	
	/*private static void minifyFolder(File folder) throws IOException{
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
					writer.write("//Copyright BushidoIT.\r\n");
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
	}*/
/*	private static void displaySegments(List<? extends Segment> segments) {
		for (Segment segment : segments) {
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println(segment.getDebugInfo());
			System.out.println(segment);
		}
		System.out.println("\n*******************************************************************************\n");
	}*/
	

}
