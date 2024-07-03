package controller;

import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.SkillDto;

public class DungeonController {
    private static DungeonController dungeonController = new DungeonController();
    private DungeonController(){}

    public static DungeonController getInstance(){
        return dungeonController;
    }

    public void myCharacterFight(CharacterDto characterDto, DungeonDto_Monster dungeonDtoMonster, SkillDto skillDto){
        int Chp = characterDto.getChp();
        int Mhp = dungeonDtoMonster.getMhp();
        int Cdamage = skillDto.getSkdamage();
        int Mdamage = dungeonDtoMonster.getMdamage();

        Mhp -= Cdamage;
        Chp -= Mdamage;

        characterDto.setChp(Chp);
        dungeonDtoMonster.setMhp(Mhp);
    }

}
