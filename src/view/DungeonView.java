package view;

import controller.DungeonController;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.SkillDto;

import java.util.ArrayList;
import java.util.Scanner;

public class DungeonView {
    Scanner scan = new Scanner(System.in);
    //  싱글톤
    private static DungeonView dungeonView = new DungeonView();
    private DungeonView(){}

    public static DungeonView getInstance(){
        return dungeonView;
    }
    // 싱글톤 end

    public void dungeonIndex( ){        // 던전 메인 인덱스
                try {
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                    System.out.println("\t주사위를 굴리려면 아무 키나 눌러주세요!");
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                    scan.next();
                    // controller 에서 dice 함수를 통해 1 ~ 6 까지의 난수(주사위 구현)를 받음
                    int ch = DungeonController.getInstance().dice();
                    if (ch == 1) {
                        // 주사위 1이 나왔다면 diceResult 에 매개변수 1을 보내고 meetMonster() (몬스터 조우 함수) 호출
                        diceResult(ch);
                        meetMonster();
                    } else if (ch == 2) {
                        // 주사위 2가 나왔다면 diceResult 에 매개변수 2를 보내고 controller 에서 hpRecovery() (체력 회복 함수) 호출
                        diceResult(ch);
                        DungeonController.getInstance().hpRecovery();
                    } else if (ch == 3) {
                        // 주사위 3이 나왔다면 diceResult 에 매개변수 3을 보내고 controller 에서 backToTheBeginning() (진행도 0 함수) 호출
                        diceResult(ch);
                        DungeonController.getInstance().backToTheBeginning();
                    } else if (ch == 4) {
                        // 주사위 4가 나왔다면 diceResult 에 매개변수 4를 보내고 controller 에서 goContinue() (그대로 진행 함수) 호출
                        diceResult(ch);
                        DungeonController.getInstance().goContinue();
                    } else if (ch == 5) {
                        // 주사위 5가 나왔다면 diceResult 에 매개변수 5를 보내고 controller 에서 goBackOneSpace() (뒤로 가기 함수) 호출
                        diceResult(ch);
                        DungeonController.getInstance().goBackOneSpace();
                    } else if (ch == 6) {
                        // 주사위 6이 나왔다면 diceResult 에 매개변수 6을 보내고 controller 에서 meetTrap() (함정 밟기 함수) 호출
                        diceResult(ch);
                        DungeonController.getInstance().meetTrap();
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
    }

    //  0. 주사위 결과 표시 함수
    public void diceResult(int ch){
        // 주사위 난수에 따른 결과 출력
        if(ch == 1){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t" + ch + "번이 나왔습니다!\n");
            System.out.println("\t몬스터를 조우하였습니다! 전투 상황이 발생합니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else if (ch == 2){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t" + ch + "번이 나왔습니다!\n");
            System.out.println("\t회복의 크리스탈을 발견하였습니다! 체력을 회복합니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        } else if (ch == 3) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t" + ch + "번이 나왔습니다!\n");
            System.out.println("\t강제 귀환! 던전의 처음으로 돌아갑니다!\n");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        } else if (ch == 4) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t" + ch + "번이 나왔습니다!\n");
            System.out.println("\t아무 일도 일어나지 않았습니다. 그대로 진행합니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        } else if (ch == 5) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t" + ch + "번이 나왔습니다!\n");
            System.out.println("\t길이 막혀있습니다! 뒤로 돌아갑니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        } else if (ch == 6) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t" + ch + "번이 나왔습니다!\n");
            System.out.println("\t함정이 발동되었습니다! 체력이 감소합니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }

    //  1. 몬스터 조우 함수
    public void meetMonster( ){
        // 주사위 1이 나왔다면 몬스터 이미지를 출력(monsterPrint() 메소드 호출)하고 fight 메소드 호출
        monsterPrint();
        fight();
    }



    //  7. 전투 함수
    public void fight(){
        // 전투 상황에서 전투를 할지 아니면 후퇴를 할지 선택
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t몬스터를 만났습니다! 전투하시겠습니까?");
        System.out.println("\t1번 : 전투하기 / 그 외 : 후퇴하기");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch == 1){
            // 1번을 눌렀다면 전투 메소드 myCharacterFight() 호출 아니면 메인 메뉴로 돌아감.
            myCharacterFight();
        }else{
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t메인 메뉴로 돌아갑니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            MenuView.mView.index2();
        }
    }

    public void myCharacterFight(){
        // 스킬을 사용할 지 기본 공격을 쓸 지 입력하고 입력값을 controller 에 myCharacterFight()에 매개변수로 보내고 fightResult 메소드 호출
        while (true){
            System.out.println("\t스킬을 사용하려면 스킬 이름을 입력하세요. \n\t기본 공격을 하고 싶다면 기본공격을 입력하세요.\n");
            System.out.println("\t몬스터에게 가하는 데미지는 캐릭터 기본 데미지 + 스킬 데미지입니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            MenuView.mView.skillinfo();
            String select = scan.next();

            DungeonController.getInstance().myCharacterFight(select);
            DungeonController.getInstance().fightResult();
        }
    }

    //  8. 몬스터 정보 표시 함수
    public void monsterPrint(){
        // controller 의 monsterPrint 메소드에서 ArrayList 를 반환 받아서 몬스터 정보 출력
        ArrayList<DungeonDto_Monster> list = DungeonController.getInstance().monsterPrint();
        if(list.isEmpty()){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t몬스터 정보가 없습니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else{
            System.out.println("\t몬스터 번호  \t 몬스터 이름");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            list.forEach(monsterInfo -> {
                System.out.printf("\t%7d %7s \n%s \n", monsterInfo.getMkey(),monsterInfo.getMname(),monsterInfo.getMimage());
                System.out.println("\n----------------------------------------------------------------------------------\n");
            });
        }
    }
    //  9. 난이도 설명 함수
    public void dungeonDifficultyExplain(){
        // 설명 들을 던전의 난이도 선택값을 받아서 설명 출력
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t어떤 난이도의 던전 설명을 들으시겠습니까?\n");
        System.out.println("\t1. EASY 2. NORMAL 3. HARD");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch == 1){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\tEASY 난이도 던전은 가장 기본이 되는 던전입니다.\n");
            System.out.println("\t<< EASY 난이도 던전 설정 >>");
            System.out.println("\t진행도 20%\n\t내 체력 100\n\t몬스터 체력 100\n\t체력 회복 수치 50\n\t체력 감소 수치 55\n\t캐릭터 기본 데미지 20\n\t몬스터 기본 데미지 10");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        } else if (ch == 2) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\tNORMAL 난이도 던전은 본격적으로 자신의 운을 체험할 수 있는 던전입니다.\n");
            System.out.println("\t<< NORMAL 난이도 던전 설정 >> ");
            System.out.println("\t진행도 10%\n\t내 체력 100\n\t몬스터 체력 100\n\t체력 회복 수치 25\n\t체력 감소 수치 60\n\t캐릭터 기본 데미지 10\n\t몬스터 기본 데미지 20");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else if(ch == 3){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\tHARD 난이도 던전은 극강의 운빨을 시험할 수 있는 던전입니다.\n\t만약 클리어하신다면 오늘 로또를 사보시는 건 어떨까요?\n");
            System.out.println("\t<< HARD 난이도 던전 설정 >> ");
            System.out.println("\t진행도 5%\n\t내 체력 100\n\t몬스터 체력 100\n\t체력 회복 수치 12\n\t체력 감소 수치 70\n\t캐릭터 기본 데미지 5\n\t몬스터 기본 데미지 40");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }

    // 진행 or 후퇴 함수
    public void goOrBack( ){
        // 던전을 계속 진행할 것인지 1번 선택하면 던전 메인 인덱스로 가고 나머지는 메인 메뉴로 돌아감
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t== 계속 진행하시겠습니까? ==");
        System.out.println("\t== 1번 : 진행하기 / 그 외 : 후퇴하기 ==");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch == 1){
            dungeonIndex();
        }else{
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t메인 메뉴로 돌아갑니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            MenuView.mView.index2();
        }
    }

    //  9. 레벨 및 경험치 함수
    public void characterLevelAndExp(){
        // controller 의 캐릭터 레벨과 경험치 계산 함수 호출
        DungeonController.getInstance().characterLevelAndExp();

    }

}
