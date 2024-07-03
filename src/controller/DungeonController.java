package controller;

import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.SkillDto;

import java.util.ArrayList;

public class DungeonController {
    private static DungeonController dungeonController = new DungeonController();
    private DungeonController(){}

    public static DungeonController getInstance(){
        return dungeonController;
    }
    // 1. 전투 메소드
    public void myCharacterFight(CharacterDto characterDto, DungeonDto_Monster dungeonDtoMonster, SkillDto skillDto,int mDamage){
        int Chp = characterDto.getChp();
        int Mhp = dungeonDtoMonster.getmHp();
        int Cdamage = skillDto.getSkdamage();
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
