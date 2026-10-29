
package files;

import facade.SocialNetworkFacade;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    // Đọc danh sách User từ file
    public static void loadUsers(SocialNetworkFacade facade) {
        try (BufferedReader br =
                new BufferedReader(new FileReader("data/users.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];

                facade.addUser(id, name, email);
            }

            System.out.println("Users loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    // Đọc quan hệ bạn bè từ file
    public static void loadFriendships(SocialNetworkFacade facade) {
        try (BufferedReader br =
                new BufferedReader(new FileReader("data/friendships.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                int id1 = Integer.parseInt(data[0]);
                int id2 = Integer.parseInt(data[1]);

                facade.addFriendship(id1, id2);
            }

            System.out.println("Friendships loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading friendships: " + e.getMessage());
        }
    }
}
