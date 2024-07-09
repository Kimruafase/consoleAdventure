package view;


import controller.CharacterController;
import controller.MenuController;
import model.dao.CharacterDAO;
import model.dto.FreindsDto;
import model.dto.MySkillDto;
import model.dto.SkillDto;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuView {

    public static MenuView mView = new MenuView();

    public Scanner scan = new Scanner(System.in);

    //0 접속 성공 초기화면
    public void index2(){ //is
        while(true){ //ws
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t1. 캐릭터정보 2.던전 3.스킬정보 4.상점 5. 내 친구 목록 6.종료");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            int ch = scan.nextInt();

            if(ch == 1){charinfo();}
            else if(ch == 2){godungeon();}
            else if(ch == 3){skillinfo();}
            else if(ch == 4){shop();}
            else if (ch==5) {
                friendsPrint();
            } else if(ch == 6){CharacterView.chview.index();}
            else {
                System.out.println("\n----------------------------------------------------------------------------------\n");
                System.out.println("\t없는 기능 입니다.");
                System.out.println("\n----------------------------------------------------------------------------------\n");
            }
        } //we
    } //ie

    //1. 캐릭터 정보 함수
    public void charinfo(){
        ArrayList<model.dto.CharacterDto> result = MenuController.MController.charinfo();

        if (result.isEmpty()){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t없는 정보입니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t닉네임  HP");
            result.forEach(charinfo ->{
                System.out.printf("\t%s %d \n" ,charinfo.getCnickname(), charinfo.getChp() );
            });
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }
    //2. 던전 메뉴 이동 함수
    public void godungeon(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t던전으로 이동합니다");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        ChoiceDungeonView.choiceDungeonView.index3();
    }
    //3. 스킬정보 함수
    public void skillinfo(){
        ArrayList<MySkillDto> result = MenuController.MController.skillinfo();

        if (result.isEmpty()){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t스킬이 없습니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {
            System.out.println("스킬 이름 \t 스킬 설명 \t\t 스킬 데미지");
            System.out.println("================================================");
            result.forEach(skillinfo -> {
                System.out.printf("%-10s %10s%10d \n", skillinfo.getSkname(), skillinfo.getSkinfo(), skillinfo.getSkdamage());
                System.out.println("==================================================");
            });
        }
    }

    //4. 상점 기능 함수
    public void shop(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t1.스킬목록 2.스킬구입 ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if (ch == 1){showshopskill();}
        else if (ch == 2){buyskill();}
        else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t없는 기능입니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }

    //4-1 스킬 목록 기능
    public void showshopskill(){
        ArrayList<SkillDto> result = MenuController.MController.showshopskill();

        if (result.isEmpty()){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t스킬이 없습니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t스킬 이름 \t 스킬 설명 \t\t 스킬 데미지");
            System.out.println("================================================");
            result.forEach(showskill -> {
                System.out.printf("\t%d %-10s %10s%10d \n",showskill.getSkkey(), showskill.getSkname(), showskill.getSkinfo(), showskill.getSkdamage());
                System.out.println("==================================================");
            });
            System.out.println("\n----------------------------------------------------------------------------------\n");
            shop();//
        }
    }

    //4-2 스킬 구입 기능
    public void buyskill(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t구매할 스킬 번호를 입력하세요 ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();    //

        boolean result = MenuController.MController.buyskill(ch);

        if (result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t스킬 구매 성공");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
        else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t스킬 구매 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }

    }
    // 5. 친구 목록 출력
    public void friendsPrint(){
        ArrayList<FreindsDto> list = CharacterController.cController.friendsPrint();
        if(list==null){
            System.out.println(">> 등록된 친구가 없습니다.");
        }
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ================== 내 친구 목록 ================== //");
        System.out.println("캐릭터 닉네임");
        list.forEach(dto -> {
            System.out.printf("%s\n" , dto.getTocnickname());
        });
        System.out.println("\n----------------------------------------------------------------------------------\n");
        friendsPage();
    }   // friendsPrint() end

    // 6. 친구 페이지
    public void friendsPage(){
        System.out.print(">> 0. 뒤로가가 1. 친구추가 ");
        int ch = scan.nextInt();
        if(ch==0){
            index2();
        } else if (ch==1) {
            addFriends();
        }
    }   // friendsPage() end

    // 6-1 친구 추가
    public void addFriends(){
        System.out.print(">> 추가할 친구의 닉네임을 입력하세요 : ");
        String newFreinds = scan.next();
        boolean result = CharacterController.cController.addFriends(newFreinds);
        if(result){
            System.out.println(">> 친구 추가 성공");
        }else {
            System.out.println(">> 해당 캐릭터가 존재하지 않습니다.");
        }
    }   // addFriends() end
}
