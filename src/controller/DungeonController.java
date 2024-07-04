package controller;

import model.dao.DungeonDao;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.MySkillDto;
import model.dto.SkillDto;

import java.util.ArrayList;

public class DungeonController {


    CharacterDto characterDto = new CharacterDto();
    DungeonDto_Monster dungeonDtoMonster = new DungeonDto_Monster();
    SkillDto skillDto = new SkillDto();

        dungeonDtoMonster.setmHp(mHp);
        skillDto.setSkdamage(cDamage);
        characterDto.setChp(cHp);

    private static DungeonController dungeonController = new DungeonController();
    private DungeonController(){}

    public static DungeonController getInstance(){
        return dungeonController;
    }
    // 1. 전투 메소드
    public void myCharacterFight( DungeonDto_Monster dungeonDtoMonster, SkillDto skillDto,int mDamage, String select){
        int Chp = characterDto.getChp();
        int Mhp = dungeonDtoMonster.getmHp();
        MySkillDto mySkillDto = DungeonDao.getInstance().useSkill(CharacterController.cController.loginCno, select);
        int Cdamage = skillDto.getSkdamage() + mySkillDto.getSkdamage();
        Mhp -= Cdamage;
        Chp -= mDamage;
        characterDto.setChp(Chp);
        dungeonDtoMonster.setmHp(Mhp);
    }

    //  8. 몬스터 정보 표시 함수
//    public ArrayList<DungeonDto_Monster> monsterPrint(int random){
//        ArrayList<DungeonDto_Monster> list =
//    }
}
