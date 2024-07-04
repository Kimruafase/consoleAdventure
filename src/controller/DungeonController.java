package controller;

import model.dao.DungeonDao;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.MySkillDto;
import model.dto.SkillDto;

import java.util.ArrayList;

public class DungeonController {
    private static DungeonController dungeonController = new DungeonController();
    private DungeonController(){}

    public static DungeonController getInstance(){
        return dungeonController;
    }
    // 1. 전투 메소드
    public void myCharacterFight(CharacterDto characterDto, DungeonDto_Monster dungeonDtoMonster, SkillDto skillDto,int mDamage, String select){
        int Chp = characterDto.getChp();
        int Mhp = dungeonDtoMonster.getmHp();
        MySkillDto mySkillDto = DungeonDao.getInstance().useSkill(CharacterController.cController.loginCno, select);
        System.out.println(mySkillDto.getSkdamage());
        System.out.println(mySkillDto.getSkname());
        if(mySkillDto.getSkname() == null){
            int Cdamage = skillDto.getSkdamage();
            System.out.println(Cdamage);
            Mhp -= Cdamage;
            System.out.println(Mhp);
        } else {
            int Cdamage = skillDto.getSkdamage() + mySkillDto.getSkdamage();
            System.out.println(Cdamage);
            Mhp -= Cdamage;
            System.out.println(Mhp);
        }
        Chp -= mDamage;
        System.out.println(Chp);
        characterDto.setChp(Chp);
        dungeonDtoMonster.setmHp(Mhp);
    }

    //  8. 몬스터 정보 표시 함수
//    public ArrayList<DungeonDto_Monster> monsterPrint(int random){
//        ArrayList<DungeonDto_Monster> list =
//    }
}
