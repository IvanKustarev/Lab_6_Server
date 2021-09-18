package WorkWithConsole;

import java.util.Scanner;

public class ConsoleWorker {
    private Scanner scanner = new Scanner(System.in);
    public String read(){
        return scanner.nextLine();
    }
    public void write(String str){
        System.out.println(str);
    }
}
