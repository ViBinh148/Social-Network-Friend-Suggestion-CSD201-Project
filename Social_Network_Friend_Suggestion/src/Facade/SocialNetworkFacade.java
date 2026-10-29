
package facade;

import algorithm.BSTSearch;
import algorithm.NodeBST;
import data_Structure.DLLStorage;
import data_Structure.NodeDLL;
import data_Structure.NodeFriend;
import model.User;

public class SocialNetworkFacade {

    private DLLStorage dllStorage;
    private BSTSearch bstSearch;

    public SocialNetworkFacade() {
        this.dllStorage = new DLLStorage();
        this.bstSearch = new BSTSearch();
    }

    public SocialNetworkFacade(DLLStorage dllStorage, BSTSearch bstSearch) {
        this.dllStorage = dllStorage;
        this.bstSearch = bstSearch;
    }

    public DLLStorage getDllStorage() {
        return dllStorage;
    }

    public void setDllStorage(DLLStorage dllStorage) {
        this.dllStorage = dllStorage;
    }

    public BSTSearch getBstSearch() {
        return bstSearch;
    }

    public void setBstSearch(BSTSearch bstSearch) {
        this.bstSearch = bstSearch;
    }

    // Thêm User vào DLL và BST
    public boolean addUser(int id, String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        if (bstSearch.search(id) != null) {
            return false;
        }

        User user = new User(id, name.trim(), email);

        if (!dllStorage.addUser(user)) {
            return false;
        }

        if (!bstSearch.insert(user)) {
            dllStorage.removeUser(id);
            return false;
        }

        return true;
    }

    // Xóa User khỏi tất cả danh sách và cấu trúc lưu trữ
    public boolean removeUser(int id) {
        if (findUserById(id) == null) {
            return false;
        }

        removeFromAllFriendLists(id);
        dllStorage.removeUser(id);
        bstSearch.delete(id);

        return true;
    }

    // Tìm User theo ID bằng BST
    public User findUserById(int id) {
        NodeBST node = bstSearch.search(id);

        if (node == null) {
            return null;
        }

        return node.getData();
    }

