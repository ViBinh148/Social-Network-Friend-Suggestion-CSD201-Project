package Model;

import java.util.List;

public class User{
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Integer> friendids;

    public User(int id, String name, String email, String password, List<Integer> friendids) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.friendids = friendids;
    }

    // Getters and setters
    public List<Integer> getFriendids() {
        return friendids;
    }

    public void setFriendids(List<Integer> friendids) {
        this.friendids = friendids;
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

    @Override
    public String toString() {
        return "User id=" + id + ", name= " + name + ", email= " + email;
    }
}