PHÂN CHIA LẠI CÔNG VIỆC — PHASE 1 (DECOMPOSITION)

docx: https://docs.google.com/document/d/1NvRyzy36-N4hQsN6YBcYttN6t8ti-aUuGf1e2KEraWo/edit?usp=sharing

1. BẢNG PHÂN CÔNG MỚI
2. CHI TIẾT TỪNG MODULE
🧑‍💻 VINH — USER ENTITY
Classes cần thiết kế:
1. User:
   - id (int), name (String), friendList (FriendList)
   - getId(), getName(), setName(), getFriendList()

2. FriendList:
   - head (Node), tail (Node), size (int)
   - addFriend(), removeFriend(), isFriend()
   - getFriends(), traverse(), getSize()



🧑‍💻 THẮNG — DLL STORAGE (MỚI)
Classes cần thiết kế:
1. NodeDLL:
   - data (User), next (NodeDLL), prev (NodeDLL)
   - Constructor: NodeDLL(User data)

2. DLLStorage:
   - head (NodeDLL), tail (NodeDLL), size (int)
   - addUser(), removeUser(), searchByID()
   - traverse(), isEmpty(), clear(), getSize()



🧑‍💻 SANG — BST SEARCH
Classes cần thiết kế:
1. NodeBST:
   - data (User), left (NodeBST), right (NodeBST)
   - Constructor: NodeBST(User data)

2. BSTSearch:
   - root (NodeBST)
   - insert(), search(), delete(), inOrder()
   - findMin(), findMax(), height(), isBalanced()


🧑‍💻 TRUNG — INTEGRATION & FACADE (MỚI)
Classes cần thiết kế:
1. SocialNetworkFacade:
   - dll (DLLStorage), bst (BSTSearch), suggester (FriendSuggestion)
   - addUser(), addFriendship(), suggest()
   - printAllUsers(), printFriends(), loadFromFile()

2. Main:
   - main(), showMenu(), handleChoice()
   - Các case: Thêm user, kết bạn, gợi ý, in danh sách, thoát

