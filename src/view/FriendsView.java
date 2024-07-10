package view;

import controller.CharacterController;
import controller.FriendsController;
import controller.MenuController;
import model.dto.FreindsDto;

import java.util.ArrayList;
import java.util.Scanner;

public class FriendsView {
    private static FriendsView friendsView = new FriendsView();
    private FriendsView(){}
    public static FriendsView getInstance(){
        return friendsView;
    }
    Scanner scan = new Scanner(System.in);

    // 5. 친구 목록 출력
    public void friendsPrint(){
        ArrayList<String > list = FriendsController.getInstance().friendsPrint();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ================== 내 친구 목록 ================== //");
        System.out.println("캐릭터 닉네임");
        if(list.isEmpty()){
            System.out.println(">> 등록된 친구가 없습니다.");
        }
        list.forEach(s -> {
            System.out.printf("%s \n" , s );
        });
        System.out.println("\n----------------------------------------------------------------------------------\n");
        friendsPage();
    }   // friendsPrint() end

    // 6. 친구 페이지
    public void friendsPage(){
        System.out.print(">> 0. 뒤로가기 1. 친구추가 2. 받은 친구 요청 3. 친구삭제 ");
        int ch = scan.nextInt();
        if(ch==0){
            MenuView.mView.index2();
        } else if (ch==1) {
            addFriends();
        } else if (ch==2) {
            receivedFriends();
        } else if (ch==3) {
            deleteFriends();
        }
    }   // friendsPage() end

    // 6-1 친구 추가
    public void addFriends(){
        System.out.print(">> 추가할 친구의 닉네임을 입력하세요 : ");
        String newFreinds = scan.next();
        boolean result = FriendsController.getInstance().addFriends(newFreinds);
        if(result){
            System.out.println(">> 친구 요청을 보냈습니다.");
            friendsPrint();
        }else {
            System.out.println(">> 해당 캐릭터가 존재하지 않습니다.");
            friendsPrint();
        }
    }   // addFriends() end

    // 6-2 받은 친구 요청
    public void receivedFriends(){
        ArrayList<FreindsDto> list = FriendsController.getInstance().receivedFriends();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ================== 내 친구 목록 ================== //");
        System.out.println("요청한캐릭터번호 캐릭터 닉네임");
        if(list.isEmpty()){
            System.out.println(">> 친구 요청 받은 내용이 없습니다.");
            friendsPage();
        }
        list.forEach(dto -> {
            System.out.printf("%d %s\n" , dto.getFromckey() , dto.getTocnickname());
        });
        System.out.println("\n----------------------------------------------------------------------------------\n");
        acceptRequest();
    }   // receivedFriends() end

    // 6-3 친구요청 수락
    public void acceptRequest(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 수락할 요청의 캐릭터 번호를 입력하세요 :" );
        int fromckey = scan.nextInt();
        boolean result = FriendsController.getInstance().acceptRequest(fromckey);
        if(result){
            System.out.println(">> 친구 요청을 수락했습니다.");
        }else {
            System.out.println(">> 친구 요청 수락에 실패했습니다. ");
        }
        friendsPage();
    }   // acceptRequest() end

    // 6-4 친구삭제
    public void deleteFriends(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 삭제할 친구의 닉네임을 입력하세요 :" );
        String cnickname = scan.next();
        boolean result = FriendsController.getInstance().deleteFriends(cnickname);
        if(result){
            System.out.println(">> 친구 삭제 성공");
        }else{
            System.out.println(">> 친구 삭제 실패");
        }
    }   // deleteFriends() end

    // 6-5 있는 친구 인지 유효성 검사
   /* public boolean checkFriends(){

    }*/

}
