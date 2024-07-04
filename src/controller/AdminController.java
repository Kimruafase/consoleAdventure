package controller;


import model.dao.AdminDao;
import model.dto.MyAccountDto;

import java.util.ArrayList;

public class AdminController {
    private static AdminController adminController = new AdminController();
    // 2. 해당 클래스의 생성자에 private 한다. 다른 클래스에서 new 사용하지 못하게
    private AdminController(){}
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static AdminController getInstance(){
        return adminController;
    }
    // 관리자 로그인 상태
    public boolean adminLoginState = false;
    // 관리자 로그인
    public boolean adminLogin(String adminID , String adminPw){
        boolean result =  AdminDao.getInstance().adminLogin(adminID , adminPw);
        if(result){
            adminLoginState = true;
            return true;
        }else {
            return false;
        }
    }   // adminLogin() end

    // 회원전체 출력
    public ArrayList<MyAccountDto> accountPrintAll(){
        return AdminDao.getInstance().accountPrintAll();
    }   // accountPrintAll() end

    // 회원검색
    public ArrayList<MyAccountDto> accountSearch(String keyword){
        return AdminDao.getInstance().accountSearch(keyword);
    }   // accountSearch() end

}   // class end
