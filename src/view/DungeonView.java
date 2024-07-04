package view;

import controller.DungeonController;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.SkillDto;

import java.util.ArrayList;
import java.util.Scanner;

public class DungeonView {
    Scanner scan = new Scanner(System.in);

    private static DungeonView dungeonView = new DungeonView();
    private DungeonView(){}

    public static DungeonView getInstance(){
        return dungeonView;
    }
    //  던전의 진행 상황 변수
    int dungeonState = 0;
    int dungeonStateChange = 20;
    //  캐릭터와 몬스터의 체력과 데미지 설정
    int cDamage = 20;
    int cHp = 100;
    int mDamage = 10;
    int mHp = 100;
    int cHpChange = 50;
    int Mhp = 0;
    public void dungeonIndex(){
                try {
                    int ch = (int)(Math.random() * 6 + 1);
                    if (ch == 1) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        meetMonster();
                    } else if (ch == 2) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("회복의 크리스탈을 발견하였습니다! 체력을 회복합니다.\n");
                        hpRecovery();
                    } else if (ch == 3) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("강제 귀환! 던전의 처음으로 돌아갑니다!\n");
                        backToTheBeginning();
                    } else if (ch == 4) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("아무 일도 일어나지 않았습니다. 그대로 진행합니다.\n");
                        goContinue();
                    } else if (ch == 5) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("길이 막혀있습니다! 뒤로 돌아갑니다.\n");
                        goBackOneSpace();
                    } else if (ch == 6) {
                        System.out.println(ch + "번이 나왔습니다!");
                        System.out.println("-----------------------------------------\n");
                        System.out.println("함정이 발동되었습니다! 체력이 감소합니다.\n");
                        meetTrap();
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
    }


    //  1. 몬스터 조우 함수
    public void meetMonster(){
        fight();
    }

    //  2. 체력 회복 함수
    public void hpRecovery(){
        if(cHp < 100){
            System.out.println("체력을 " + cHpChange + "만큼 회복합니다.");
            cHp += cHpChange;
        }else{
            System.out.println("최대 체력이므로 더 이상 회복하지 못합니다.");
            cHp = 100;
        }
        dungeonState += dungeonStateChange;
        System.out.println("현재 체력은 " + cHp + "입니다.\n");
        System.out.println("진행도가 " + dungeonStateChange + "%만큼 증가합니다.\n");
        System.out.println("진행도 : " + dungeonState + "%");
        gameOver();
    }

    //  3. 강제 귀환 함수
    public void backToTheBeginning(){


        dungeonState = 0;
        System.out.println("진행도가 " + dungeonState + "%로 초기화됩니다...\n");
        System.out.println("진행도 : " + dungeonState + "%");
        gameOver();
    }

    //  4. 그대로 진행 함수
    public void goContinue(){


        dungeonState += dungeonStateChange;
        System.out.println("진행도가 " + dungeonStateChange + "%만큼 증가합니다.\n");
        System.out.println("진행도 : " + dungeonState + "%");
        gameOver();
    }

    //  5. 뒤로 가기 함수
    public void goBackOneSpace(){

        if(dungeonState > 0){
            dungeonState -= dungeonStateChange;
        }else {
            dungeonState = 0;
        }
        System.out.println("진행도가 " + dungeonStateChange + "%만큼 감소합니다.\n");
        System.out.println("진행도 : " + dungeonState + "%");
        gameOver();
    }

    //  6. 함정 발동 함수
    public void meetTrap(){
        cHp -= cHpChange;
        dungeonState += dungeonStateChange;
        System.out.println("체력이 " + cHpChange + "만큼 감소합니다. ");
        System.out.println("현재 체력은 " + cHp + "입니다.\n");
        System.out.println("진행도가 " + dungeonStateChange + "%만큼 증가합니다.\n");
        System.out.println("진행도 : " + dungeonState + "%");
        gameOver();

    }

    //  7. 전투 함수
    public void fight(){
        System.out.println("몬스터를 만났습니다! 전투하시겠습니까?");
        System.out.println("1번 : 전투하기 / 그 외 : 후퇴하기");
        System.out.println("\n-----------------------------------------");
        int ch = scan.nextInt();
        if(ch == 1){
            myCharacterFight();
        }else{
            return;
        }
    }

    public void myCharacterFight(){

        CharacterDto characterDto = new CharacterDto();
        DungeonDto_Monster dungeonDtoMonster = new DungeonDto_Monster();
        SkillDto skillDto = new SkillDto();

        dungeonDtoMonster.setmHp(mHp);
        skillDto.setSkdamage(cDamage);
        characterDto.setChp(cHp);

        while (true){
            System.out.println("스킬을 사용하려면 스킬 이름을 입력하세요. \n기본 공격을 하고 싶다면 아무 키나 누르세요");
            MenuView.mView.skillinfo();
            String select = scan.next();

            DungeonController.getInstance().myCharacterFight(characterDto,dungeonDtoMonster,skillDto,mDamage,select);
            System.out.println("\n-----------------------------------------\n");
            System.out.println("나의 체력 : " + characterDto.getChp());
            System.out.println("몬스터의 체력 : " + dungeonDtoMonster.getmHp());
            System.out.println("\n-----------------------------------------\n");

            if(characterDto.getChp() <= 0){
                cHp = characterDto.getChp();
                gameOver();
                return;
            } else if (dungeonDtoMonster.getmHp() <= 0) {
                System.out.println("전투에서 승리하였습니다!\n");
                cHp = characterDto.getChp();
                dungeonState += 20;
                System.out.println("진행도가 " + dungeonStateChange + "%만큼 증가합니다.\n");
                System.out.println("진행도 : " + dungeonState + "%");
                gameOver();
                return;
            }
        }
    }

    //  8. 몬스터 정보 표시 함수
    public void monsterPrint(){
        int random = (int)(Math.random()*3+1);
//        ArrayList<DungeonDto_Monster> list = DungeonController.getInstance().monsterPrint(random);
    }

    // 진행 or 후퇴 함수
    public void goOrBack(){
        System.out.println("\n-----------------------------------------\n");
        System.out.println(" == 계속 진행하시겠습니까? ==");
        System.out.println(" == 1번 : 진행하기 / 그 외 : 후퇴하기 ==");
        int ch = scan.nextInt();
        if(ch == 1){
            dungeonIndex();
        }else{
            return;
        }
    }

    // 게임 종료 함수
    public void gameOver(){
        if(cHp <= 0){
            System.out.println("\n-----------------------------------------\n");
            System.out.println("[[캐릭터의 체력이 0 이하이므로 클리어하지 못했습니다.]]\n");
            System.out.println("=============== GAME OVER =============== ");
            MenuView.mView.index2();
        } else if (dungeonState >= 100) {
            System.out.println("\n-----------------------------------------\n");
            System.out.println("[[던전을 클리어하셨습니다! 축하드립니다!]]\n");
            System.out.println("=============== GAME CLEAR ============== ");
            MenuView.mView.index2();
        } else{
            goOrBack();
        }


    }


}
