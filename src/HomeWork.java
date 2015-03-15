import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class HomeWork {
    public static void main(String[] args) throws IOException {


        userInterface();


    }

    public static void userInterface() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.println("Введите команду  \"gen\" чтобы сгенерировать файл ");
            System.out.println("Введите команду  \"copy\" чтобы копировать  файл ");
            System.out.println("Введите команду  \"find\" чтобы найти символ в файле ");
            System.out.println("Введите команду  \"exit\" чтобы завершить программу");
            String command=scanner.nextLine();

            if(command.equals("gen")){
                System.out.println("Введите адрес файла в формате \"С://file.txt\" ");
                String address = scanner.nextLine();
                System.out.println("Сколько мегабайт сгенерировать? введите число");
                int amount=0;
                try {
                    amount = Integer.parseInt(scanner.nextLine());
                }catch (NumberFormatException e){
                    System.out.println("Ошибка, вы ввели строку");
                }
                generateFile(address, amount);

            }else if(command.equals("copy")){
                System.out.println("Введите адрес файла копируемого файла в формате \"С://file.txt\" ");
                String address1 = scanner.nextLine();
                System.out.println("Введите адрес файла в который бутет происходить копирование  в формате \"С://file.txt\" ");
                String address2 = scanner.nextLine();

                copy(address1,address2);

            }else if(command.equals("find")){
                System.out.println("Введите адрес файла  \"С://file.txt\" ");
                String address = scanner.nextLine();
                System.out.println("Введите символ или строку для поиска в файле");
                String symbol = scanner.nextLine();
                find(address,symbol);

            }else if(command.equals("exit")){
                break;

            }else{
                System.out.println("Такой команды не сущестует");
            }


            System.out.println();




        }

    }

    public static void generateFile(String address,int size) throws IOException {

        final int SIZE_MB =1048576;
        long bit=size*SIZE_MB;
        int counter =0;

        Random random = new Random();

        File file = new File(address);

        if (file.exists() && file.canWrite()) {
            OutputStream outStream = new FileOutputStream(address);
            String s;

            while (true) {


                s = Integer.toString(random.nextInt(1000000000)) + " ";

                outStream.write(s.getBytes());

                if (file.length() >= bit) {
                    break;
                }

                if(file.length()/SIZE_MB==counter){
                    System.out.println("Сгенерировано "+(counter+1)+" mb");
                    ++counter;
                }
            }
            outStream.close();
        } else System.out.println("Указан неверный путь к файлу!!!");


    }

    public static void find(String address, String x) throws IOException {


        int counter = 0;
        String list;

        File file = new File(address);

        if (file.exists() && file.canRead()) {
            System.out.println("Выполняется поиск...");

            Scanner sc = new Scanner(new File(address));

            while (sc.hasNext()) {

                list = (sc.next());

                if (list.contains(x)) {
                    counter++;
                }
            }
            sc.close();

            System.out.println("Символ " + x + " встречается в файле " + address + " " + counter + " раз.");
        } else System.out.println("Указан неверный путь к файлу!!!");

    }

    public static void copy(String address, String address2) throws IOException {

        InputStream inStream = new FileInputStream(address);
        OutputStream outputStream = new FileOutputStream(address2);

        System.out.println("Копирую "+address+" в "+address2);

        while (inStream.available()>0){

            int date = inStream.read();
            outputStream.write(date);

        }
        System.out.println("Копирование завершено");

        inStream.close();
        outputStream.close();
    }

}
