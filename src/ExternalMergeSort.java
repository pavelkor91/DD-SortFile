
import java.io.*;
import java.nio.file.Files;

/**
 * При сортировке использовался стандартный алгоритм слиянием.
 *
 * В качестве временного буфера для сортировки используются 2 файла, поэтому затраты по
 * оперативной памяти минимальны. Затраты по использованию внешней памяти O(2N).
 *
 * Алгоритм работает за фиксированная время, которое составляет O(NlogN).
 *
 */
public class ExternalMergeSort {

    private int stringCount;

    private final String fileName;

    private final String resultFile;

    public ExternalMergeSort(final String fileName,final String resultFile) {
        this.fileName = fileName;
        this.resultFile = resultFile;
    }


    public void sort() throws IOException {

        stringCount = StringCounter.stringCount(fileName);

        String firstFileString;
        String secondFileString;
        createOutFile();

        int counter = 1;
        while (stringCount > counter) {
            separator(counter,resultFile);
            PrintWriter printWriter = new PrintWriter(resultFile);
            FileInputStream streamTemp1 = new FileInputStream("tempFile1");
            InputStreamReader inputStream1 = new InputStreamReader(streamTemp1);
            BufferedReader readerForTemp1 = new BufferedReader(inputStream1);
            firstFileString = readerForTemp1.readLine();

            FileInputStream streamTemp2 = new FileInputStream("tempFile2");
            InputStreamReader inputStream2 = new InputStreamReader(streamTemp2);
            BufferedReader readerForTemp2 = new BufferedReader(inputStream2);
            secondFileString = readerForTemp2.readLine();
            int i, j;
            while (firstFileString != null && firstFileString != null){
                i = 0;
                j = 0;
                while (i < counter && j < counter && firstFileString != null && secondFileString != null) {
                    if (firstFileString.compareTo(secondFileString) < 0) {
                        printWriter.println(firstFileString);
                        firstFileString = readerForTemp1.readLine();
                        i++;
                    } else {
                        printWriter.println(secondFileString);
                        secondFileString = readerForTemp2.readLine();
                        j++;
                    }
                }
                while (i < counter && firstFileString != null) {
                    printWriter.println(firstFileString);
                    firstFileString = readerForTemp1.readLine();
                    i++;
                }
                while (j < counter && secondFileString != null) {
                    printWriter.println(secondFileString);
                    secondFileString = readerForTemp2.readLine();
                    j++;
                }
            }
            while (firstFileString != null) {
                printWriter.println(firstFileString);
                firstFileString = readerForTemp1.readLine();
            }
            while (secondFileString != null) {
                printWriter.println(secondFileString);
                secondFileString = readerForTemp2.readLine();
            }
            readerForTemp1.close();
            readerForTemp2.close();
            printWriter.close();
            counter *= 2;
        }

    }
    private void separator(final int counter, final String resultFile) throws IOException{

        FileInputStream file = new FileInputStream(resultFile);
        InputStreamReader inputStream = new InputStreamReader(file);
        BufferedReader bufReader = new BufferedReader(inputStream);
        PrintWriter printWriter1 = new PrintWriter("tempFile1");
        PrintWriter printWriter2 = new PrintWriter("tempFile2");

        String tmp = bufReader.readLine();
        while (tmp != null){
            for (int i = 0; i < counter; i++){
                if(tmp != null) {
                    printWriter1.println(tmp);
                    tmp = bufReader.readLine();
                }
            }
            for (int i = 0; i < counter; i++){
                if(tmp != null) {
                    printWriter2.println(tmp);
                    tmp = bufReader.readLine();
                }
            }
        }
        bufReader.close();
        printWriter1.close();
        printWriter2.close();
    }

    private void createOutFile() throws IOException{

        File resultFile = new File(this.resultFile);
        Files.copy(new File(fileName).toPath(), resultFile.toPath());
    }

}
