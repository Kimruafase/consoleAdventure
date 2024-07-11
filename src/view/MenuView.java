package view;


import controller.CharacterController;
import controller.MenuController;
import model.dao.CharacterDAO;
import model.dto.CharacterDto;
import model.dto.FreindsDto;
import model.dto.MySkillDto;
import model.dto.SkillDto;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuView {

    public static MenuView mView = new MenuView();

    public Scanner scan = new Scanner(System.in);

    //0 캐릭터 접속 성공시 초기화면
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
                FriendsView.getInstance().friendsPrint();
            } else if(ch == 6){CharacterView.chview.index();}
            else {
                System.out.println("\n----------------------------------------------------------------------------------\n");
                System.out.println("\t없는 기능 입니다.");
                System.out.println("\n----------------------------------------------------------------------------------\n");
            }
        } //we
    } //ie

    //1. 캐릭터 정보 함수
    public void charinfo(){//1s
        ArrayList<model.dto.CharacterDto> result = MenuController.MController.charinfo();

        if (result.isEmpty()){//result가 공백 값이면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t없는 정보입니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else { //result 가 존재하는 값이면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t닉네임  HP  소지금");
            result.forEach(charinfo ->{
                System.out.printf("\t%s %d   %d \n" ,charinfo.getCnickname(), charinfo.getChp() ,charinfo.getCmoney());
            });
            DungeonView.getInstance().characterLevelAndExp();
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }//1e
    //2. 던전 메뉴 이동 함수
    public void godungeon(){ //2s
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t던전으로 이동합니다");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        ChoiceDungeonView.choiceDungeonView.index3();
    } //2e
    //3. 스킬정보 함수
    public void skillinfo(){//3s
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
    }//3e

    //4. 상점 기능 함수
    public void shop(){//4s
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
    }//4e

    //4-1 스킬 목록 기능
    public void showshopskill(){//4-1s
        ArrayList<SkillDto> result = MenuController.MController.showshopskill();

        if (result.isEmpty()){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t스킬이 없습니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t스킬 이름 \t 스킬 설명 \t\t 스킬 데미지 \t\t 스킬 가격");
            System.out.println("==========================================================");
            result.forEach(showskill -> {
                System.out.printf("\t%d %-10s %10s%10d %10d\n",showskill.getSkkey(), showskill.getSkname(), showskill.getSkinfo(), showskill.getSkdamage(),showskill.getSkmoney());
                System.out.println("============================================================");
            });
            System.out.println("\n----------------------------------------------------------------------------------\n");
            shop();//
        }
    }//4-1e

    //4-2 스킬 구입 기능
    public void buyskill(){//4-2s
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



}
