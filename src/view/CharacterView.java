package view;

import controller.CharacterController;

import java.util.ArrayList;
import java.util.Scanner;

public class CharacterView { //cs

    //공용 사용 시작 객체 생성
    public static CharacterView chview = new CharacterView();

    Scanner scan = new Scanner(System.in);

    //초기화면 함수 생성
    public void index(){ //is
        while(true){ //ws
            System.out.println("1. 캐릭터 생성 2. 캐릭터 접속 3. 캐릭터 삭제 4.캐릭터 목록 5. 나가기 ");
            try{
                int ch = scan.nextInt();
                if (ch == 1){createChar();}
                else if(ch == 2){joinGame();}
                else if(ch == 3){delChar(ch);}
                else if(ch == 4){showChar();}
                else if(ch == 5){break;}
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
    public void delChar(int bno){
        boolean result = CharacterController.cController.delChar(bno);
        if (result){System.out.println("삭제 성공");}
        else {System.out.println("삭제 실패");}
    }

    //4. 캐릭터 목록 함수
    public void showChar(){
//        ArrayList< model.dto.CharacterDto> result = CharacterController.cController.showChar();

//        if (result.isEmpty()){
//            System.out.println("보유하신 캐릭터가 없습니다.");
//        }else {
//            System.out.println("캐릭터목록");
//            result.forEach(showcha -> {
//                System.out.printf("");
//            });
//        }
        //매개변수 X 리턴값 계정키번호와 맞는 캐릭터 닉네임 출력
    }
} //ce