    // Hiển thị toàn bộ User bằng cách duyệt DLL
    public void displayAllUsers() {
        if (dllStorage.isEmpty()) {
            System.out.println("User list is empty.");
            return;
        }

        NodeDLL current = dllStorage.getHead();

        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    // Kết bạn hai chiều
    public boolean addFriendship(int id1, int id2) {
        if (!isValidPair(id1, id2)) {
            return false;
        }

        User u1 = findUserById(id1);
        User u2 = findUserById(id2);

        if (u1.getFriendlist().isFriend(id2)) {
            return false;
        }

        u1.getFriendlist().addFriendTail(u2);
        u2.getFriendlist().addFriendTail(u1);

        return true;
    }

    // Hủy kết bạn hai chiều
    public boolean removeFriendship(int id1, int id2) {
        if (!isValidPair(id1, id2)) {
            return false;
        }

        User u1 = findUserById(id1);
        User u2 = findUserById(id2);

        if (!u1.getFriendlist().isFriend(id2)) {
            return false;
        }

        u1.getFriendlist().removeFriend(u2);
        u2.getFriendlist().removeFriend(u1);

        return true;
    }

    // Hiển thị danh sách bạn bè của User
    public void displayFriendsOf(int id) {
        User user = findUserById(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Friends of " + user.getName() + ":");
        user.getFriendlist().traverseHead();
    }

    // Đếm số lượng bạn chung giữa hai User
    public int countMutualFriends(int id1, int id2) {
        if (!isValidPair(id1, id2)) {
            return 0;
        }

        User u1 = findUserById(id1);
        User u2 = findUserById(id2);

        int count = 0;
        NodeFriend current = u1.getFriendlist().getHead();

        while (current != null) {
            int friendId = current.getData().getId();

            if (u2.getFriendlist().isFriend(friendId)) {
                count++;
            }

            current = current.getNext();
        }

        return count;
    }

    // Hiển thị các User là bạn chung
    public void displayMutualFriends(int id1, int id2) {
        if (!isValidPair(id1, id2)) {
            System.out.println("Invalid user IDs.");
            return;
        }

        User u1 = findUserById(id1);
        User u2 = findUserById(id2);

        boolean found = false;
        NodeFriend current = u1.getFriendlist().getHead();

        while (current != null) {
            int friendId = current.getData().getId();

            if (u2.getFriendlist().isFriend(friendId)) {
                System.out.println(current.getData());
                found = true;
            }

            current = current.getNext();
        }

        if (!found) {
            System.out.println("No mutual friends found.");
        }
    }

    // Kiểm tra hai ID hợp lệ và không trùng nhau
    private boolean isValidPair(int id1, int id2) {
        return id1 != id2 && findUserById(id1) != null && findUserById(id2) != null;
    }

    // Kiểm tra ứng viên có tồn tại trong DLL tạm không
    private boolean containsCandidate(NodeFriend head, int id) {
        NodeFriend current = head;

        while (current != null) {
            if (current.getData().getId() == id) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    // Tìm ứng viên bằng cách duyệt bạn của bạn
    // Sau đó dùng Insertion Sort để sắp xếp gợi ý
    public void suggestFriends(int id, int k) {
        User user = findUserById(id);

        if (user == null || k <= 0) {
            System.out.println("Invalid user or suggestion limit.");
            return;
        }

        NodeFriend candidateHead = null;
        NodeFriend candidateTail = null;

        // Duyệt các bạn bè trực tiếp
        NodeFriend friend = user.getFriendlist().getHead();

        while (friend != null) {

            // Duyệt bạn bè của từng người bạn
            NodeFriend fof = friend.getData().getFriendlist().getHead();

            while (fof != null) {
                User candidate = fof.getData();
                int candidateId = candidate.getId();

                // Bỏ qua chính mình, bạn hiện tại và ứng viên trùng
                if (candidateId != id
                        && !user.getFriendlist().isFriend(candidateId)
                        && !containsCandidate(candidateHead, candidateId)) {

                    NodeFriend newNode = new NodeFriend(candidate);

                    // Thêm ứng viên vào cuối DLL tạm
                    if (candidateHead == null) {
                        candidateHead = newNode;
                        candidateTail = newNode;
                    } else {
                        candidateTail.setNext(newNode);
                        newNode.setPrev(candidateTail);
                        candidateTail = newNode;
                    }
                }

                fof = fof.getNext();
            }

            friend = friend.getNext();
        }

        if (candidateHead == null) {
            System.out.println("No friend suggestions found.");
            return;
        }

        // Sắp xếp danh sách ứng viên bằng Insertion Sort
        sortSuggestions(candidateHead, id);

        // Hiển thị tối đa k ứng viên sau khi sắp xếp
        System.out.println("Friend suggestions:");

        NodeFriend current = candidateHead;
        int displayed = 0;

        while (current != null && displayed < k) {
            int mutualCount = countMutualFriends(
                    id, current.getData().getId());

            System.out.println(current.getData() + " | Mutual friends: " + mutualCount);

            current = current.getNext();
            displayed++;
        }
    }

    // Insertion Sort trên DLL tạm
    // Sắp xếp giảm dần theo số bạn chung
    // Nếu bằng nhau thì ưu tiên User có ID nhỏ hơn
    private void sortSuggestions(NodeFriend head, int userId) {

        if (head == null) {
            return;
        }

        NodeFriend current = head.getNext();

        while (current != null) {
            NodeFriend temp = current;

            // So sánh với các node đứng trước
            while (temp.getPrev() != null) {
                User currentUser = temp.getData();
                User previousUser = temp.getPrev().getData();

                int currentCount = countMutualFriends(userId, currentUser.getId());

                int previousCount = countMutualFriends(userId, previousUser.getId());

                // Đổi vị trí dữ liệu nếu thứ tự chưa đúng
                if (currentCount > previousCount || (currentCount == previousCount && currentUser.getId() < previousUser.getId())) {
                    temp.setData(previousUser);
                    temp.getPrev().setData(currentUser);

                    temp = temp.getPrev();
                } else {
                    break;
                }
            }

            current = current.getNext();
        }
    }

    // Xóa User khỏi danh sách bạn bè của các User khác
    private void removeFromAllFriendLists(int id) {
        User user = findUserById(id);

        if (user == null) {
            return;
        }

        NodeDLL current = dllStorage.getHead();

        while (current != null) {
            User other = current.getData();

            if (other.getId() != id) {
                other.getFriendlist().removeFriend(user);
            }

            current = current.getNext();
        }
    }
}
