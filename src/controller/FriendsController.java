package controller;

import model.dao.FriendsDao;
import model.dao.MenuDAO;
import model.dto.FreindsDto;

import java.util.ArrayList;
import java.util.Scanner;

public class FriendsController {
    private static FriendsController friendsController = new FriendsController();
    private FriendsController(){}
    public static FriendsController getInstance(){
        return friendsController;
    }
    Scanner scan = new Scanner(System.in);

    // 5. 친구 목록 출력
    public ArrayList<String > friendsPrint(){
        return FriendsDao.getInstance().friendsPrint(CharacterController.cController.loginCno);
    }   // friendsPrint() end

    // 6-1 친구 추가
    public boolean addFriends(String newFreinds){
        return FriendsDao.getInstance().addFriends(newFreinds , CharacterController.cController.loginCno);
    }   // addFriends() end

    // 6-2 받은 친구 요청
    public  ArrayList<FreindsDto> receivedFriends(){
        return FriendsDao.getInstance().receivedFriends(CharacterController.cController.loginCno);
    }   // receivedFriends() end

    // 6-3 친구요청 수락
    public boolean acceptRequest(int fromckey){
        return FriendsDao.getInstance().acceptRequest(fromckey);
    }   // acceptRequest() end

    // 6-4 친구삭제
    public boolean deleteFriends(String cnickname){
        return FriendsDao.getInstance().deleteFriends(cnickname , CharacterController.cController.loginCno);
    }   // deleteFriends() end

    // 6-5 있는 친구 인지 유효성 검사
    /*public void checkFriends(){

    }   // checkFriends() end*/
}
