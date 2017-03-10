import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void runMenu() throws IOException{
        int key = 0;
        String inputFile;
        String outPutFile;
        while (key != 4){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите цифру нужного пункта меню:");
        System.out.println("1. Сортировка классическим алгоритмом слияния:");
        System.out.println("2. Сортировка с использованием буффера:");
        System.out.println("3. Поиск подстроки:");
        System.out.println("4. Сгенерировать файл:");
        System.out.println("5. Выход:");
            key = Integer.parseInt(scanner.next());
            switch (key){
                case 1:{
                    long startTime = System.currentTimeMillis();
                    System.out.println("Введите имя файла, который нужно отсортировать:");
                    inputFile = scanner.next();
                    System.out.println("Введите имя файла, в который запишем результат:");
                    outPutFile = scanner.next();
                    ExternalMergeSort mergeSort = new ExternalMergeSort(inputFile,outPutFile);
                    mergeSort.sort();
                    System.out.println("Алгоритм выполнянлся " + (System.currentTimeMillis()-startTime) + " миллисекунд");
                    break;
                }
                case 2:{
                    long startTime = System.currentTimeMillis();
                    System.out.println("Введите имя файла, который нужно отсортировать:");
                    inputFile = scanner.next();
                    System.out.println("Введите имя файла, в который запишем результат:");
                    outPutFile = scanner.next();
                    ExternalBufferSort extSort = new ExternalBufferSort(inputFile,outPutFile);
                    extSort.sortFile();
                    System.out.println("Алгоритм выполнянлся " + (System.currentTimeMillis()-startTime) + " миллисекунд");
                    break;
                }
                case 3:{
                    System.out.println("Введите имя файла, в котором ищем подстроку:");
                    inputFile = scanner.next();
                    System.out.println("Введити подстроку, которую нужно найти:");
                    outPutFile = scanner.next();
                    StringSearch.stringSearch(inputFile,outPutFile);
                    break;
                }
                case 4:{
                    int stringCount;
                    int stringLenght;
                    System.out.println("Введите имя файла:");
                    inputFile = scanner.next();
                    System.out.println("Введите количество строк для записи в файл:");
                    stringCount =Integer.parseInt(scanner.next());
                    System.out.println("Введите максимальное количество символов в строке:");
                    stringLenght =Integer.parseInt(scanner.next());
                    FileGenerator newFile = new FileGenerator(inputFile,stringCount,stringLenght);
                    newFile.generate();
                    break;
                }
                case 5:{
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Незвестная команда, попробуйте еще раз");
                }
            }
        }
    }
}
