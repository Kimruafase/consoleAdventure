package view;

import controller.AdminGameController;
import model.dto.CharacterDto;
import model.dto.DungeonDto_Monster;
import model.dto.SkillDto;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminGameView {
    private static AdminGameView adminGameView = new AdminGameView();
    private AdminGameView(){}
    public static AdminGameView getInstance(){
        return adminGameView;
    }
    Scanner scan = new Scanner(System.in);

    // 게임관리 첫번째 페이지
    public void index(){
        while (true){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("// ================== 게임관리 페이지 ================== //");
            System.out.println("\t>> 0. 뒤로가기 1. 캐릭터관리 2. 몬스터관리 3. 스킬관리 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            int ch = scan.nextInt();
            if(ch==0){
                AdminView.getInstance().adminIndex();
            } else if (ch==1) {
                adminCharacter();
            } else if (ch == 2) {
                adminMonster();
            } else if (ch==3) {
                adminSkill();
            }else{
                System.out.println("\n----------------------------------------------------------------------------------\n");
                System.out.println(">> 없는 기능번호입니다. ");
                System.out.println("\n----------------------------------------------------------------------------------\n");
            }
        }   // while end

    }   // index() end

    // 1. 캐릭터관리 페이지
    public void adminCharacter(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ================== 캐릭터관리 페이지 ================== //");
        System.out.println("\t>> 0. 뒤로가기 1. 캐릭터 전체 출력 ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            characterAllPrint();
        }
    }
    // 1-1 캐릭터 전체 출력
    public void characterAllPrint(){
       ArrayList<CharacterDto> list = AdminGameController.getInstance().characterAllPrint();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ==================== 전체 캐릭터 ==================== //");
        System.out.println("캐릭터번호  닉네임  회원아이디 ");
        list.forEach(dto -> {
            System.out.printf("%3d %8s %8s \n" , dto.getCkey() , dto.getCnickname() ,
                    dto.getAid());
        });
        System.out.println("\n----------------------------------------------------------------------------------\n");
        adminCharacter2();
    }   // characterAllPrint() end

    // 1-2 캐릭터 세부 관리 페이지
    public void adminCharacter2(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 0. 뒤로가기 1. 캐릭터삭제 : ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            characterDelete();
        }

    }   // adminCharacter2() end

    // 1-3 캐릭터 삭제
    public void characterDelete(){
        System.out.println("// ==================== 캐릭터 삭제 ==================== //");
        System.out.print(">> 삭제할 캐릭터 번호를 입력하세요 : ");
        int ckey = scan.nextInt();
        boolean result = AdminGameController.getInstance().characterDelete(ckey);
        if(result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 캐릭터 삭제 성공");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            characterAllPrint();
        }else{
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 캐릭터 삭제 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }   // characterDelete() end

    /*// 2. 던전관리 페이지
    public void adminDungeon(){
        System.out.println("// ================== 캐릭터관리 페이지 ================== //");
        System.out.print(">> 0. 뒤로가기 1. 던전맵 전체 출력 : ");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            // dungeonAllPrint();
        }
    }   // adminDungeon() end*/

    /*// 2-1 던전맵 전체 출력
    public void dungeonAllPrint(){
        ArrayList<DungeonDto_Dungeon> list = AdminGameController.getInstance().dungeonAllPrint();
        System.out.println("// ==================== 전체 던전맵 ==================== //");


    }   // dungeonAllPrint() end*/

    // 3. 몬스터 관리 페이지
    public void adminMonster(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ================== 몬스터관리 페이지 ================== //");
        System.out.println("\t>> 0. 뒤로가기 1. 몬스터 전체 출력 ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            monsterAllPrint();
        }
    }   // adminDungeon() end

    // 3-1 몬스터 전체 출력
    public void monsterAllPrint(){
        ArrayList<DungeonDto_Monster> list = AdminGameController.getInstance().monsterAllPrint();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ==================== 전체 몬스터 ==================== //");
        System.out.println("몬스터번호  몬스터이름  몬스터이미지 ");
        list.forEach(dto -> {
            System.out.printf("%3d %8s \n %s\n" , dto.getMkey() , dto.getMname() , dto.getMimage());
        });
        System.out.println("\n----------------------------------------------------------------------------------\n");
        adminMonster2();
    }   // monsterAllPrint() end

    // 3-2 몬스터 세부 관리페이지
    public void adminMonster2(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 0. 뒤로가기 1. 몬스터추가 2. 몬스터 삭제 ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
            if(ch==0){
                adminMonster();
            } else if (ch==1) {
                addMonster();
            } else if (ch==2) {
                deleteMonster();
            }
    }   // adminMonster2() end

    // 3-3 몬스터 추가 페이지
    public void addMonster(){
        System.out.print(">> 추가할 몬스터 이름을 입력해주세요 : ");
        String mname = scan.next();
        scan.nextLine();
        StringBuilder input = new StringBuilder();
        System.out.println(">> 추가할 몬스터 이미지를 입력해주세요 :");
        boolean isFirstLine = true;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            // 첫 번째 줄이 아니면 줄바꿈 추가
            if (!isFirstLine) {
                input.append("\n");
            }
            // 입력이 빈 줄일 때까지 처리
            if (line.isEmpty()) {
                break;
            }
            input.append(line);
            isFirstLine = false;
        }
        // scan.close();
        String mimage = input.toString();

        boolean result = AdminGameController.getInstance().addMonster(mname, mimage);

        if (result) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println(">> 몬스터 추가 성공");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            monsterAllPrint();

        } else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println(">> 몬스터 추가 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }

    }   // addMonster() end

    // 3-4. 몬스터 삭제
    public void deleteMonster(){
        System.out.print(">> 삭제할 몬스터의 번호를 입력해주세요. : ");
        int mkey = scan.nextInt();
        boolean result = AdminGameController.getInstance().deleteMonster(mkey);
        if(result) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println(">> 삭제 성공");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            adminMonster2();
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println(">> 삭제 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }   // deleteMonster() end

    // 4. 스킬관리 페이지
    public void adminSkill() {
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ================== 스킬관리 페이지 ================== //");
        System.out.println("\t>> 0. 뒤로가기 1. 스킬 전체 출력 ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if (ch == 0) {
            index();
        } else if (ch == 1) {
            skillAllPrint();
        }
    }

    // 4-1. 스킬전체 프린트
    public void skillAllPrint(){
        ArrayList<SkillDto> list = AdminGameController.getInstance().skillAllPrint();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("// ==================== 전체 스킬 ==================== //");
        System.out.println("스킬번호  스킬이름  스킬정보  스킬데미지 ");
        list.forEach(dto -> {
            System.out.printf("%3d %8s %s %d \n" , dto.getSkkey() , dto.getSkname() , dto.getSkinfo() , dto.getSkdamage());
        });
        System.out.println("\n----------------------------------------------------------------------------------\n");
        adminSkill2();
    }   // skillAllPrint() end
    
    // 4-2. 스킬 세부관리 페이지
    public void adminSkill2(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 0. 뒤로가기 1. 스킬추가 2. 스킬수정 3. 스킬삭제 : ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch==0){
            adminSkill();
        } else if (ch==1) {
            addSkill();
        } else if (ch==2) {
            updateSkill();
        } else if (ch==3) {
            deleteSkill();
        }
    }   // adminSkill2() end

    // 4-3. 스킬추가
    public void addSkill(){
        System.out.print(">> 추가할 스킬의 이름 : ");   String skname = scan.nextLine();
        scan.nextLine();
        System.out.print(">> 추가할 스킬의 정보 : "); String skinfo = scan.nextLine();
        System.out.print(">> 추가할 스킬의 데미지 : ");    int skdamage = scan.nextInt();

        SkillDto skillDto = new SkillDto();
        skillDto.setSkname(skname);
        skillDto.setSkinfo(skinfo);
        skillDto.setSkdamage(skdamage);

        boolean result = AdminGameController.getInstance().addSkill(skillDto);
        if(result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 스킬 추가 성공 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            skillAllPrint();
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 스킬 추가 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }   // addSkill() end

    // 4-4. 스킬수정
    public void updateSkill(){
        System.out.print(">> 수정할 스킬의 번호 : ");   int skkey = scan.nextInt();
        scan.nextLine();
        System.out.print(">> 수정할 스킬의 새로운 정보 : "); String skinfo = scan.nextLine();
        System.out.print(">> 수정할 스킬의 새로운 데미지 : ");  int skdamage = scan.nextInt();

        SkillDto skillDto = new SkillDto();
        skillDto.setSkkey(skkey);
        skillDto.setSkinfo(skinfo);
        skillDto.setSkdamage(skdamage);

        boolean result = AdminGameController.getInstance().updateSkill(skillDto);
        if(result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 스킬 수정 성공 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            skillAllPrint();
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 스킬 수정 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }   // updateSkill() end

    // 4-5. 스킬삭제
    public void deleteSkill(){
        System.out.print(">> 삭제할 스킬의 번호 : ");   int skkey = scan.nextInt();
        boolean result = AdminGameController.getInstance().deleteSkill(skkey);
        if(result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 스킬 삭제 성공 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            skillAllPrint();
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t>> 스킬 삭제 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }   // deleteSkill() end

}   // AdminGameView class end
