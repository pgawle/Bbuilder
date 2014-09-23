import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;

import net.htmlparser.jericho.OutputDocument;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.SourceCompactor;


public class minifyHtml {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if (args.length!=0){
			File f = new File(args[0]);
			 try {
				Source source=new Source(new URL("file:"+f.getPath()));
				 source = new Source(new SourceCompactor(source).toString());
				 Writer out =new FileWriter(f);
				 OutputDocument outputDocument=new OutputDocument(source);
				 outputDocument.writeTo(out);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("wrong file path. The path you are trying to look is:");
				System.out.println(f.getCanonicalPath());
			}
		}else{
			System.out.println("No file specyfied");	
		}
	}
}
