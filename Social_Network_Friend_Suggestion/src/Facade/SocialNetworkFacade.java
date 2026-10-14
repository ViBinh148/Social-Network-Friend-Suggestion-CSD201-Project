
public class SocialNetworkFacade {

    private DLLStorage dllStorage;
    private BSTSearch bstSearch;

    
    public SocialNetworkFacade() {

    }

    
    public boolean addUser(int id, String name) {
        return false;
    }

    public boolean removeUser(int id) {
        return false;
    }

  
    public User findUserById(int id) {
        return null;
    }

    public void displayAllUsers() {

    }

    public boolean addFriendship(int id1, int id2) {
        return false;
    }


    public boolean removeFriendship(int id1, int id2) {
        return false;
    }

    public void displayFriendsOf(int id) {

    }

    public int countMutualFriends(int id1, int id2) {
        return 0;
    }

    public void displayMutualFriends(int id1, int id2) {

    }

    public FriendSuggestion[] suggestFriends(int id, int k) {
        return null;
    }

    private boolean isValidPair(int id1, int id2) {
        return false;
    }

    private void sortSuggestions(FriendSuggestion[] list) {

    }
}