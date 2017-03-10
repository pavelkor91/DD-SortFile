import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Класс создает файл с указанным количеством строк и заполняет его случайными символами
 */

public class FileGenerator {

    private String fileName;

    private int stringCount;

    private  int maxStringSize;

    public FileGenerator(String fileName, int stringCount, int maxStringSize) {
        this.fileName = fileName;
        this.stringCount = stringCount;
        this.maxStringSize = maxStringSize;
    }

    public void generate() throws IOException{
        char[] listOfSymbols = new char[]{
                'Q','W','E','R',
                'T','Y','U','I',
                'O','P','A','S',
                'D','F','G','H',
                'J','K','L','Z',
                'X','C','V','B',
                'N','M','1','2',
                '3','4','5','6',
                '7','8','9',' ',
                'q','w','e','a',
        };

        PrintWriter fWrite = new PrintWriter(fileName);

        Random randomString = new Random();

        for (int i = 0; i < stringCount; i++){
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < randomString.nextInt(maxStringSize); j++){
                sb.append(listOfSymbols[randomString.nextInt(listOfSymbols.length)]);
            }
            fWrite.println(sb);
        }

        System.out.print("Файл "+fileName+" успешно создан");
        fWrite.close();
    }
}
