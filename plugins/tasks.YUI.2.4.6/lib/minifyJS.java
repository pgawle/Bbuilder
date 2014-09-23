import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;


public class minifyJS {

    /**
    * @param args
    * @throws IOException
    */
    public static void main(String[] args) throws IOException {
        if (args.length!=0){
			File folder = new File(args[0]);

			minifyFolder(folder);
           
        }else{
            System.out.println("No js folder specyfied");
        }

    }
	
	
	private static void minifyFolder(File folder) throws IOException{

		String fileName;
		
		File[] listOfFiles = folder.listFiles();
		try{
			for (int i = 0; i < listOfFiles.length; i++)
			{
                if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith("js"))
                {
                    FileReader reader = new FileReader(listOfFiles[i]);
                    BufferedReader br = new BufferedReader(reader);
                    StringBuilder js = new StringBuilder();
                    String line = null;
                    while((line = br.readLine()) != null) {
                        js.append(line);
                        js.append(System.getProperty("line.separator"));
                    }
                    reader.close();
                    String compressedJS = minifyJS(js.toString());
                    char buffer[] = new char[compressedJS.length()];
                    compressedJS.getChars(0, compressedJS.length(), buffer, 0);
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
			System.out.println("wrong js folder. The folder you are trying to look is:");
			System.out.println(folder.getCanonicalPath());
		}
	
	
	}

	
	
    private  static String minifyJS(String js) throws IOException
    {

        StringReader reader = new StringReader(js);
        JavaScriptCompressor jsCompressor = new JavaScriptCompressor(reader,new ErrorReporter() {

            public void warning(String message, String sourceName,
                    int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("\n[WARNING] " + message);
                } else {
                    System.err.println("\n[WARNING] " + line + ':'
                            + lineOffset + ':' + message);
                }
            }

            public void error(String message, String sourceName,
                    int line, String lineSource, int lineOffset) {
                if (line < 0) {
                    System.err.println("\n[ERROR] " + message);
                } else {
                    System.err.println("\n[ERROR] " + line + ':'
                            + lineOffset + ':' + message);
                }
            }

            public EvaluatorException runtimeError(String message,
                    String sourceName, int line, String lineSource,
                    int lineOffset) {
                error(message, sourceName, line, lineSource, lineOffset);
                return new EvaluatorException(message);
            }
        });
        StringWriter writer = new StringWriter();
        jsCompressor.compress(writer, -1, true, false, true, false);
        String s = writer.toString();
        return s;
    }

}
