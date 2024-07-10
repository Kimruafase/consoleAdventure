package controller;

import model.dao.DungeonDao;
import model.dto.*;
import view.DungeonView;
import view.MenuView;

import java.util.ArrayList;

public class DungeonController {

    // controller 에서 필요한 객체들을 생성자로 생성
    CharacterDto characterDto = new CharacterDto();
    DungeonDto_Monster dungeonDtoMonster = new DungeonDto_Monster();
    DungeonDto_Dungeon dungeonDtoDungeon = new DungeonDto_Dungeon();
    SkillDto skillDto = new SkillDto();

    // 싱글톤
    private static DungeonController dungeonController = new DungeonController();
    private DungeonController(){}

    public static DungeonController getInstance(){
        return dungeonController;
    }

    //  게임 종료를 판단하는 함수
    public void gameOver( ) {
        // 캐릭터의 체력이 0 이하이면 게임 오버,
        // 진행도가 100 이상이면 던전 클리어,
        // 아무것도 해당 안되면 view 의 진행 or 후퇴 함수 호출
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
            MenuController.MController.plusMoney( 10 * dungeonDtoDungeon.getDungeonDiff() );
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t[[던전을 클리어하셨습니다! 축하드립니다!]]\n");
            getDungeonClearExp();
            System.out.println("\n\t골드를 " + (10 * dungeonDtoDungeon.getDungeonDiff()) + "만큼 획득하셨습니다!\n");
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
        // 주사위 구현을 위해서 1 ~ 6 까지의 난수 구현
        return (int)(Math.random() * 6 + 1);
    }

