import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Url {
    public static void main(String[] args) throws IOException, MimeTypeParseException {
        URL url = new URL("http://lib.ru");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String ct = connection.getHeaderField("Content-Type");
        MimeType mt = new MimeType(ct);
        String cs = mt.getParameter("charset");
        try (InputStream is = connection.getInputStream()){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (true){
                int c = is.read();
                if (c<0)
                    break;
                bos.write(c);
            }
            System.out.println(bos.toString(cs));
        }
        connection.disconnect();
    }

}
