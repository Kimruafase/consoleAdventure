package controller;

import model.dao.DungeonDao;
import model.dto.*;
import view.DungeonView;
import view.MenuView;

import java.util.ArrayList;

public class DungeonController {


    CharacterDto characterDto = new CharacterDto();
    DungeonDto_Monster dungeonDtoMonster = new DungeonDto_Monster();
    DungeonDto_Dungeon dungeonDtoDungeon = new DungeonDto_Dungeon();
    SkillDto skillDto = new SkillDto();

    private static DungeonController dungeonController = new DungeonController();
    private DungeonController(){}

    public static DungeonController getInstance(){
        return dungeonController;
    }

    //  게임 종료를 판단하는 함수
    public void gameOver( ) {
        if (characterDto.getChp() <= 0) {
            characterDto.setChp(100);
            System.out.println("\n-----------------------------------------\n");
            System.out.println("[[캐릭터의 체력이 0 이하이므로 클리어하지 못했습니다.]]\n");
            System.out.println("=============== GAME OVER =============== ");
            MenuView.mView.index2();
        } else if (dungeonDtoDungeon.getDungeonState() >= 100) {
            characterDto.setChp(100);
            System.out.println("\n-----------------------------------------\n");
            System.out.println("[[던전을 클리어하셨습니다! 축하드립니다!]]\n");
            System.out.println("=============== GAME CLEAR ============== ");
            MenuView.mView.index2();
        } else {
            DungeonView.getInstance().goOrBack();
        }

    }
    // 0. 주사위 메소드
    public int dice(){
        return (int)(Math.random() * 6 + 1);
    }

    // 1. 전투 메소드
    public void myCharacterFight(String select){
        MySkillDto mySkillDto = DungeonDao.getInstance().useSkill(CharacterController.cController.loginCno, select);
        int Cdamage = skillDto.getSkdamage() + mySkillDto.getSkdamage();
        dungeonDtoMonster.setmHp(dungeonDtoMonster.getmHp()-Cdamage);
        characterDto.setChp(characterDto.getChp()-dungeonDtoMonster.getmDamage());
    }
    // 1-1. 전투 결과 메소드
    public void fightResult(){
        System.out.println("\n-----------------------------------------\n");
        System.out.println("나의 체력 : " + characterDto.getChp());
        System.out.println("몬스터의 체력 : " + dungeonDtoMonster.getmHp());
        System.out.println("\n-----------------------------------------\n");

        if (characterDto.getChp() <= 0) {
            characterDto.setChp(characterDto.getChp());
            dungeonDtoMonster.setmHp(100);
            gameOver();
        } else if (dungeonDtoMonster.getmHp() <= 0) {
            System.out.println("전투에서 승리하였습니다!\n");
            characterDto.setChp(characterDto.getChp());
            dungeonDtoMonster.setmHp(100);
            dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+dungeonDtoDungeon.getDungeonStateChange());
            System.out.println("진행도가 " + dungeonDtoDungeon.getDungeonStateChange() + "%만큼 증가합니다.\n");
            System.out.println("진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
            gameOver();
        }
    }
    // 2. 체력 회복 함수
    public void hpRecovery( ){
        if(characterDto.getChp() < 100){
            System.out.println("체력을 " + characterDto.getcHpChange() + "만큼 회복합니다.");
            characterDto.setChp(characterDto.getChp()+characterDto.getcHpChange());
            if(characterDto.getChp() > 100){
                characterDto.setChp(100);
            }
        }else{
            System.out.println("최대 체력이므로 더 이상 회복하지 못합니다.");
            characterDto.setChp(100);
        }
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+dungeonDtoDungeon.getDungeonStateChange());
        System.out.println("현재 체력은 " + characterDto.getChp() + "입니다.\n");
        System.out.println("진행도가 " + dungeonDtoDungeon.getDungeonStateChange() + "%만큼 증가합니다.\n");
        System.out.println("진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  3. 강제 귀환 함수
    public void backToTheBeginning( ){
        dungeonDtoDungeon.setDungeonState(0);
        System.out.println("진행도가 " + dungeonDtoDungeon.getDungeonState() + "%로 초기화됩니다...\n");
        System.out.println("진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  4. 그대로 진행 함수
    public void goContinue( ){
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+dungeonDtoDungeon.getDungeonStateChange());
        System.out.println("진행도가 " + dungeonDtoDungeon.getDungeonStateChange() + "%만큼 증가합니다.\n");
        System.out.println("진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  5. 뒤로 가기 함수
    public void goBackOneSpace( ){

        if(dungeonDtoDungeon.getDungeonState() > 0){
            dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()-dungeonDtoDungeon.getDungeonStateChange());
        }else {
            dungeonDtoDungeon.setDungeonState(0);
        }
        System.out.println("진행도가 " + dungeonDtoDungeon.getDungeonStateChange() + "%만큼 감소합니다.\n");
        System.out.println("진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }
    //  6. 함정 발동 함수
    public void meetTrap(){
        characterDto.setChp(characterDto.getChp()-characterDto.getcHpChange());
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+dungeonDtoDungeon.getDungeonStateChange());
        System.out.println("체력이 " + characterDto.getcHpChange() + "만큼 감소합니다. ");
        System.out.println("현재 체력은 " + characterDto.getChp() + "입니다.\n");
        System.out.println("진행도가 " + dungeonDtoDungeon.getDungeonStateChange() + "%만큼 증가합니다.\n");
        System.out.println("진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  7. 던전 난이도 설정 함수
    public void dungeonDifficulty(int difficulty){
        if(difficulty == 3){
            dungeonDtoDungeon.setDungeonDiff(difficulty + 1);
        }else{
            dungeonDtoDungeon.setDungeonDiff(difficulty);
        }
        DungeonView.getInstance().dungeonIndex();
    }
    //  8. 몬스터 정보 표시 함수
//    public ArrayList<DungeonDto_Monster> monsterPrint(int random){
//        ArrayList<DungeonDto_Monster> list =
//    }
}
