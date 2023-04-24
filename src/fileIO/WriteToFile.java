package fileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteToFile<T> {
    public void writeToFile(String path,List<T> t) {
        File file = new File(path);
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(t);
            outputStream.close();
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("Đối tượng chưa được ghi vào file");
        }
    }
}