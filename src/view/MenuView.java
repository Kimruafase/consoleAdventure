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
            System.out.println("\t닉네임  HP  소지금  레벨");
            DungeonView.getInstance().characterLevelAndExp();
            result.forEach(charinfo ->{
                System.out.printf("\t%s %d   %d %d\n" ,charinfo.getCnickname(), charinfo.getChp() ,charinfo.getCmoney(),charinfo.getcLevel());
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
        ArrayList<FreindsDto> list = MenuController.MController.friendsPrint();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ================== 내 친구 목록 ================== //");
        System.out.println("캐릭터 닉네임");
        if(list.isEmpty()){
            System.out.println(">> 등록된 친구가 없습니다.");
        }
        list.forEach(dto -> {
            System.out.printf("%s\n" , dto.getTocnickname());
        });
        System.out.println("\n----------------------------------------------------------------------------------\n");
        friendsPage();
    }   // friendsPrint() end

    // 6. 친구 페이지
    public void friendsPage(){
        System.out.print(">> 0. 뒤로가기 1. 친구추가 2. 받은 친구 요청 ");
        int ch = scan.nextInt();
        if(ch==0){
            index2();
        } else if (ch==1) {
            addFriends();
        } else if (ch==2) {
            receivedFriends();
        }
    }   // friendsPage() end

    // 6-1 친구 추가
    public void addFriends(){
        System.out.print(">> 추가할 친구의 닉네임을 입력하세요 : ");
        String newFreinds = scan.next();
        boolean result = MenuController.MController.addFriends(newFreinds);
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
        ArrayList<FreindsDto> list = MenuController.MController.receivedFriends();
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
        boolean result = MenuController.MController.acceptRequest(fromckey);
        if(result){
            System.out.println(">> 친구 요청을 수락했습니다.");
        }else {
            System.out.println(">> 친구 요청 수락에 실패했습니다. ");
        }
        friendsPage();
    }   // acceptRequest() end

    //5 getskill
    public void getskill(){
        boolean result = MenuController.MController.getskill();
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


}
