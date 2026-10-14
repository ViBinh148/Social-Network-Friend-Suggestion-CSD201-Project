package data_Structure;

import model.User;

public class NodeFriend {
    private User data;
    private NodeFriend next;
    private NodeFriend prev;    

    public NodeFriend(User data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public User getData() {
        return data;
    }

    public NodeFriend getNext() {
        return next;
    }

    public NodeFriend getPrev() {
        return prev;
    }

    public void setData(User data) {
        this.data = data;
    }

    public void setNext(NodeFriend next) {
        this.next = next;
    }

    public void setPrev(NodeFriend prev) {
        this.prev = prev;
    }
    //tostring
    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}
