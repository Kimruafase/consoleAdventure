package controller;

import model.dao.AdminGameDao;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Dungeon;
import model.dto.DungeonDto_Monster;
import model.dto.SkillDto;

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

   /* // 2-1 던전맵 전체 출력
    public ArrayList<DungeonDto_Dungeon> dungeonAllPrint(){

    }   // dungeonAllPrint() end*/

    // 3-1 몬스터 전체 출력
    public ArrayList<DungeonDto_Monster> monsterAllPrint(){
        return AdminGameDao.getInstance().monsterAllPrint();
    }   // monsterAllPrint() end

    // 3-3 몬스터 추가 페이지
    public boolean addMonster(String manme , String mimage){
        return AdminGameDao.getInstance().addMonster(manme , mimage);
    }   // addMonster() end

    // 3-4. 몬스터 삭제
    public boolean deleteMonster(int mkey){
        return AdminGameDao.getInstance().deleteMonster(mkey);
    }   // deleteMonster() end

    // 4-1. 스킬전체 프린트
    public  ArrayList<SkillDto> skillAllPrint(){
        return AdminGameDao.getInstance().skillAllPrint();
    }   // skillAllPrint() end

    // 4-3. 스킬추가
    public boolean addSkill(SkillDto skillDto){
        return AdminGameDao.getInstance().addSkill(skillDto);
    }   // addSkill() end

    // 4-4. 스킬수정
    public boolean updateSkill(SkillDto skillDto){
        return AdminGameDao.getInstance().updateSkill(skillDto);
    }   // updateSkill() end

    // 4-5. 스킬삭제
    public boolean deleteSkill(int skkey){
        return AdminGameDao.getInstance().deleteSkill(skkey);
    }   // deleteSkill() end

}   // class end
