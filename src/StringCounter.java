import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class StringCounter {

    public static int stringCount(final String fileName) throws IOException{
        int stringCount = 0;

        FileInputStream file = new FileInputStream(fileName);
        InputStreamReader inputStream = new InputStreamReader(file);
        BufferedReader bufReader = new BufferedReader(inputStream);

        while (bufReader.readLine() != null)
            stringCount++;

        return stringCount;

    }
}
