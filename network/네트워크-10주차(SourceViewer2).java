import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

	public static void main(String[] args) {
			String url="http://cyber.hanbat.ac.kr";
			try {
				// Open the URLConnection for reading
				URL u = new URL(url);
				URLConnection uc = u.openConnection();
				try (InputStream raw = uc.getInputStream()) { // autoclose
					InputStream buffer = new BufferedInputStream(raw);
					// chain the InputStream to a Reader
					Reader reader = new InputStreamReader(buffer,"utf-8");
					Reader reader2 = new InputStreamReader(buffer);
					int c;
					while ((c = reader.read()) != -1) {
						System.out.print((char) c);
					}
				}
			} catch (MalformedURLException ex) {
				System.err.println(args[0] + " is not a parseable URL");
			} catch (IOException ex) {
				System.err.println(ex);
			}
		

	}

}
