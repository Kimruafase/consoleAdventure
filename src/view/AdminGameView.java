package view;

import controller.AdminGameController;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;

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
                adminDungeon();
            } else if (ch == 3) {
                adminMonster();
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
    // 1-1 캐릭터 전체 출력
    public void characterAllPrint(){
       ArrayList<CharacterDto> list = AdminGameController.getInstance().characterAllPrint();
        System.out.println("// ==================== 전체 캐릭터 ==================== //");
        System.out.println("캐릭터번호  닉네임  회원아이디 ");
        list.forEach(dto -> {
            System.out.printf("%3d %8s %8s \n" , dto.getCkey() , dto.getCnickname() ,
                    dto.getAid());
        });
        adminCharacter2();
    }   // characterAllPrint() end

    // 1-2 캐릭터 세부 관리 페이지
    public void adminCharacter2(){
        System.out.print(">> 0. 뒤로가기 1. 캐릭터삭제 : "); int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            characterDelete();
        }

    }   // adminCharacter2() end

    // 1-3 캐릭터 삭제
    public void characterDelete(){
        System.out.println("// ==================== 캐릭터 삭제 ==================== //");
        System.out.print(">> 삭제할 캐릭터 번호를 입력하세요 : ");
        int ckey = scan.nextInt();
        boolean result = AdminGameController.getInstance().characterDelete(ckey);
        if(result){
            System.out.println(">> 캐릭터 삭제 성공");
            characterAllPrint();
        }else{
            System.out.println(">> 캐릭터 삭제 실패");
        }
    }   // characterDelete() end

    // 2. 던전관리 페이지
    public void adminDungeon(){
        System.out.println("// ================== 캐릭터관리 페이지 ================== //");
        System.out.print(">> 0. 뒤로가기 1. 던전맵 전체 출력 : ");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            dungeonAllPrint();
        }
    }   // adminDungeon() end

    // 2-1 던전맵 전체 출력
    public void dungeonAllPrint(){

    }   // dungeonAllPrint() end

    // 3. 몬스터 관리 페이지
    public void adminMonster(){
        System.out.println("// ================== 몬스터관리 페이지 ================== //");
        System.out.print(">> 0. 뒤로가기 1. 몬스터 전체 출력 : ");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            monsterAllPrint();
        }
    }   // adminDungeon() end

    // 2-1 던전맵 전체 출력
   /* public void dungeonAllPrint(){

    }   // dungeonAllPrint() end*/

    // 3-1 몬스터 전체 출력
    public void monsterAllPrint(){
        ArrayList<DungeonDto_Monster> list = AdminGameController.getInstance().monsterAllPrint();
        System.out.println("// ==================== 전체 몬스터 ==================== //");
        System.out.println("몬스터번호  몬스터이름 ");
        list.forEach(dto -> {
            System.out.printf("%3d %8s \n" , dto.getMkey() , dto.getMname());
        });
        adminMonster2();
    }   // monsterAllPrint() end

    // 3-2 몬스터 세부 관리페이지
    public void adminMonster2(){
        System.out.print(">> 0. 뒤로가기 1. 몬스터추가 2. 몬스터수정 3. 몬스터 삭제 : "); int ch = scan.nextInt();
            if(ch==0){
                adminMonster();
            } else if (ch==1) {

            }
    }   // adminMonster2() end

    // 3-3 몬스터 추가 페이지
    public void addMonster(){
        System.out.print(">> 추가할 몬스터 이름을 입력해주세요 : ");
        String mname = scan.next();
        boolean result = AdminGameController.getInstance().addMonster(mname);
        if(result){
            System.out.println(">> 몬스터 추가 성공");
            monsterAllPrint();
        }else {
            System.out.println(">> 몬스터 추가 실패");
        }
    }   // addMonster() end

}   // AdminGameView class end
