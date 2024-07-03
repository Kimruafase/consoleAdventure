package controller;

import model.dao.MenuDAO;
import model.dto.MySkillDto;

import java.util.ArrayList;

public class MenuController {

public static MenuController MController = new MenuController();

    //1. 캐릭터 정보 함수
    public ArrayList<model.dto.CharacterDto> charinfo(){
        return MenuDAO.MDAO.charinfo(CharacterController.cController.loginCno);


    }
    //2. 던전 메뉴 이동 함수
    public void godungeon(){

    }
    //3. 스킬정보 함수
    public ArrayList<MySkillDto> skillinfo(){
        return MenuDAO.MDAO.skillinfo(CharacterController.cController.loginCno);
    }

}
