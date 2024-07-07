package controller;

import model.dao.AdminGameDao;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;

import java.util.ArrayList;

public class AdminGameController {
    private static AdminGameController adminGameController = new AdminGameController();
    private AdminGameController(){}
    public static AdminGameController getInstance(){
        return adminGameController;
    }

    // 캐릭터 전체 출력
    public ArrayList<CharacterDto> characterAllPrint(){
        return AdminGameDao.getInstance().characterAllPrint();
    }   // characterAllPrint() end

    // 1-3 캐릭터 삭제
    public boolean characterDelete(int ckey){
        return AdminGameDao.getInstance().characterDelete(ckey);
    }   // characterDelete() end

    // 2-1 던전맵 전체 출력
    public void dungeonAllPrint(){

    }   // dungeonAllPrint() end

    // 3-1 몬스터 전체 출력
    public ArrayList<DungeonDto_Monster> monsterAllPrint(){
        return AdminGameDao.getInstance().monsterAllPrint();
    }   // monsterAllPrint() end

    // 3-3 몬스터 추가 페이지
    public boolean addMonster(String manme){
        return AdminGameDao.getInstance().addMonster(manme);
    }   // addMonster() end

}
