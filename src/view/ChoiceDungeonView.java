package view;

import java.util.Scanner;

public class ChoiceDungeonView {

    public static ChoiceDungeonView choiceDungeonView = new ChoiceDungeonView();

    public Scanner scan = new Scanner(System.in);

    //0 접속 성공 초기화면
    public void index3(){ //is
        while(true){ //ws
            System.out.println("1.쉬움 2.보통 3.어려움 : ");
            int ch = scan.nextInt();

            if(ch == 1){easy(ch);}
            else if(ch == 2){normal(ch);}
            else if(ch == 3){hard(ch);}
            else if(ch == 4){return;}
            else {System.out.println("없는 기능 입니다.");}
        } //we
    } //ie

    //1. 쉬움난이도이동
    public void easy(int multi){
        DungeonView.getInstance().dungeonIndex(multi);
    }
    //2. 보통난이도이동
    public void normal(int multi){
        DungeonView.getInstance().dungeonIndex(multi);
    }
    //3. 어려움난이도이동
    public void hard(int multi){
        DungeonView.getInstance().dungeonIndex(multi);
    }
}
