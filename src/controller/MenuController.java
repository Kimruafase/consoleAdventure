package controller;

import model.dao.CharacterDAO;
import model.dao.MenuDAO;
import model.dto.CharacterDto;
import model.dto.FreindsDto;
import model.dto.MySkillDto;
import model.dto.SkillDto;

import java.util.ArrayList;

public class MenuController {

public static MenuController MController = new MenuController();
SkillDto skillDto = new SkillDto();
CharacterDto characterDto = new CharacterDto();

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

    //4-1 스킬 목록 기능
    public ArrayList<SkillDto> showshopskill(){
        return  MenuDAO.MDAO.showshopskill();
    }

    //4-2 스킬 구입 기능
    public boolean buyskill(int ch){
        int ckey = CharacterController.cController.loginCno;
        return MenuDAO.MDAO.buyskill(ch,ckey);
    }

    //5 스킬 구입 가격차감기능
    public boolean getskill(){
       int result = characterDto.getCmoney() - skillDto.getSkmoney();
       return MenuDAO.MDAO.getskill(result,CharacterController.cController.loginCno);

    }

}
