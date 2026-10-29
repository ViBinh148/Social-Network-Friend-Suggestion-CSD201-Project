
package files;

import facade.SocialNetworkFacade;
import data_Structure.NodeDLL;
import data_Structure.NodeFriend;
import model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
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

    // Lưu danh sách User từ DLL xuống file
    public static void saveUsers(SocialNetworkFacade facade) {
        try (BufferedWriter bw =
                new BufferedWriter(new FileWriter("data/users.txt"))) {

            NodeDLL current = facade.getDllStorage().getHead();

            while (current != null) {

                User user = current.getData();

                bw.write(user.getId() + "|" + user.getName() + "|" + user.getEmail());

                bw.newLine();

                current = current.getNext();
            }

            System.out.println("Users saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Lưu quan hệ bạn bè từ FriendList xuống file
    public static void saveFriendships(SocialNetworkFacade facade) {
        try (BufferedWriter bw =
                new BufferedWriter(new FileWriter("data/friendships.txt"))) {

            NodeDLL current = facade.getDllStorage().getHead();

            while (current != null) {

                User user = current.getData();

                NodeFriend friend = user.getFriendlist().getHead();

                while (friend != null) {

                    int id1 = user.getId();
                    int id2 = friend.getData().getId();

                    // Chỉ lưu một lần cho mỗi quan hệ hai chiều
                    if (id1 < id2) {
                        bw.write(id1 + "|" + id2);
                        bw.newLine();
                    }

                    friend = friend.getNext();
                }

                current = current.getNext();
            }

            System.out.println("Friendships saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving friendships: " + e.getMessage());
        }
    }
}
