package controller;

import model.dao.CharacterDAO;
import model.dto.CharacterDto;
import model.dto.FreindsDto;

import java.util.ArrayList;

public class CharacterController {
    public static CharacterController cController = new CharacterController(); //컨트롤러 사용을 위한 전역변수 선언

    //1. 캐릭터생성함수
    public boolean createChar(model.dto.CharacterDto characterDTO){
        characterDTO.setAkey(MyAccountController.getInstance().loginAkey); //캐릭터DTO에 포함된 Akey에 현재 로그인된 식별번호 입력
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
    public int loginCno = 0;            //로그인 상태 함수
    public void logout(){loginCno = 0;} //0이면 로그아웃 상태

    //2. 캐릭터 접속함수
    public boolean joinGame(model.dto.CharacterDto characterDTO){
        int result = CharacterDAO.characterDAO.joinGame(characterDTO,MyAccountController.getInstance().loginAkey);

        if (result != 0){loginCno = result;return true;} //유효성 검사 로그인한 캐릭터 넘버가 0이 아니면 로그인 상태
        else {loginCno = 0;} return false; //그게 아니면 로그아웃 상태
    }

    //3. 캐릭터삭제함수
    public boolean delChar(model.dto.CharacterDto characterDTO){
        //로그인 정보번호 받아와서 저장
        characterDTO.setAkey(MyAccountController.getInstance().loginAkey);
        boolean result = CharacterDAO.characterDAO.delChar(characterDTO);
        return result;
    }

}
