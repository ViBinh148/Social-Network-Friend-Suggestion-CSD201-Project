
package ui;

import facade.SocialNetworkFacade;
import files.FileManager;
import java.util.Scanner;
import model.User;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SocialNetworkFacade facade = new SocialNetworkFacade();

        // Đọc dữ liệu từ file khi khởi động
        FileManager.loadUsers(facade);
        FileManager.loadFriendships(facade);

        int choice;

        do {
            showMenu();

            choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1:
                    handleAddUser(facade, sc);
                    break;

                case 2:
                    handleRemoveUser(facade, sc);
                    break;

                case 3:
                    handleSearchUser(facade, sc);
                    break;

                case 4:
                    facade.displayAllUsers();
                    break;

                case 5:
                    handleAddFriend(facade, sc);
                    break;

                case 6:
                    handleRemoveFriend(facade, sc);
                    break;

                case 7:
                    int id = readInt(sc, "Enter user ID: ");
                    facade.displayFriendsOf(id);
                    break;

                case 8:
                    handleMutualFriends(facade, sc);
                    break;

                case 9:
                    handleSuggest(facade, sc);
                    break;

                case 10:
                    // Lưu dữ liệu thủ công
                    FileManager.saveUsers(facade);
                    FileManager.saveFriendships(facade);
                    break;

                case 0:
                    // Tự động lưu dữ liệu trước khi thoát
                    FileManager.saveUsers(facade);
                    FileManager.saveFriendships(facade);

                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }

    // Hiển thị menu chính
    public static void showMenu() {
        System.out.println();
        System.out.println("===== SOCIAL NETWORK FRIEND SUGGESTION =====");
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Search User");
        System.out.println("4. Display All Users");
        System.out.println("5. Add Friendship");
        System.out.println("6. Remove Friendship");
        System.out.println("7. Display Friends");
        System.out.println("8. Display Mutual Friends");
        System.out.println("9. Suggest Friends");
        System.out.println("10. Save Data");
        System.out.println("0. Exit");
        System.out.println("============================================");
    }

    // Thêm User mới
    public static void handleAddUser(
            SocialNetworkFacade f, Scanner sc) {

        int id = readInt(sc, "Enter user ID: ");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if (f.addUser(id, name, email)) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Failed to add user.");
        }
    }

    // Xóa User theo ID
    public static void handleRemoveUser(
            SocialNetworkFacade f, Scanner sc) {

        int id = readInt(sc, "Enter user ID: ");

        if (f.removeUser(id)) {
            System.out.println("User removed successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // Tìm User theo ID
    public static void handleSearchUser(
            SocialNetworkFacade f, Scanner sc) {

        int id = readInt(sc, "Enter user ID: ");

        User user = f.findUserById(id);

        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User not found.");
        }
    }

    // Kết bạn giữa hai User
    public static void handleAddFriend(
            SocialNetworkFacade f, Scanner sc) {

        int id1 = readInt(sc, "Enter first user ID: ");
        int id2 = readInt(sc, "Enter second user ID: ");

        if (f.addFriendship(id1, id2)) {
            System.out.println("Friendship added successfully.");
        } else {
            System.out.println("Failed to add friendship.");
        }
    }

    // Hủy kết bạn giữa hai User
    public static void handleRemoveFriend(
            SocialNetworkFacade f, Scanner sc) {

        int id1 = readInt(sc, "Enter first user ID: ");
        int id2 = readInt(sc, "Enter second user ID: ");

        if (f.removeFriendship(id1, id2)) {
            System.out.println("Friendship removed successfully.");
        } else {
            System.out.println("Failed to remove friendship.");
        }
    }

    // Hiển thị bạn chung và số lượng bạn chung
    public static void handleMutualFriends(
            SocialNetworkFacade f, Scanner sc) {

        int id1 = readInt(sc, "Enter first user ID: ");
        int id2 = readInt(sc, "Enter second user ID: ");

        System.out.println("Mutual friends:");
        f.displayMutualFriends(id1, id2);

        int count = f.countMutualFriends(id1, id2);

        System.out.println("Total mutual friends: " + count);
    }

    // Gợi ý bạn bè dựa trên số lượng bạn chung
    public static void handleSuggest(
            SocialNetworkFacade f, Scanner sc) {

        int id = readInt(sc, "Enter user ID: ");
        int k = readInt(sc, "Enter number of suggestions: ");

        f.suggestFriends(id, k);
    }

    // Đọc số nguyên và xử lý nhập sai
    public static int readInt(Scanner sc, String prompt) {

        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
