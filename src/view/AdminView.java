package view;

import java.util.Scanner;

public class AdminView {
    private static AdminView adminView = new AdminView();
    // 2. 해당 클래스의 생성자에 private 한다. 다른 클래스에서 new 사용하지 못하게
    private AdminView(){}
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static AdminView getInstance(){
        return adminView;
    }
    Scanner scan = new Scanner(System.in);
    // 관리자 페이지
    public void adminIndex(){
        System.out.println("// ================== 관리자페이지 ================== //");
        adminLogin();

        /*System.out.print(">> 1. 회원관리 2. 게임관리 : ");
        int ch = scan.nextInt();
        if(ch==1){
            adminAccount();
        } else if (ch==2) {

        }*/
    }   // adminIndex() end

    public void adminLogin(){
        System.out.print(">> 관리자 아이디 : ");      String adminID = scan.next();
        System.out.print(">> 관리자 비밀번호 : ");     String adminPw = scan.next();

    }
    public void adminAccount(){
        System.out.println("test");
    }   // adminAccount() end
}   // class end
