package view;

import controller.AdminController;
import controller.MyAccountController;
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
        boolean adminLoginState = AdminController.getInstance().adminLoginState();
        while (true){
            if(!adminLoginState){
                System.out.println(">> 관리자만입장가능합니다.");
                boolean result = adminLogin();
                if(result){
                    adminMenu();
                }
            }else {
                adminMenu();
            }
        }   // while end
    }   // adminIndex() end

    // 메뉴버튼
    public void adminMenu(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println(">> 0. 로그아웃 1. 회원관리 2. 게임관리 : ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        int ch = scan.nextInt();
        if(ch==0){
            adminLogout();
            MyAccountView.getInstance().index();
        } else if(ch==1){
            adminAccount();
        } else if (ch==2) {
            AdminGameView.getInstance().index();
        }
    }   // adminMenu() end

    // 로그아웃
    public void adminLogout(){
        AdminController.getInstance().adminLogout();
        System.out.println(">> 로그아웃 성공 [초기메뉴로]");
    }   // adminLogout() end

    // 회원관리 페이지
    public void adminAccount(){
        System.out.println("// ================== 회원관리 페이지 ================== //");
        System.out.print(">> 0. 뒤로가기 1. 전체회원출력 2. 회원검색 : ");
        int ch = scan.nextInt();
        if(ch==0){
            adminIndex();     // 여기서 뒤로가면 회원관리할지 게임관리할지 메뉴로 돌아가야함 그런데 로그인은 되어있어야함.
        } else if(ch==1){
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
        System.out.println("// ============================== 전체회원 ============================== //");
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
        if(list.isEmpty()){
            System.out.println(">> 검색한 회원 결과가 없습니다.");
            adminAccount();
        }
        System.out.println("// ============================== 회원정보 ============================== //");
        System.out.println("회원번호  회원아이디  회원비밀번호  회원이름      연락처     생년월일     회원가입일");
        list.forEach(dto -> {
            System.out.printf("%3d %8s %10s %7s %13s %7s %s\n" , dto.getAkey() , dto.getAid() ,
                    dto.getApwd() , dto.getAname(),  dto.getAnum(), dto.getAbirth(), dto.getAdate());
        });
        System.out.print(">> 0. 뒤로가기 1. 회원정보수정 2. 회원정보삭제 : ");
        int ch = scan.nextInt();
        if(ch==0){
            adminAccount();
        } else if(ch==1){
            adminAccountUpdate();
            adminAccount();
        } else if (ch==2) {
            adminAccountDelete();
            adminAccount();
        }
    }   // accountSearch() end

    // 회원수정
    public void adminAccountUpdate(){
        System.out.print(">> 변경할 회원의 번호 : ");  int akey = scan.nextInt();
        System.out.print(">> 변경할 새로운 연락처 (-제외) : ");  String anum = scan.next();
        boolean result = AdminController.getInstance().adminAccountUpdate(akey , anum);

        if(result){
            System.out.println(">> 회원정보를 성공적으로 수정했습니다.");
        }else {
            System.out.println(">> 회원정보 수정에 실패했습니다.");
        }
    }   // adminAccountUpdate() end

    // 회원삭제
    public void adminAccountDelete(){
        System.out.print(">> 삭제할 회원의 번호 : ");  int akey = scan.nextInt();
        boolean result = AdminController.getInstance().adminAccountDelete(akey);

        if(result){
            System.out.println(">> 회원정보를 성공적으로 삭제했습니다..");
        }else {
            System.out.println(">> 회원정보 삭제에 실패했습니다.");
        }
    }   // adminAccountDelete() end
}   // class end
