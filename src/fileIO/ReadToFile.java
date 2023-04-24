package fileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadToFile<T> {
    public List<T> readToFile(String path) {

        List<T> list = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            list = (List<T>) objectInputStream.readObject();
            inputStream.close();
            objectInputStream.close();
        } catch (Exception  e) {
            System.out.println("Chưa có đối tượng khởi tạo trong file");
        }
        return list;
    }

}
