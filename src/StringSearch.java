import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class StringSearch {

    public static void stringSearch(String fileName, String subString) throws IOException{
        BufferedReader readFile = new BufferedReader(new FileReader(fileName));
        String tmpString;
        int stringCounter = 1;
        boolean flag = false;
        while ((tmpString = readFile.readLine()) != null)
        {
            if(tmpString.indexOf(subString) != -1){
                System.out.println("Подстрока " + subString + " найдена в строчке № " + stringCounter +
                        ", текст строки: " + tmpString);
                flag = true;
            }
            stringCounter++;
        }
        if (flag == false)
            System.out.println("Не найдено");
    }
}
