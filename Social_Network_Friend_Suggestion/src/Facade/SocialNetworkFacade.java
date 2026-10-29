package facade;

import algorithm.BSTSearch;
import algorithm.FriendSuggestion;
import algorithm.NodeBST;
import data_Structure.DLLStorage;
import data_Structure.NodeFriend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // Thêm user vào cả DLL và BST, từ chối nếu ID đã tồn tại
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

    // Gỡ user khỏi mọi danh sách bạn bè, rồi xóa khỏi DLL và BST
    public boolean removeUser(int id) {
        if (findUserById(id) == null) {
            return false;
        }
        removeFromAllFriendLists(id);
        dllStorage.removeUser(id);
        bstSearch.delete(id);
        return true;
    }

    public User findUserById(int id) {
        NodeBST node = bstSearch.search(id);
        return node == null ? null : node.getData();
    }

    public void displayAllUsers() {
        List<User> users = dllStorage.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Danh sách người dùng đang trống.");
            return;
        }
        for (User u : users) {
            System.out.println(u);
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

    public void displayFriendsOf(int id) {
        User user = findUserById(id);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng có ID " + id + ".");
            return;
        }
        System.out.println("Bạn bè của " + user.getName() + ":");
        user.getFriendlist().traverseHead();
    }

    // Đếm bạn chung: với mỗi bạn của id1, kiểm tra có là bạn của id2 không
    public int countMutualFriends(int id1, int id2) {
        if (!isValidPair(id1, id2)) {
            return 0;
        }
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        int count = 0;
        NodeFriend current = u1.getFriendlist().getHead();
        while (current != null) {
            if (u2.getFriendlist().isFriend(current.getData().getId())) {
                count++;
            }
            current = current.getNext();
        }
        return count;
    }

    public void displayMutualFriends(int id1, int id2) {
        if (!isValidPair(id1, id2)) {
            System.out.println("Hai ID không hợp lệ.");
            return;
        }
        User u1 = findUserById(id1);
        User u2 = findUserById(id2);
        boolean found = false;
        NodeFriend current = u1.getFriendlist().getHead();
        while (current != null) {
            if (u2.getFriendlist().isFriend(current.getData().getId())) {
                System.out.println(current.getData());
                found = true;
            }
            current = current.getNext();
        }
        if (!found) {
            System.out.println("Hai người không có bạn chung.");
        }
    }

    // Trả về tối đa k người có nhiều bạn chung nhất, xếp hạng giảm dần
    public FriendSuggestion[] suggestFriends(int id, int k) {
        if (findUserById(id) == null || k <= 0) {
            return new FriendSuggestion[0];
        }
        Map<Integer, Integer> candidates = collectCandidates(id);
        List<FriendSuggestion> found = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : candidates.entrySet()) {
            User candidate = findUserById(entry.getKey());
            if (candidate != null) {
                found.add(new FriendSuggestion(candidate, entry.getValue()));
            }
        }
        FriendSuggestion[] result = found.toArray(new FriendSuggestion[0]);
        sortSuggestions(result);
        if (result.length <= k) {
            return result;
        }
        FriendSuggestion[] top = new FriendSuggestion[k];
        for (int i = 0; i < k; i++) {
            top[i] = result[i];
        }
        return top;
    }

    private boolean isValidPair(int id1, int id2) {
        return id1 != id2 && findUserById(id1) != null && findUserById(id2) != null;
    }

    // Duyệt "bạn của bạn": mỗi lần một người ngoài (chưa là bạn) xuất hiện qua một bạn chung thì cộng 1.
    // Trả về map: ID ứng viên -> số bạn chung
    private Map<Integer, Integer> collectCandidates(int id) {
        Map<Integer, Integer> candidates = new HashMap<>();
        User user = findUserById(id);
        if (user == null) {
            return candidates;
        }
        NodeFriend friend = user.getFriendlist().getHead();
        while (friend != null) {
            NodeFriend fof = friend.getData().getFriendlist().getHead();
            while (fof != null) {
                int candidateId = fof.getData().getId();
                if (candidateId != id && !user.getFriendlist().isFriend(candidateId)) {
                    candidates.merge(candidateId, 1, Integer::sum);
                }
                fof = fof.getNext();
            }
            friend = friend.getNext();
        }
        return candidates;
    }

    // Gỡ user khỏi danh sách bạn của tất cả người dùng còn lại
    private void removeFromAllFriendLists(int id) {
        User user = findUserById(id);
        if (user == null) {
            return;
        }
        for (User other : dllStorage.getAllUsers()) {
            if (other.getId() != id) {
                other.getFriendlist().removeFriend(user);
            }
        }
    }

    // Insertion sort theo compareTo của FriendSuggestion
    private void sortSuggestions(FriendSuggestion[] list) {
        for (int i = 1; i < list.length; i++) {
            FriendSuggestion key = list[i];
            int j = i - 1;
            while (j >= 0 && key.compareTo(list[j]) < 0) {
                list[j + 1] = list[j];
                j--;
            }
            list[j + 1] = key;
        }
    }
}