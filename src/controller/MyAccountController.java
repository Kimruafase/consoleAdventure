package controller;

import model.dao.MyAccountDao;
import model.dto.MyAccountDto;

public class MyAccountController {

    private static MyAccountController myAccountController = new MyAccountController();
    // 2. 해당 클래스의 생성자에 private 한다. 다른 클래스에서 new 사용하지 못하게
    private MyAccountController(){}
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static MyAccountController getInstance(){
        return myAccountController;
    }
    // 1. 회원가입
    public boolean signup(MyAccountDto myAccountDto) {
        return MyAccountDao.getInstance().signup(myAccountDto);
    }   // signup()

    public int loginAkey = 0;
    // 2. 로그인
    public boolean login(MyAccountDto myAccountDto){
        int result = MyAccountDao.getInstance().login(myAccountDto);
        if(result != 0){    // 만약 result 값이 0이 아니면
            loginAkey = result;  // 로그인 성공한 회원의 번호 저장하는 멤버변수에 결과값을 저장해준다.(회원번호)
        }else {         // 만약 result 값이 0이면
            result = 0; // 로그인 실패이므로 변수에 0 저장
        }   // if end
        return result == 0 ? false : true;
    }   // login() end

    // 3. 아이디찾기
    public String  findID(MyAccountDto myAccountDto){
        return MyAccountDao.getInstance().findID(myAccountDto);
    }   // findID() end

    // 4. 비밀번호찾기
    public String findPWD(MyAccountDto myAccountDto){
        return MyAccountDao.getInstance().findPWD(myAccountDto);
    }   // findPWD() end

    // 5. 로그아웃
    public void logout(){
        loginAkey = 0;
    }   // logout() end

    // 6. 내정보 출력
    public MyAccountDto myInfo(){
        return MyAccountDao.getInstance().myInfo(loginAkey);
    }   // myInfo() end

    // 7.. 회원수정
    public boolean aUpdate(MyAccountDto myAccountDto){
        myAccountDto.setAkey(loginAkey);
        return MyAccountDao.getInstance().aUpdate(myAccountDto);
    }   // aUpdate() end

    // * 비밀번호 확인
    public boolean confirmPw(String confirmPwd){
        return MyAccountDao.getInstance().confirmPw(confirmPwd , loginAkey);
    }   // confirmPw() end

    // 8. 회원탈퇴
    public boolean aDelete(String confirmPwd){
        return MyAccountDao.getInstance().aDelete(confirmPwd , loginAkey);
    }   // aDelete() end

}   // class end
