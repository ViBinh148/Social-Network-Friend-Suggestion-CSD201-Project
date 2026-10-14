package Algorithm;
import Model.User;


public class FriendSuggestion {

    private User user;
    private int mutualCount;


    public FriendSuggestion(User user, int mutualCount) {
        this.user = user;
        this.mutualCount = mutualCount;
    }
 
    public User getUser() {
        return this.user;
    }

    public int getMutualCount() {
        return this.mutualCount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMutualCount(int mutualCount) {
        this.mutualCount = mutualCount;
    }

    @Override
    public String toString() {
        return "FriendSuggestion{" +"user=" + user + ", mutualCount=" + mutualCount + '}';
    }
}