import java.io.*;
import java.util.Arrays;

/**
 * Для сортировки большого файлы используется метод qSort. из стандартных java библиотек
 * и имеет производительность O(nLogn)
 *
 * В качестве временного буфера мы выделям массив строчек в оперативной памяти размером M
 * В нашем случае 50 000 000 строк.
 *
 * Отсортировываем каждые 50 000 000 строк и записываем во временные файлы.
 *
 * Считываем поочередно все временные файлы и сравниваем самое первое значение,
 * после чего минимальное записывается в файл результата.
 *
 */

public class ExternalBufferSort {

    private int stringCount;

    static final int M = 500_000; // Размер буффера (измеряется в строках)

    private final String fileName;

    private final String resultFile;


    public ExternalBufferSort(String fileName, String resultFile) {
        this.fileName = fileName;
        this.resultFile = resultFile;
    }


    public void sortFile() throws IOException {
        stringCount = StringCounter.stringCount(fileName);
        int part = (int) Math.ceil((double) stringCount / M);
        stringCount = StringCounter.stringCount(fileName);
        fillBuffer(part);
        createLessArray(part);
    }
    private void fillBuffer(final int part) throws IOException{
        String[] buffer = new String[M < stringCount ? M : stringCount];
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);


        int i, j;
        i = j = 0;
        for (i = 0; i < part; i++) {
            for (j = 0; j < (M < stringCount ? M : stringCount); j++) {
                String t = br.readLine();
                if (t != null)
                    buffer[j] = t;
                else
                    break;
            }
            sortBuffer(buffer, j, i);
        }

        br.close();
        fr.close();
    }
    private void sortBuffer(String[] buffer, int index, int indexFile) throws IOException{
        Arrays.sort(buffer);
        writeTempFiles(buffer,index, indexFile);
    }
    private void writeTempFiles(String[] buffer, int index, int indexFile) throws IOException{
        String tempFile = "temp-file-";

        FileWriter fw = new FileWriter(tempFile + Integer.toString(indexFile) + ".txt");
        PrintWriter pw = new PrintWriter(fw);
        for (int k = 0; k < index; k++)
            pw.println(buffer[k]);

        pw.close();
        fw.close();
    }
    private void createLessArray(final int part) throws IOException{
        String tempFile = "temp-file-";
        String[] topNums = new String[part];
        BufferedReader[] brs = new BufferedReader[part];

        for (int i = 0; i < part; i++) {
            brs[i] = new BufferedReader(new FileReader(tempFile + Integer.toString(i) + ".txt"));
            String t = brs[i].readLine();
            if (t != null) {
                topNums[i] = t;
            }
        }
        File out = new File(resultFile);
        if (out.exists()) {
            out.delete();
        }
        mergeInResult(topNums,part,brs);
    }
    private void mergeInResult(String[] topNums, int part, BufferedReader[] brs) throws IOException{
        String tempFile = "temp-file-";
        FileWriter fw = new FileWriter(resultFile);
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < stringCount; i++) {
            String min = topNums[0];

            int minFile = 0;

            for (int j = 0; j < part; j++) {
                if (min.compareTo(topNums[j]) > 0) {
                    min = topNums[j];
                    minFile = j;
                }
            }
            pw.println(min);
            String t = brs[minFile].readLine();
            if (t != null)
                topNums[minFile] = t;
            else topNums[minFile] = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";

        }
        for (int i = 0; i < part; i++) {
            brs[i].close();
            File delete = new File(tempFile + Integer.toString(i) + ".txt");
            delete.delete();
        }
        pw.close();
        fw.close();
    }

}
