import java.util.*;
import java.io.*;

class Ex5_C {
    static MyHashC hashC;

    private static void initialize(String filename){
        try {
            FileReader inFile = new FileReader(filename);
            BufferedReader inBuffer = new BufferedReader(inFile);

            String line;
            while ((line = inBuffer.readLine()) != null) {
                commandExecute(line);
            }
            inBuffer.close();
            inFile.close();
        }catch(IOException e){
            System.out.println("Exception :" + e);
        }
    }

    private static boolean commandExecute(String line){
        StringTokenizer token = new StringTokenizer(line, " ");
        int tokenCount = token.countTokens();
        if (tokenCount > 0){
            String command = new String(token.nextToken());
            if       (command.equals("insert") && tokenCount > 2 ){
                String newKey        = new String(token.nextToken());
                String newData = "";
                while(token.hasMoreTokens()){
                    newData = newData + token.nextToken() + " ";
                }
                MyKeyword newKeyword = new MyKeyword(newKey, newData);
                hashC.insert(newKeyword);
            }else if (command.equals("delete") && tokenCount == 2){
                String deleteKey   = new String(token.nextToken());
                MyKeyword result = null;
                result = hashC.delete(deleteKey);
                if (result != null){
                    System.out.println("  " + result + " deleted.");
                }else{
                    System.out.println("  not deleted.");
                }
            }else if (command.equals("search") && tokenCount == 2){
                String searchKey   = token.nextToken();
                MyKeyword result = hashC.search(searchKey);
                if (result != null){
                    System.out.println("  key (" + searchKey + ") is found.");
                    System.out.println("  " + result.toStringAll());
                }else{
                    System.out.println("  key (" + searchKey + ") is not found.");
                }
            }else if (command.equals("print") && tokenCount == 1){
                System.out.println("" + hashC);
            }else if (command.equals("quit") && tokenCount == 1){
                System.out.println("  quit.");
                return false;
            }else{
                System.out.println("  don't understand your command: " + line);
            }
        }else{
            System.out.println("  don't understand your command: " + line);
        }
        return true;
    }

    public static void main(String argv[]) {
        hashC = new MyHashC(100);
        if (argv.length > 0){
            for(int i = 0; i < argv.length; i++){
                long startTime = System.currentTimeMillis();
                initialize(argv[i]);
                long endTime = System.currentTimeMillis();
                System.out.printf("  %7.3f[s] for reading %s.\n",
                                  (endTime - startTime)/1000.0, argv[i]);
            }
        }
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.print("command: ");
                String line = br.readLine();
                if (line == null) break;
                if (commandExecute(line) == false) break;
            }
            br.close();
        }catch(IOException e){
            System.out.println("Exception :" + e);
        }
    }
}
