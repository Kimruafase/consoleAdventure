package view;


import controller.MenuController;
import model.dto.MySkillDto;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuView {

    public static MenuView mView = new MenuView();

    public Scanner scan = new Scanner(System.in);

    //0 접속 성공 초기화면
    public void index2(){ //is
        while(true){ //ws
            System.out.println("1. 캐릭터정보 2.던전 3.스킬정보 4.종료");
            int ch = scan.nextInt();

            if(ch == 1){charinfo();}
            else if(ch == 2){godungeon();}
            else if(ch == 3){skillinfo();}
            else if(ch == 4){break;}
            else {System.out.println("없는 기능 입니다.");}
        } //we
    } //ie

    //1. 캐릭터 정보 함수
    public void charinfo(){
        ArrayList<model.dto.CharacterDto> result = MenuController.MController.charinfo();

        if (result.isEmpty()){
            System.out.println("없는 정보입니다.");
        }else {
            System.out.println("닉네임  HP");
            result.forEach(charinfo ->{
                System.out.printf("%s %d \n" ,charinfo.getCnickname(), charinfo.getChp() );
            });
        }
    }
    //2. 던전 메뉴 이동 함수
    public void godungeon(){
        System.out.println("던전으로 이동합니다");
        System.out.println("==========================");
        ChoiceDungeonView.choiceDungeonView.index3();
    }
    //3. 스킬정보 함수
    public void skillinfo(){
        ArrayList<MySkillDto> result = MenuController.MController.skillinfo();

        if (result.isEmpty()){
            System.out.println("스킬이 없습니다.");
        }else {
            System.out.println("스킬 이름 \t 스킬 설명 \t\t 스킬 데미지");
            System.out.println("================================================");
            result.forEach(skillinfo -> {
                System.out.printf("%-10s %10s%10d \n", skillinfo.getSkname(), skillinfo.getSkinfo(), skillinfo.getSkdamage());
                System.out.println("==================================================");
            });
        }
    }
}
