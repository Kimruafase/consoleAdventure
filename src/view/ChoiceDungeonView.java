package view;

import controller.DungeonController;

import java.util.Scanner;

public class ChoiceDungeonView {

    public static ChoiceDungeonView choiceDungeonView = new ChoiceDungeonView();

    public Scanner scan = new Scanner(System.in);

    //0 접속 성공 초기화면
    public void index3(){ //is
        while(true){ //ws
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("           1. 쉬움 2. 보통 3. 어려움 4. 난이도 설명 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            int ch = scan.nextInt();

            if(ch == 1){easy(ch);}
            else if(ch == 2){normal(ch);}
            else if(ch == 3){hard(ch);}
            else if(ch == 4){DungeonView.getInstance().dungeonDifficultyExplain();}
            else if(ch == 5){
                return;
            }
            else {
                System.out.println("\n----------------------------------------------------------------------------------\n");
                System.out.println("           없는 기능 입니다.");
                System.out.println("\n----------------------------------------------------------------------------------\n");
            }
        } //we
    } //ie

    //1. 쉬움난이도이동
    public void easy(int ch){
        DungeonController.getInstance().dungeonDifficulty(ch);
    }
    //2. 보통난이도이동
    public void normal(int ch ){
        DungeonController.getInstance().dungeonDifficulty(ch);
    }
    //3. 어려움난이도이동
    public void hard(int ch ){
        DungeonController.getInstance().dungeonDifficulty(ch);
    }
}