    // 1. 전투 메소드
    public void myCharacterFight(String select){
        // DAO 의 useSkill 메소드에 cKey 와 입력 받은 스킬 문자열을
        // 매개변수로 보내서 캐릭터의 스킬 데미지를 받아와서 몬스터와의 전투 구현
        MySkillDto mySkillDto = DungeonDao.getInstance().useSkill(CharacterController.cController.loginCno, select);
        // 난이도에 따른 기본 데미지 + 캐릭터 스킬 데미지 = 내 캐릭터 데미지
        int Cdamage = (skillDto.getSkdamage()/dungeonDtoDungeon.getDungeonDiff()) + mySkillDto.getSkdamage();
        // 던전 몬스터의 체력 = 몬스터의 현재 체력 - 내 캐릭터 데미지
        dungeonDtoMonster.setmHp(dungeonDtoMonster.getmHp()-Cdamage);
        // 내 캐릭터의 체력 = 내 캐릭터의 현재 체력 - 난이도에 따른 몬스터의 데미지
        characterDto.setChp(characterDto.getChp() - (dungeonDtoMonster.getmDamage() * dungeonDtoDungeon.getDungeonDiff()));
        // 내 캐릭터와 몬스터가 가한 데미지를 출력
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t현재 당신의 캐릭터가 몬스터에게 가한 데미지는 " + Cdamage + "입니다.");
        System.out.println("\t현재 몬스터가 당신의 캐릭터에게 가한 데미지는 " + (dungeonDtoMonster.getmDamage() * dungeonDtoDungeon.getDungeonDiff()) + "입니다. ");
    }
    // 1-1. 전투 결과 메소드
    public void fightResult(){
        // 내 캐릭터의 체력과 몬스터의 체력 출력
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t나의 체력 : " + characterDto.getChp());
        System.out.println("\t몬스터의 체력 : " + dungeonDtoMonster.getmHp());
        System.out.println("\n----------------------------------------------------------------------------------\n");
        // 전투 종료 판단
        if (characterDto.getChp() <= 0) {
            // 내 캐릭터의 체력이 0 이하라면 몬스터의 체력을 100으로 초기화 후
            // gameOver 메소드 호출해서 게임 종료 판단
            characterDto.setChp(characterDto.getChp());
            dungeonDtoMonster.setmHp(100);
            gameOver();
        } else if (dungeonDtoMonster.getmHp() <= 0) {
            // 몬스터의 체력이 0 이하라면 몬스터의 체력을 100으로 초기화 후 진행도 증가 후
            // gameOver 메소드 호출해서 게임 종료 판단
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
        // 내 캐릭터의 체력이 100 이하라면 난이도에 따른 체력 회복
        if(characterDto.getChp() < 100){
            System.out.println("\t체력을 " + (int)(characterDto.getcHpChange()/dungeonDtoDungeon.getDungeonDiff()) + "만큼 회복합니다.");
            characterDto.setChp(characterDto.getChp() + (int)(characterDto.getcHpChange() / dungeonDtoDungeon.getDungeonDiff()));
            if(characterDto.getChp() > 100){
                // 만약에 회복한 체력이 100을 넘어가면 체력을 100으로 고정
                characterDto.setChp(100);
            }
        }else{
            // 체력이 100 이라면 회복 X
            System.out.println("\t최대 체력이므로 더 이상 회복하지 못합니다.");
            characterDto.setChp(100);
        }
        // 그 후 진행도 증가시키고
        // gameOver 메소드 호출해서 게임 종료 판단
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+(dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()));
        System.out.println("\t현재 체력은 " + characterDto.getChp() + "입니다.\n");
        System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()) + "%만큼 증가합니다.\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  3. 강제 귀환 함수
    public void backToTheBeginning( ){
        // 진행도를 강제로 0으로 낮추고
        // gameOver 메소드 호출해서 게임 종료 판단
        dungeonDtoDungeon.setDungeonState(0);
        System.out.println("\t진행도가 " + dungeonDtoDungeon.getDungeonState() + "%로 초기화됩니다...\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  4. 그대로 진행 함수
    public void goContinue( ){
        // 진행도를 난이도에 따라 증가시킨 후
        // gameOver 메소드 호출해서 게임 종료 판단
        dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()+ (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()));
        System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() / dungeonDtoDungeon.getDungeonDiff()) + "%만큼 증가합니다.\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }

    //  5. 뒤로 가기 함수
    public void goBackOneSpace( ){
        // 진행도를 난이도에 따라 감소시킨 후
        // gameOver 메소드 호출해서 게임 종료 판단
        if(dungeonDtoDungeon.getDungeonState() > 0){
            dungeonDtoDungeon.setDungeonState(dungeonDtoDungeon.getDungeonState()-(dungeonDtoDungeon.getDungeonStateChange() * dungeonDtoDungeon.getDungeonDiff()));
            if(dungeonDtoDungeon.getDungeonState() < 0){
                // 진행도를 음수가 되게 할 수 없으므로 진행도가 0인 상태에서는 0으로 고정
                dungeonDtoDungeon.setDungeonState(0);
            }
        }else {
            // 진행도를 음수가 되게 할 수 없으므로 진행도가 0인 상태에서는 0으로 고정
            dungeonDtoDungeon.setDungeonState(0);
        }
        System.out.println("\t진행도가 " + (dungeonDtoDungeon.getDungeonStateChange() * dungeonDtoDungeon.getDungeonDiff()) + "%만큼 감소합니다.\n");
        System.out.println("\t진행도 : " + dungeonDtoDungeon.getDungeonState() + "%");
        gameOver();
    }
    //  6. 함정 발동 함수
    public void meetTrap(){
        // 난이도에 따른 함정 발동으로 체력 감소 시킨 후 진행도 증가 시키고
        // gameOver 메소드 호출해서 게임 종료 판단
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
        // 난이도 선택을 입력받아서 입력받은 값에 따라서 난이도 설정 후 view 의 던전 메인 인덱스로 입장
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
        // DAO 에서 몬스터 테이블의 정보가 담긴 ArrayList 를 반환 받고
        // 그 ArrayList 의 size 만큼을 레코드 갯수로 저장
        ArrayList<DungeonDto_Monster> list = DungeonDao.dungeonDao.monsterTable();
        mKey = list.size();
        // 몬스터 테이블 레코드 갯수만큼의 난수를 생성해서 저장 후 반환
        int random = (int)(Math.random()*mKey+1);
        return random;
    }

    //  8. 몬스터 정보 표시 함수
    public ArrayList<DungeonDto_Monster> monsterPrint(){
        // monsterTable 메소드 호출해서 몬스터 테이블 레코드 갯수만큼의 난수를
        // DAO 의 monsterPrint 메소드에 매개변수로 보내서 ArrayList 로 반환받음
        ArrayList<DungeonDto_Monster> list = DungeonDao.getInstance().monsterPrint(monsterTable());
        return list;
    }

    //  9. 레벨 및 경험치 함수
    public void characterLevelAndExp(){
        // controller 의 cKey 를 DAO 의 레벨 경험치 계산 함수로 보내서
        // 경험치를 반환 받고 경험치에 따른 레벨 증가량 계산 후
        // 캐릭터 dto 의 레벨에 저장 후 레벨 출력
        int cExp = DungeonDao.getInstance().characterLevelAndExp(CharacterController.cController.loginCno);
        int cLevel = (int)(cExp / 100);
        characterDto.setcLevel(characterDto.getcLevel() + cLevel);
        System.out.println("\n\t레벨");
        System.out.println("\t" + characterDto.getcLevel());
    }

    //  10. 던전 클리어 후 경험치 추가 함수
    public void getDungeonClearExp(){
        // 던전 클리어 시 난이도에 따른 경험치를 받아와서
        // 변수에 저장 후 받은 경험치를 출력하고
        // DAO 의 경험치 업데이트 함수에 보냄
        int cExp = characterDto.getcExp();
        System.out.println("\n\t경험치를 " + cExp + "만큼 획득하셨습니다!");
        DungeonDao.getInstance().getDungeonClearExp(cExp,CharacterController.cController.loginCno);
    }

    //  11. 레벨업 함수
//    public void levelUp(){
//        characterDto.setcLevel(characterDto.getcLevel() + 1);
//        DungeonDao.getInstance().levelUp(CharacterController.cController.loginCno);
//        System.out.println("\n\t레벨");
//        System.out.println("\t"+characterDto.getcLevel());
//    }

}
