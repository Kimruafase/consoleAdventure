package view;

import java.util.Scanner;

public class AdminGameView {
    private static AdminGameView adminGameView = new AdminGameView();
    // 2. 해당 클래스의 생성자에 private 한다. 다른 클래스에서 new 사용하지 못하게
    private AdminGameView(){}
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static AdminGameView getInstance(){
        return adminGameView;
    }
    Scanner scan = new Scanner(System.in);

    // 게임관리 첫번째 페이지
    public void index(){
        while (true){
            System.out.println("// ================== 게임관리 페이지 ================== //");
            System.out.print(">> 0. 뒤로가기 1. 캐릭터관리 2. 던전관리 3. 몬스터관리 4. 스킬관리 : ");
            int ch = scan.nextInt();
            if(ch==0){
                AdminView.getInstance().adminIndex();
            } else if (ch==1) {
                adminCharacter();
            } else if (ch==2) {

            } else if (ch == 3) {

            } else if (ch==4) {

            }else{
                System.out.println(">> 없는 기능번호입니다. ");

            }
        }   // while end

    }   // index() end

    // 1. 캐릭터관리 페이지
    public void adminCharacter(){
        System.out.println("// ================== 캐릭터관리 페이지 ================== //");
        System.out.print(">> 0. 뒤로가기 1. 캐릭터 전체 출력 : ");
        int ch = scan.nextInt();
        if(ch==0){
            index();
        } else if (ch==1) {
            characterAllPrint();
        }
    }
    // 1-1 캐릭터 전채 출력
    public void characterAllPrint(){
        System.out.println("test");
    }

}   // AdminGameView class end
