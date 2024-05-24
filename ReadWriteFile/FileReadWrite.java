package ReadWriteFile;

import java.io.*;
import java.util.Scanner;

public class FileReadWrite {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("ENTER 'R' TO READ A FILE, 'W' TO WRITE TO FILE OR 'E' TO EXIT THE PROGRAM");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        line = line.toLowerCase();


        while(!line.equals("w") && !line.equals("r")) {
            if(line.equals("e")) {
                System.out.println("Exiting program...");
                return;
            }
            System.out.println("PLEASE ENTER 'R' TO READ 'W' TO WRITE OR 'E' TO EXIT");
            line = scanner.nextLine();
            line = line.toLowerCase();
        }

        defineMethod(line);
    }

    private static void defineMethod(String line) throws InterruptedException {
        if(line.equals("r")) {
            read();
        } else {
            write();
        }
    }

    private static void write() throws InterruptedException {
        System.out.println("NOW CHOOSE WHAT YOU WANT TO DO");
        System.out.println("IF YOU WANT TO FULLY REWRITE FILE TYPE 'F'");
        System.out.println("IF YOU WANT TO APPEND TO FILE TYPE 'A'");

        Scanner console = new Scanner(System.in);
        String option = console.nextLine();
        option = option.toLowerCase();

        while(!option.equals("a") && !option.equals("f")) {
            System.out.println("PLEASE ENTER 'F' TO REWRITE AND 'A' TO APPEND");
            option = console.nextLine();
            option = option.toLowerCase();
        }

        File file = getFile();

        if(option.equals("a")) {
            try(FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
            {
                System.out.println("NOW ENTER THE MESSAGE YOU WANT TO APPEND");
                Scanner c = new Scanner(System.in);
                String message = c.nextLine();

                bufferedWriter.write(STR." \n\{message}");
            } catch (IOException e) {
                System.out.println("Something went wrong : " + e);
            }

            System.out.println();
            main(new String[] {});
        } else {
            try(FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter))
            {
                System.out.println("NOW TYPE WHAT YOU WANT TO BE IN FILE");
                Scanner c = new Scanner(System.in);
                String message = c.nextLine();

                bufferedWriter.write(message);
            } catch (IOException e) {
                System.out.println("Something went wrong : " + e);
            }

            System.out.println();
            main(new String[] {});
        }
    }

    private static void read() throws InterruptedException {
        System.out.println("PLEASE ENTER PATH");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        path = path.replace("\\", "\\\\");
        path = path.replace("\u202A", "");
        File file = new File(path);

        try(Scanner reader = new Scanner(file);) {
            StringBuilder line = new StringBuilder();

            while(reader.hasNextLine()) {
                line.append(reader.nextLine());
            }

            System.out.println(STR."\n\{line}");
        } catch (IOException e) {
            System.out.println(STR."Something went wrong : \{e}");
        }

        main(new String[] {});
    }

    private static File getFile() {
        System.out.println("PLEASE ENTER PATH");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        path = path.replace("\\", "\\\\");
        path = path.replace("\u202A", "");

        return new File(path);
    }
}