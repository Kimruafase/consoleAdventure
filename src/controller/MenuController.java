package controller;

import model.dao.CharacterDAO;
import model.dao.MenuDAO;
import model.dto.CharacterDto;
import model.dto.FreindsDto;
import model.dto.MySkillDto;
import model.dto.SkillDto;

import java.awt.*;
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

        // 유효성 검사
            // 1. 현재 돈
        int myMoney = MenuDAO.MDAO.minMoney(ckey);

            // 2. 구매할 스킬 금액
        int skillMoney = MenuDAO.MDAO.minskillmoney(ch);

            // 3. 비교
        if( myMoney >= skillMoney ){

            // 스킬구매
            boolean result =  MenuDAO.MDAO.buyskill(ch,ckey);
            if( result ){
                // 구매 성공시 소지금 차감
                int mone = myMoney - skillMoney;
                MenuDAO.MDAO.upMoney(mone,ckey);
            }
            return result;
        }else{
            return false;
        }

    }

    // 5. 친구 목록 출력
    public ArrayList<FreindsDto> friendsPrint(){
        return MenuDAO.MDAO.friendsPrint(CharacterController.cController.loginCno);
    }   // friendsPrint() end

    // 6-1 친구 추가
    public boolean addFriends(String newFreinds){
        return MenuDAO.MDAO.addFriends(newFreinds , CharacterController.cController.loginCno);
    }   // addFriends() end

    // 6-2 받은 친구 요청
    public  ArrayList<FreindsDto> receivedFriends(){
        return MenuDAO.MDAO.receivedFriends(CharacterController.cController.loginCno);
    }   // receivedFriends() end

    // 6-3 친구요청 수락
    public boolean acceptRequest(int fromckey){
        return MenuDAO.MDAO.acceptRequest(fromckey);
    }   // acceptRequest() end

    //7 증가
    public void plusMoney( int money ){
        int ckey = CharacterController.cController.loginCno;
        MenuDAO.MDAO.plusMoney(money,ckey);
    }

    //8 감소
    public  void minusMoney(){

    }
}
