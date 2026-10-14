package facade;

import model.User;
import algorithm.BSTSearch;
import Data_Structure.DLLStorage;
import algorithm.FriendSuggestion;

public class SocialNetworkFacade {

    private DLLStorage dllStorage;
    private BSTSearch bstSearch;

    
    public SocialNetworkFacade() {

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