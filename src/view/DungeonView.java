package view;

import controller.DungeonController;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.SkillDto;

import java.util.ArrayList;
import java.util.Scanner;

public class DungeonView {
    Scanner scan = new Scanner(System.in);

    private static DungeonView dungeonView = new DungeonView();
    private DungeonView(){}

    public static DungeonView getInstance(){
        return dungeonView;
    }

    public void dungeonIndex( ){
                try {
                    int ch = DungeonController.getInstance().dice();
                    if (ch == 1) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        meetMonster();
                    } else if (ch == 2) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("회복의 크리스탈을 발견하였습니다! 체력을 회복합니다.\n");
                        DungeonController.getInstance().hpRecovery();
                    } else if (ch == 3) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("강제 귀환! 던전의 처음으로 돌아갑니다!\n");
                        DungeonController.getInstance().backToTheBeginning();
                    } else if (ch == 4) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("아무 일도 일어나지 않았습니다. 그대로 진행합니다.\n");
                        DungeonController.getInstance().goContinue();
                    } else if (ch == 5) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("길이 막혀있습니다! 뒤로 돌아갑니다.\n");
                        DungeonController.getInstance().goBackOneSpace();
                    } else if (ch == 6) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("함정이 발동되었습니다! 체력이 감소합니다.\n");
                        DungeonController.getInstance().meetTrap();
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
    }


    //  1. 몬스터 조우 함수
    public void meetMonster( ){
        fight();
    }



    //  7. 전투 함수
    public void fight(){
        System.out.println("몬스터를 만났습니다! 전투하시겠습니까?");
        System.out.println("1번 : 전투하기 / 그 외 : 후퇴하기");
        System.out.println("\n-----------------------------------------");
        int ch = scan.nextInt();
        if(ch == 1){
            myCharacterFight();
        }else{
            return;
        }
    }

    public void myCharacterFight(){


        while (true){
            System.out.println("스킬을 사용하려면 스킬 이름을 입력하세요. \n기본 공격을 하고 싶다면 기본공격을 입력하세요.");
            MenuView.mView.skillinfo();
            String select = scan.next();

            DungeonController.getInstance().myCharacterFight(select);
            DungeonController.getInstance().fightResult();
        }
    }

    //  8. 몬스터 정보 표시 함수
    public void monsterPrint(){
        int random = (int)(Math.random()*3+1);
//        ArrayList<DungeonDto_Monster> list = DungeonController.getInstance().monsterPrint(random);
    }

    // 진행 or 후퇴 함수
    public void goOrBack( ){
        System.out.println("\n-----------------------------------------\n");
        System.out.println(" == 계속 진행하시겠습니까? ==");
        System.out.println(" == 1번 : 진행하기 / 그 외 : 후퇴하기 ==");
        int ch = scan.nextInt();
        if(ch == 1){
            dungeonIndex();
        }else{
            return;
        }
    }




}
