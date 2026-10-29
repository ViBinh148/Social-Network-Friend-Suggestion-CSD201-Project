package algorithm;

import model.User;

public class FriendSuggestion implements Comparable<FriendSuggestion> {

    private User user;
    private int mutualCount;

    public FriendSuggestion(User user, int mutualCount) {
        this.user = user;
        this.mutualCount = mutualCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMutualCount() {
        return mutualCount;
    }

    public void setMutualCount(int mutualCount) {
        this.mutualCount = mutualCount;
    }

    // Thứ tự xếp hạng: nhiều bạn chung hơn đứng trước, bằng nhau thì ID nhỏ hơn đứng trước
    @Override
    public int compareTo(FriendSuggestion other) {
        if (this.mutualCount != other.mutualCount) {
            return Integer.compare(other.mutualCount, this.mutualCount);
        }
        return Integer.compare(this.user.getId(), other.user.getId());
    }

    @Override
    public String toString() {
        return user.getName() + " (ID " + user.getId() + ") - " + mutualCount + " bạn chung";
    }
}