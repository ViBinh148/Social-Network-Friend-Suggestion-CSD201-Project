package ui;

import algorithm.FriendSuggestion;
import facade.SocialNetworkFacade;
import java.util.Scanner;
import model.User;

public class Main {

    public static void main(String[] args) {
        SocialNetworkFacade facade = new SocialNetworkFacade();
        Scanner sc = new Scanner(System.in);
        loadSampleData(facade);

        int choice;
        do {
            showMenu();
            choice = readInt(sc, "Chọn chức năng: ");
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
                    facade.displayFriendsOf(readInt(sc, "Nhập ID: "));
                    break;
                case 8:
                    handleMutualFriends(facade, sc);
                    break;
                case 9:
                    handleSuggest(facade, sc);
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
        sc.close();
    }

    public static void showMenu() {
        System.out.println();
        System.out.println("===== SOCIAL NETWORK FRIEND SUGGESTION =====");
        System.out.println("1. Thêm người dùng");
        System.out.println("2. Xóa người dùng");
        System.out.println("3. Tìm người dùng theo ID");
        System.out.println("4. Hiển thị tất cả người dùng");
        System.out.println("5. Kết bạn");
        System.out.println("6. Hủy kết bạn");
        System.out.println("7. Xem danh sách bạn bè");
        System.out.println("8. Xem bạn chung");
        System.out.println("9. Gợi ý kết bạn");
        System.out.println("0. Thoát");
    }

    public static void loadSampleData(SocialNetworkFacade f) {
        f.addUser(1, "An", "an@gmail.com");
        f.addUser(2, "Binh", "binh@gmail.com");
        f.addUser(3, "Chi", "chi@gmail.com");
        f.addUser(4, "Dung", "dung@gmail.com");
        f.addUser(5, "Em", "em@gmail.com");

        f.addFriendship(1, 2);
        f.addFriendship(1, 3);
        f.addFriendship(2, 4);
        f.addFriendship(3, 4);
        f.addFriendship(3, 5);
    }

    public static void handleAddUser(SocialNetworkFacade f, Scanner sc) {
        int id = readInt(sc, "Nhập ID: ");
        System.out.print("Nhập tên: ");
        String name = sc.nextLine().trim();
        System.out.print("Nhập email: ");
        String email = sc.nextLine().trim();
        if (f.addUser(id, name, email)) {
            System.out.println("Đã thêm người dùng.");
        } else {
            System.out.println("Thêm thất bại (ID đã tồn tại hoặc tên rỗng).");
        }
    }

    public static void handleRemoveUser(SocialNetworkFacade f, Scanner sc) {
        int id = readInt(sc, "Nhập ID cần xóa: ");
        if (f.removeUser(id)) {
            System.out.println("Đã xóa người dùng.");
        } else {
            System.out.println("Không tìm thấy người dùng có ID " + id + ".");
        }
    }

    public static void handleSearchUser(SocialNetworkFacade f, Scanner sc) {
        int id = readInt(sc, "Nhập ID cần tìm: ");
        User user = f.findUserById(id);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng có ID " + id + ".");
        } else {
            System.out.println(user);
        }
    }

    public static void handleAddFriend(SocialNetworkFacade f, Scanner sc) {
        int id1 = readInt(sc, "Nhập ID người thứ nhất: ");
        int id2 = readInt(sc, "Nhập ID người thứ hai: ");
        if (f.addFriendship(id1, id2)) {
            System.out.println("Kết bạn thành công.");
        } else {
            System.out.println("Kết bạn thất bại (ID không tồn tại, trùng nhau hoặc đã là bạn).");
        }
    }

    public static void handleRemoveFriend(SocialNetworkFacade f, Scanner sc) {
        int id1 = readInt(sc, "Nhập ID người thứ nhất: ");
        int id2 = readInt(sc, "Nhập ID người thứ hai: ");
        if (f.removeFriendship(id1, id2)) {
            System.out.println("Đã hủy kết bạn.");
        } else {
            System.out.println("Hủy kết bạn thất bại (ID không tồn tại hoặc chưa là bạn).");
        }
    }

    public static void handleMutualFriends(SocialNetworkFacade f, Scanner sc) {
        int id1 = readInt(sc, "Nhập ID người thứ nhất: ");
        int id2 = readInt(sc, "Nhập ID người thứ hai: ");
        if (f.findUserById(id1) == null || f.findUserById(id2) == null || id1 == id2) {
            System.out.println("Hai ID không hợp lệ.");
            return;
        }
        System.out.println("Số bạn chung: " + f.countMutualFriends(id1, id2));
        f.displayMutualFriends(id1, id2);
    }

    public static void handleSuggest(SocialNetworkFacade f, Scanner sc) {
        int id = readInt(sc, "Nhập ID người cần gợi ý: ");
        User user = f.findUserById(id);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng có ID " + id + ".");
            return;
        }
        int k = readInt(sc, "Số gợi ý tối đa: ");
        FriendSuggestion[] list = f.suggestFriends(id, k);
        if (list.length == 0) {
            System.out.println("Không có gợi ý nào cho " + user.getName() + ".");
            return;
        }
        System.out.println("Gợi ý kết bạn cho " + user.getName() + ":");
        for (int i = 0; i < list.length; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }
    }
}