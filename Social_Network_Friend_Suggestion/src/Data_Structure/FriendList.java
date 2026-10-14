package Data_Structure;

import Model.User;

public class FriendList {
    private NodeFriend head;
    private NodeFriend tail;
    private int size;

    public FriendList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public NodeFriend getHead() {
        return head;
    }

    public void setHead(NodeFriend head) {
        this.head = head;
    }

    public NodeFriend getTail() {
        return tail;
    }

    public void setTail(NodeFriend tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFriend(User data) {

    }

    public void removeFriend(User data) {
 
    }

    public void traverse() {

    }

    public void traverseReverse() {

    }

    public boolean isFriend(int userId) {
        return findFriend(userId) != null;
    }

    public User findFriend(int userId) {
        return null;
    }

}
