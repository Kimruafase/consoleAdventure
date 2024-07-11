package view;

import controller.MyAccountController;
import model.dto.MyAccountDto;

import java.util.Scanner;

public class MyAccountView {
    private static MyAccountView myAccountView = new MyAccountView();
    // 2. 해당 클래스의 생성자에 private 한다. 다른 클래스에서 new 사용하지 못하게
    private MyAccountView(){}
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static MyAccountView getInstance(){
        return myAccountView;
    }
    Scanner scan = new Scanner(System.in);
    // 첫화면
    public void index(){
        while (true){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t0. 관리자모드 1. 회원가입 2. 로그인 3. 아이디찿기 4. 비밀번호 찾기 5. 종료 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            int ch = scan.nextInt();
            try{
                if(ch==0){
                    AdminView.getInstance().adminIndex();
                }
                else if(ch==1){
                    signup();
                } else if (ch==2) {
                    login();
                }else if(ch==3){
                    findID();
                } else if (ch==4) {
                    findPWD();
                } else if (ch==5) {
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                    System.out.println("\t프로그램을 종료합니다.");
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                    break;
                } else {
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                    System.out.println("\t없는 기능 번호입니다.");
                    System.out.println("\n----------------------------------------------------------------------------------\n");
                }
            }catch (Exception e){
                System.out.println("\n----------------------------------------------------------------------------------\n");
                System.out.println("\t잘못된 입력입니다. 초기메뉴로 돌아갑니다.");
                System.out.println("\n----------------------------------------------------------------------------------\n");
                scan = new Scanner(System.in);
            }   // try end
        }   // while end
        System.exit(0);
    }   // index() end

    // 로그인 후 페이지
    public void index2(){
        while(true){        // 메뉴 설정을 종료되거나 오류가 생기기 전까지 반복하기 위해 while문
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t1. 로그아웃 2. 내정보 3. 회원수정 4. 회원탈퇴 5. 게임시작  ");   // 컨셉 상 설정한 메뉴를 입력 받기 위해 먼저 안내해준다.
            System.out.println("\n----------------------------------------------------------------------------------\n");
            int ch = scan.nextInt();     // 위에 안내한 메뉴를 정수로 입력 받아 변수에 저장한다.
            if(ch==1){
                logout(); index();
            }else if(ch==2){
                myInfo();
            }else if(ch==3){
                aUpdate();
            }else if(ch==4) {
                aDelete();
            } else if (ch==5) {
                CharacterView.chview.index();
            } else if (ch==6) {
                System.out.println("\n----------------------------------------------------------------------------------\n");
                System.out.println("\t프로그램을 종료합니다.");
                System.out.println("\n----------------------------------------------------------------------------------\n");
                return;
            } else{
                System.out.println("\n----------------------------------------------------------------------------------\n");
                System.out.println("\t없는 기능번호입니다.");
                System.out.println("\n----------------------------------------------------------------------------------\n");
            }   // if end
        }   // while end
    }   // index2() end

