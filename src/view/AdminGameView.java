package view;

import controller.AdminGameController;
import model.dto.CharacterDto;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminGameView {
    private static AdminGameView adminGameView = new AdminGameView();
    private AdminGameView(){}
    public static AdminGameView getInstance(){
        return adminGameView;
    }
    Scanner scan = new Scanner(System.in);

    // 게임관리 첫번째 페이지
    public void index(){
        while (true){
            System.out.println("// ================== 게임관리 페이지 ================== //");
            System.out.print(">> 0. 뒤로가기 1. 캐릭터관리 2. 던전관리 3. 몬스터관리 4. 스킬관리 : ");
            int ch = scan.nextInt();
            if(ch==0){
                AdminView.getInstance().adminIndex();
            } else if (ch==1) {
                adminCharacter();
            } else if (ch==2) {

            } else if (ch == 3) {

            } else if (ch==4) {

            }else{
                System.out.println(">> 없는 기능번호입니다. ");

            }
        }   // while end

    }   // index() end

    // 1. 캐릭터관리 페이지
    public void adminCharacter(){
        System.out.println("// ================== 캐릭터관리 페이지 ================== //");
        System.out.print(">> 0. 뒤로가기 1. 캐릭터 전체 출력 : ");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            characterAllPrint();
        }
    }
    // 1-1 캐릭터 전채 출력
    public void characterAllPrint(){
       ArrayList<CharacterDto> list = AdminGameController.getInstance().characterAllPrint();
        System.out.println("// ============================== 전체 캐릭터 ============================== //");
        System.out.println("캐릭터번호  닉네임  회원아이디 ");
        list.forEach(dto -> {
            System.out.printf("%3d %8s %10s %7s %13s %7s %s\n" , dto.getCkey() , dto.getCnickname() ,
                    dto.getAid());
        });

    }   // characterAllPrint() end

}   // AdminGameView class end
