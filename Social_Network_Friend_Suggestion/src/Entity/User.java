package Entity;

import java.util.List;

public class User{
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Integer> friendlist;

    public User(int id, String name, String email, String password, List<Integer> friendlist) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.friendlist = friendlist;
    }

    // Getters and setters
    public List<Integer> getFriendlist() {
        return friendlist;
    }

    public void setFriendlist(List<Integer> friendlist) {
        this.friendlist = friendlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Thêm bạn (chỉ lưu ID)
    public void addFriend(int friendId) {
        if (friendId != this.id && !friendlist.contains(friendId)) {
            friendlist.add(friendId);
        }
    }

    // Xóa bạn
    public void removeFriend(int friendId) {
        friendlist.remove(Integer.valueOf(friendId));
    }

    // check xem có phải là bạn hay không
    public boolean isFriend(int friendId) {
        return friendlist.contains(friendId);
    }

    // Đếm số bạn
    public int getFriendCount() {
        return friendlist.size();
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}