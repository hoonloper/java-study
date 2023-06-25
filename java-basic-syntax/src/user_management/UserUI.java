package user_management;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UserUI {
    private BufferedReader br;

    public UserUI() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public int menu() {
        System.out.println("1. 회원 등록");
        System.out.println("2. 회원 목록보기");
        System.out.println("5. 종료");
        int menuId = -1;
        try {
            String line = br.readLine();
            menuId = Integer.parseInt(line);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return menuId;
    }

    public User regUser() {
        try {
            System.out.println("email을 입력하세요.");
            String email = br.readLine();
            System.out.println("이름을 입력하세요.");
            String name = br.readLine();
            System.out.println("생년을 입력하세요.");
            String strBirtyYear = br.readLine();
            int birthYear = Integer.parseInt(strBirtyYear);

            return new User(email, name, birthYear);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void printUserList(List<User> users) {
        System.out.println("email         이름         생년");
        System.out.println("=============================");
        for (User user : users) {
            System.out.print(user.getEmail());
            System.out.print("         ");
            System.out.print(user.getName());
            System.out.print("         ");
            System.out.println(user.getBirthYear());
        }
    }
}
