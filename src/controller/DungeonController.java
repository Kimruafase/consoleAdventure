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
            dungeonDtoDungeon.setDungeonState(0);
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t[[캐릭터의 체력이 0 이하이므로 클리어하지 못했습니다.]]\n");
            System.out.println("\t=============== GAME OVER =============== ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t메인 메뉴로 돌아갑니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            MenuView.mView.index2();
        } else if (dungeonDtoDungeon.getDungeonState() >= 100) {
            characterDto.setChp(100);
            dungeonDtoDungeon.setDungeonState(0);
            characterDto.setcExp(20 * dungeonDtoDungeon.getDungeonDiff());
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t[[던전을 클리어하셨습니다! 축하드립니다!]]\n");
            getDungeonClearExp();
            System.out.println("\t=============== GAME CLEAR ============== ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t메인 메뉴로 돌아갑니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
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
        int Cdamage = (skillDto.getSkdamage()/dungeonDtoDungeon.getDungeonDiff()) + mySkillDto.getSkdamage();
        dungeonDtoMonster.setmHp(dungeonDtoMonster.getmHp()-Cdamage);
        characterDto.setChp(characterDto.getChp() - (dungeonDtoMonster.getmDamage() * dungeonDtoDungeon.getDungeonDiff()));
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t현재 당신의 캐릭터가 몬스터에게 가한 데미지는 " + Cdamage + "입니다.");
        System.out.println("\t현재 몬스터가 당신의 캐릭터에게 가한 데미지는 " + (dungeonDtoMonster.getmDamage() * dungeonDtoDungeon.getDungeonDiff()) + "입니다. ");
    }
    // 1-1. 전투 결과 메소드
    public void fightResult(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t나의 체력 : " + characterDto.getChp());
        System.out.println("\t몬스터의 체력 : " + dungeonDtoMonster.getmHp());
        System.out.println("\n----------------------------------------------------------------------------------\n");

        if (characterDto.getChp() <= 0) {
            characterDto.setChp(characterDto.getChp());
            dungeonDtoMonster.setmHp(100);
            gameOver();
        } else if (dungeonDtoMonster.getmHp() <= 0) {
            System.out.println("\t전투에서 승리하였습니다!\n");
            characterDto.setChp(characterDto.getChp());
            dungeonDtoMonster.setmHp(100);
            dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+(dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()));
            System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()) + "%만큼 증가합니다.\n");
            System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
            gameOver();
        }
    }
    // 2. 체력 회복 함수
    public void hpRecovery( ){
        if(characterDto.getChp() < 100){
            System.out.println("\t체력을 " + (int)(characterDto.getcHpChange()/dungeonDtoDungeon.getDungeonDiff()) + "만큼 회복합니다.");
            characterDto.setChp(characterDto.getChp() + (int)(characterDto.getcHpChange() / dungeonDtoDungeon.getDungeonDiff()));
            if(characterDto.getChp() > 100){
                characterDto.setChp(100);
            }
        }else{
            System.out.println("\t최대 체력이므로 더 이상 회복하지 못합니다.");
            characterDto.setChp(100);
        }
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+(dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()));
        System.out.println("\t현재 체력은 " + characterDto.getChp() + "입니다.\n");
        System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()) + "%만큼 증가합니다.\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  3. 강제 귀환 함수
    public void backToTheBeginning( ){
        dungeonDtoDungeon.setDungeonState(0);
        System.out.println("\t진행도가 " + dungeonDtoDungeon.getDungeonState() + "%로 초기화됩니다...\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  4. 그대로 진행 함수
    public void goContinue( ){
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+ (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()));
        System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()) + "%만큼 증가합니다.\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  5. 뒤로 가기 함수
    public void goBackOneSpace( ){
        if(dungeonDtoDungeon.getDungeonState() > 0){
            dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()-(dungeonDtoDungeon.getDungeonStateChange() * dungeonDtoDungeon.getDungeonDiff()));
            if(dungeonDtoDungeon.getDungeonState() < 0){
                dungeonDtoDungeon.setDungeonState(0);
            }
        }else {
            dungeonDtoDungeon.setDungeonState(0);
        }
        System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() * dungeonDtoDungeon.getDungeonDiff()) + "%만큼 감소합니다.\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }
    //  6. 함정 발동 함수
    public void meetTrap(){
        characterDto.setChp(characterDto.getChp()-(characterDto.getcHpChange() + (5 * dungeonDtoDungeon.getDungeonDiff())));
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState() + (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()));
        System.out.println("\t체력이 " + (characterDto.getcHpChange() + (5 * dungeonDtoDungeon.getDungeonDiff())) + "만큼 감소합니다. ");
        System.out.println("\t현재 체력은 " + characterDto.getChp() + "입니다.\n");
        System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()) + "%만큼 증가합니다.\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  7. 던전 난이도 설정 함수
    public void dungeonDifficulty(int difficulty){
        if(difficulty == 3){
            dungeonDtoDungeon.setDungeonDiff(difficulty + 1);
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\tHARD 난이도 던전으로 입장합니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else if(difficulty == 2){
            dungeonDtoDungeon.setDungeonDiff(difficulty);
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\tNORMAL 난이도 던전으로 입장합니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else{
            dungeonDtoDungeon.setDungeonDiff(difficulty);
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\tEASY 난이도 던전으로 입장합니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println();
        System.out.println();
        System.out.println("\t>>                   던전 입장 중...                    <<");
        System.out.println();
        System.out.println();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        DungeonView.getInstance().dungeonIndex();
    }

    public int mKey = 0;

    //  8-1. 몬스터 테이블 먼저 가져오는 함수
    public int monsterTable(){
        ArrayList<DungeonDto_Monster> list = DungeonDao.dungeonDao.monsterTable();
        mKey = list.size();
        int random = (int)(Math.random()*mKey+1);
        return random;
    }

    //  8. 몬스터 정보 표시 함수
    public ArrayList<DungeonDto_Monster> monsterPrint(){
        ArrayList<DungeonDto_Monster> list = DungeonDao.getInstance().monsterPrint(monsterTable());
        return list;
    }

    //  9. 레벨 및 경험치 함수
    public void characterLevelAndExp(){
        int cExp = DungeonDao.getInstance().characterLevelAndExp(CharacterController.cController.loginCno);
        int cLevel = cExp % 100;
        characterDto.setcLevel(characterDto.getcLevel()+cLevel);
    }

    //  10. 던전 클리어 후 경험치 추가 함수
    public void getDungeonClearExp(){
        int cExp = characterDto.getcExp();
        System.out.println("\t경험치를 " + cExp + "만큼 획득하셨습니다.");
        DungeonDao.getInstance().getDungeonClearExp(cExp,CharacterController.cController.loginCno);
    }
}