    // 1. 회원가입
    public void signup(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        // 1. 입력
        System.out.println("\t>> 회원가입 페이지 <<");                       // 어떤 페이지인지 안내
        // 회원가입 위해 받을 필드 안내 후 sql에 설계해둔 타입에 맞는 타입과 변수명 정해서 입력받아 저장.
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 아이디 : "); String aid = scan.next();
        System.out.print(">> 비밀번호 : "); String apwd = scan.next();
        System.out.print(">> 이름 : "); String aname = scan.next();
        System.out.print(">> 연락처(-제외) : "); String anum = scan.next();
        System.out.print(">> 생년월일(6자리) : "); String abirth = scan.next();

        // 2. 유효성검사
        // 3. 객체화
        MyAccountDto myAccountDto = new MyAccountDto(aid , apwd, aname , anum , abirth);
        // 받은 변수들 한번에 전달 위해 묶음 처리 MemberDto 클래스에서 변수에 맞는 생성자 생성해두었기 때문에 바로 대입해 사용 가능.
        // 4. Controller에게 전달
        boolean result = MyAccountController.getInstance().signup(myAccountDto);   // 전달 후 받은 값을 true or false로 결과 값 저장.

        if(result){         // 만약 result가 true이면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원가입성공");    // 회원가입 성공
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {             // 만약 result가 false이면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원가입실패");    // 회원가입 실패
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }   // if end
    }   // signup() end

    // 2. 로그인
    public void login(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 로그인 페이지 <<");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 아이디 : "); String aid = scan.next();
        System.out.print(">> 비밀번호 : "); String apwd = scan.next();

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setAid(aid);
        myAccountDto.setApwd(apwd);

        boolean result = MyAccountController.getInstance().login(myAccountDto);
        if(result) {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t로그인 성공");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            index2();
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t로그인 실패");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }   // login() end

    // 3. 아이디찾기
    public void findID(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 아이디찾기 페이지 <<");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 이름 : "); String aname = scan.next();    // 이름 입력받아 String 타입 변수에 저장
        System.out.print(">> 연락처(-제외) : "); String anum = scan.next();
        System.out.print(">> 생년월일(6자리) : "); String abirth = scan.next();

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setAname(aname);
        myAccountDto.setAnum(anum);
        myAccountDto.setAbirth(abirth);

        String findID = MyAccountController.getInstance().findID(myAccountDto);
        if(findID != null){     // 만약 result 값이 null이 아니면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원아이디 : "+ findID);     // result에 찾은 아이디를 넣어놨으니까 찾은 아이디 출력
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {                 // 만약 result 값이 null이면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t동일한 회원 정보가 없습니다.");  // 입력한 회원 정보가 없어 오류임을 알려준다.
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }   // if end

    }   // findID() end

    // 4. 비밀번호찾기
    public void findPWD(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 비밀번호 페이지 <<");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 아이디 : "); String aid = scan.next();    // 이름 입력받아 String 타입 변수에 저장
        System.out.print(">> 연락처(-제외) : "); String anum = scan.next();
        System.out.print(">> 생년월일(6자리) : "); String abirth = scan.next();

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setAid(aid);
        myAccountDto.setAnum(anum);
        myAccountDto.setAbirth(abirth);

        String findPWD = MyAccountController.getInstance().findPWD(myAccountDto);
        if(findPWD != null){     // 만약 result 값이 null이 아니면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원비밀번호 : "+ findPWD);     // result에 찾은 아이디를 넣어놨으니까 찾은 아이디 출력
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {                 // 만약 result 값이 null이면
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t동일한 회원 정보가 없습니다.");  // 입력한 회원 정보가 없어 오류임을 알려준다.
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }   // if end
    }   // findPWD() end

    // 5. 로그아웃
    public void logout(){
        MyAccountController.getInstance().logout();
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t로그아웃 성공 [초기메뉴로] ");
        System.out.println("\n----------------------------------------------------------------------------------\n");
    }   // logout() end

    // 6. 내정보 출력
    public void myInfo(){
        System.out.println("// ============== 내정보 ============== //");
        MyAccountDto myAccountDto = MyAccountController.getInstance().myInfo();
        if(myAccountDto == null){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원 정보가 없습니다. ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            return;
        }
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("회원번호 아이디  비밀번호 이름    연락처     생년월일 회원가입일");
        System.out.printf("%3d"+"  "+"%s %s %s %s %s %s \n" , myAccountDto.getAkey() , myAccountDto.getAid() ,
                myAccountDto.getApwd() , myAccountDto.getAname() , myAccountDto.getAnum() ,
                myAccountDto.getAbirth() , myAccountDto.getAdate());
        System.out.println("\n----------------------------------------------------------------------------------\n");

    }   // myInfo() end

    // 7. 회원수정
    public void aUpdate(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 회원정보 수정 페이지 <<");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 비밀번호 입력 : ");      // 회원 수정을 위한 현재 로그인 한 회원의 비밀번호 입력
        String confirmPwd = scan.next();         // 입력 받은 값 String 타입에 저장
        // 결과 값을 boolean으로 받아 유효성 검사를 위해 변수 저장
        boolean confirm = MyAccountController.getInstance().confirmPw(confirmPwd);
        if(!confirm){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t비밀번호가 맞지 않습니다. ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            return;
        }
        System.out.print(">> 변경할 새로운 연락처 (-제외) : ");  String anum = scan.next();
        System.out.print(">> 변경할 새로운 비밀번호 : ");   String apwd = scan.next();

        MyAccountDto myAccountDto = new MyAccountDto();
        myAccountDto.setAnum(anum);
        myAccountDto.setApwd(apwd);

        boolean result = MyAccountController.getInstance().aUpdate(myAccountDto);

        if(result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원정보를 성공적으로 수정했습니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }else {
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원정보 수정에 실패했습니다.");
            System.out.println("\n----------------------------------------------------------------------------------\n");
        }
    }   // aUpdate() end

    // 8. 회원탈퇴
    public void aDelete(){
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.println("\t>> 회원 탈퇴 페이지 <<");
        System.out.println("\n----------------------------------------------------------------------------------\n");
        System.out.print(">> 비밀번호 입력 : ");      // 회원 수정을 위한 현재 로그인 한 회원의 비밀번호 입력
        String confirmPwd = scan.next();         // 입력 받은 값 String 타입에 저장
        // 결과 값을 boolean으로 받아 유효성 검사를 위해 변수 저장
        boolean confirm = MyAccountController.getInstance().confirmPw(confirmPwd);
        if(!confirm){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t비밀번호가 맞지 않습니다. ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            index2();
        }
        boolean result = MyAccountController.getInstance().aDelete(confirmPwd);
        if(result){
            System.out.println("\n----------------------------------------------------------------------------------\n");
            System.out.println("\t회원탈퇴 성공 ");
            System.out.println("\n----------------------------------------------------------------------------------\n");
            index();
        }else {
            index2();
        }
    }   // aDelete() end
    // test
}   // class end
