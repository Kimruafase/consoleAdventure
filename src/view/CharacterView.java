package view;

import controller.CharacterController;
import model.dto.CharacterDto;

import java.util.ArrayList;
import java.util.Scanner;

public class CharacterView { //cs

    //공용 사용 시작 객체 생성
    public static CharacterView chview = new CharacterView();

    Scanner scan = new Scanner(System.in);

    //초기화면 함수 생성
    public void index(){ //is
        while(true){ //ws
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t1. 캐릭터 생성 2. 캐릭터 접속 3. 캐릭터 삭제 4.나가기 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            try{
                int ch = scan.nextInt();
                if (ch == 1){createChar();}
                else if(ch == 2){joinGame();}
                else if(ch == 3){delChar();}
                else if(ch == 4){MyAccountView.getInstance().index2();}
                else {
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                    System.out.println("\t사용할 수 없는 기능 입니다.");
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                }
            }catch (Exception e){System.out.println(e);}
        } //we
    } //ie

    //1. 캐릭터생성함수
    public void createChar(){ //CS
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>>>> 캐릭터 생성 페이지 <<<<");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print("닉네임 입력 : "); String nickname = scan.next();

        model.dto.CharacterDto characterDTO = new model.dto.CharacterDto(nickname);

        boolean result = CharacterController.cController.createChar(characterDTO);

        if (result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t캐릭터 생성완료");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
        else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t캐릭터 생성 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }

    } //CE

    //2. 캐릭터 접속함수 //캐릭터닉네임 입력하여 계정과 캐릭터이름 동일시 로그인
    public void joinGame(){
        System.out.print("캐릭터 닉네임 입력 : "); String nickname = scan.next();

        model.dto.CharacterDto characterDTO = new model.dto.CharacterDto(nickname);

        boolean reslut = CharacterController.cController.joinGame(characterDTO);
        if(reslut){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>>>>> 접속성공 <<<<<");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            MenuView.mView.index2();
        }
        else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t접속 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }

    //3. 캐릭터 삭제함수
    public void delChar(){
        System.out.print("삭제할 캐릭터 입력 : "); String delch = scan.next();

        model.dto.CharacterDto characterDTO = new model.dto.CharacterDto(delch);

        boolean result = CharacterController.cController.delChar(characterDTO);
        if (result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t삭제 성공");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
        else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t삭제 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }

} //ce
