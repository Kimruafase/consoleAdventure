package controller;

import model.dao.CharacterDAO;
import model.dto.CharacterDto;
import model.dto.FreindsDto;

import java.util.ArrayList;

public class CharacterController {
    public static CharacterController cController = new CharacterController();

    //1. 캐릭터생성함수
    public boolean createChar(model.dto.CharacterDto characterDTO){
        characterDTO.setAkey(MyAccountController.getInstance().loginAkey);
        // 캐릭터 생성
        int result = CharacterDAO.characterDAO.createChar(characterDTO);
        // 기본공격 대입
        if( result > 0 ){
            characterDTO.setCkey( result );
            CharacterDAO.characterDAO.charattack(characterDTO);
            return true;
        }
        return false;
    }
    public int loginCno = 0;
    public void logout(){loginCno = 0;}

    //2. 캐릭터 접속함수
    public boolean joinGame(model.dto.CharacterDto characterDTO){
        int result = CharacterDAO.characterDAO.joinGame(characterDTO);

        if (result != 0){loginCno = result;return true;}
        else {loginCno = 0;} return false;
    }

    //3. 캐릭터삭제함수
    public boolean delChar(model.dto.CharacterDto characterDTO){
        //로그인 정보번호 받아와서 저장
        characterDTO.setAkey(MyAccountController.getInstance().loginAkey);
        boolean result = CharacterDAO.characterDAO.delChar(characterDTO);
        return result;
    }

}
