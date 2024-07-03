package 프로젝트2.Controller;

import 프로젝트2.Model.Dao.CharacterDAO;
import 프로젝트2.Model.Dto.CharacterDTO;

import java.util.ArrayList;

public class CharacterController {
    public static CharacterController cController = new CharacterController();

    //1. 캐릭터생성함수
    public boolean createChar(CharacterDTO characterDTO){
        boolean result = CharacterDAO.characterDAO.createChar(characterDTO);
        return result;
    }
    public int loginCno = 0;
    public void logout(){loginCno = 0;}

    //2. 캐릭터 접속함수
    public boolean joinGame(CharacterDTO characterDTO){
        int result = CharacterDAO.characterDAO.joinGame(characterDTO);

        if (result != 0){loginCno = result;return true;}
        else {loginCno = 0;} return false;
    }

    //3. 캐릭터삭제함수
    public boolean delChar(int bno){
        //로그인 정보번호 받아와서 저장
        return CharacterDAO.characterDAO.delChar(bno);
    }

    //4. 캐릭터 목록 함수
    public void showChar(){
        return CharacterDAO.characterDAO.showChar();
    }
    //매개변수 X 리턴값 계정키번호와 맞는 캐릭터 닉네임 출력
}
