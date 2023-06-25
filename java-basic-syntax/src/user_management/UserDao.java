package user_management;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String filename;
    public UserDao(String filename) {
        this.filename = filename;
    }

    public void saveUser(List<User> list) {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getUsers() {
        File file = new File(filename);
        if(!file.exists()) {
            return new ArrayList<>();
        }

        List<User> list = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            list = (List<User>) in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
