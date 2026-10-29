
package files;

import facade.SocialNetworkFacade;
import data_Structure.NodeDLL;
import data_Structure.NodeFriend;
import model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    // Đọc danh sách User từ file
    public static boolean loadUsers(SocialNetworkFacade facade) {
        try (BufferedReader br = Files.newBufferedReader(
                Paths.get("data/users.txt"), StandardCharsets.UTF_8)) {

            String line;

            while ((line = br.readLine()) != null) {

                // Bỏ qua dòng trống
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                // Kiểm tra số cột
                if (data.length != 3) {
                    System.out.println("Invalid user data: " + line);
                    return false;
                }

                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                String email = data[2].trim();

                // Thêm User thông qua Facade
                if (!facade.addUser(id, name, email)) {
                    System.out.println("Cannot load user ID: " + id);
                    return false;
                }
            }

            System.out.println("Users loaded successfully.");
            return true;

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return false;
        }
    }

    // Đọc quan hệ bạn bè từ file
    public static boolean loadFriendships(SocialNetworkFacade facade) {
        try (BufferedReader br = Files.newBufferedReader(
                Paths.get("data/friendships.txt"), StandardCharsets.UTF_8)) {

            String line;

            while ((line = br.readLine()) != null) {

                // Bỏ qua dòng trống
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|", -1);

                // Kiểm tra số cột
                if (data.length != 2) {
                    System.out.println("Invalid friendship data: " + line);
                    return false;
                }

                int id1 = Integer.parseInt(data[0].trim());
                int id2 = Integer.parseInt(data[1].trim());

                // Thêm quan hệ bạn bè thông qua Facade
                if (!facade.addFriendship(id1, id2)) {
                    System.out.println("Cannot load friendship: " + id1 + "|" + id2);
                    return false;
                }
            }

            System.out.println("Friendships loaded successfully.");
            return true;

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading friendships: " + e.getMessage());
            return false;
        }
    }

    // Lưu toàn bộ User từ DLL xuống file
    public static void saveUsers(SocialNetworkFacade facade) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get("data/users.txt"), StandardCharsets.UTF_8)) {

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
        try (BufferedWriter bw = Files.newBufferedWriter(
                Paths.get("data/friendships.txt"), StandardCharsets.UTF_8)) {

            NodeDLL current = facade.getDllStorage().getHead();

            while (current != null) {
                User user = current.getData();
                NodeFriend friend = user.getFriendlist().getHead();

                while (friend != null) {
                    int id1 = user.getId();
                    int id2 = friend.getData().getId();

                    // Chỉ ghi một lần cho mỗi quan hệ hai chiều
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
