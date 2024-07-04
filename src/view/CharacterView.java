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
            System.out.println("1. 캐릭터 생성 2. 캐릭터 접속 3. 캐릭터 삭제 4.나가기 ");
            try{
                int ch = scan.nextInt();
                if (ch == 1){createChar();}
                else if(ch == 2){joinGame();}
                else if(ch == 3){delChar();}
                else if(ch == 4){break;}
                else {System.out.println("사용할 수 없는 기능 입니다.");}
            }catch (Exception e){System.out.println(e);}
        } //we
    } //ie

    //1. 캐릭터생성함수
    public void createChar(){ //CS
        System.out.println(">>>> 캐릭터 생성 페이지 <<<<");
        System.out.println("닉네임 입력 : "); String nickname = scan.next();

        model.dto.CharacterDto characterDTO = new model.dto.CharacterDto(nickname);

        boolean result = CharacterController.cController.createChar(characterDTO);

        if (result){System.out.println("캐릭터 생성완료");}
        else {System.out.println("캐릭터 생성 실패");}

    } //CE

    //2. 캐릭터 접속함수 //캐릭터닉네임 입력하여 계정과 캐릭터이름 동일시 로그인
    public void joinGame(){
        System.out.println("캐릭터 닉네임 입력 : "); String nickname = scan.next();

        model.dto.CharacterDto characterDTO = new model.dto.CharacterDto(nickname);

        boolean reslut = CharacterController.cController.joinGame(characterDTO);
        System.out.println(reslut);
        if(reslut){
            System.out.println(">>>>> 접속중 <<<<<");
            System.out.println(" ");
            System.out.println(">>>>> 접속중 <<<<<");
            System.out.println(" ");
            System.out.println(">>>>> 접속중 <<<<<");
            System.out.println(" ");
            System.out.println(">>>>> 접속중 <<<<<");
            System.out.println(" ");
            System.out.println(">>>>> 접속성공 <<<<<");
            System.out.println("===================================================");
            MenuView.mView.index2();
        }
        else {System.out.println("접속 실패");}
    }

    //3. 캐릭터 삭제함수
    public void delChar(){
        System.out.println("삭제할 캐릭터 입력 : "); String delch = scan.next();

        model.dto.CharacterDto characterDTO = new model.dto.CharacterDto(delch);

        boolean result = CharacterController.cController.delChar(characterDTO);
        if (result){System.out.println("삭제 성공");}
        else {System.out.println("삭제 실패");}
    }

} //ce
