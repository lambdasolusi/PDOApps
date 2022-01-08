package utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileServices {
    public static void saveObjectToFile(Object obj, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
        fos.close();
    }
    public static Object readObjectFromFile(String filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            fis.close();
            return obj;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
    public static List<String> readTxtFile(String filename){
        try{
            List<String> data = new ArrayList<>();
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                String str = scanner.nextLine();
                data.add(str);
            }
            return data;
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
