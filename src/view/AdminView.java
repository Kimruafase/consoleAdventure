package view;

import controller.AdminController;
import model.dto.MyAccountDto;

import java.util.ArrayList;
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
        boolean result = adminLogin();
        if(!result){
            System.out.println(">> 관리자만 입장 가능합니다. ");
            return;
        }
        System.out.print(">> 1. 회원관리 2. 게임관리 : ");
        int ch = scan.nextInt();
        if(ch==1){
            adminAccount();
        } else if (ch==2) {

        }
    }   // adminIndex() end

    // 회원관리 페이지
    public void adminAccount(){
        System.out.println("// ================== 회원관리 페이지 ================== //");
        System.out.print(">> 1. 전체회원출력 2. 회원검색 : ");
        int ch = scan.nextInt();
        if(ch==1){
            accountPrintAll();
        } else if (ch==2) {
            accountSearch();
        }
    }   // adminAccount() end

    // 관리자 로그인
    public boolean adminLogin(){
        System.out.print(">> 관리자 아이디 : ");      String adminID = scan.next();
        System.out.print(">> 관리자 비밀번호 : ");     String adminPw = scan.next();
        boolean result = AdminController.getInstance().adminLogin(adminID , adminPw);
        if(result){
            System.out.println(">> 관리자 로그인 성공");
            return true;
        }else {
            return false;
        }
    }   // adminLogin() end

    // 회원전체 출력
    public void accountPrintAll(){
        ArrayList<MyAccountDto> list = AdminController.getInstance().accountPrintAll();
        System.out.println("// ============================ 전체회원 ============================ //");
        System.out.println("회원번호  회원아이디  회원비밀번호  회원이름      연락처     생년월일     회원가입일");
        list.forEach(dto -> {
            System.out.printf("%3d %8s %10s %7s %13s %7s %s\n" , dto.getAkey() , dto.getAid() ,
                    dto.getApwd() , dto.getAname(),  dto.getAnum(), dto.getAbirth(), dto.getAdate());
        });
        adminAccount();
    }   // accountPrintAll() end

    // 회원검색
    public void accountSearch(){
        System.out.print(">> 검색할 회원의 이름 또는 연락처를 입력하세요 : ");
        String keyword = scan.next();
        ArrayList<MyAccountDto> list = AdminController.getInstance().accountSearch(keyword);
        if(list==null){
            System.out.println(">> 검색한 회원 결과가 없습니다.");
        }
        System.out.println(list);
    }   // accountSearch() end

}   // class end
