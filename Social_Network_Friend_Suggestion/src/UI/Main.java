package ui;

import java.util.Scanner;
import facade.SocialNetworkFacade;

//main class to run the program
public class Main {


<<<<<<< Updated upstream
    public static void main(String[] args) {

=======
        

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
>>>>>>> Stashed changes
    }

    public static void showMenu() {

    }

    public static void loadSampleData(SocialNetworkFacade f) {

    }

    public static void handleAddUser(SocialNetworkFacade f, Scanner sc) {

    }

    public static void handleRemoveUser(SocialNetworkFacade f, Scanner sc) {

    }

    public static void handleSearchUser(SocialNetworkFacade f, Scanner sc) {

    }

    public static void handleAddFriend(SocialNetworkFacade f, Scanner sc) {

    }

    public static void handleRemoveFriend(SocialNetworkFacade f, Scanner sc) {

    }

    public static void handleMutualFriends(SocialNetworkFacade f, Scanner sc) {

    }

    public static void handleSuggest(SocialNetworkFacade f, Scanner sc) {

    }

    public static int readInt(Scanner sc, String prompt) {
        return 0;
    }
}