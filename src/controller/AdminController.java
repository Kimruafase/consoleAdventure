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
    public int adminLoginKey = 0;
    // 관리자 로그인
    public boolean adminLogin(String adminID , String adminPw){
        boolean result =  AdminDao.getInstance().adminLogin(adminID , adminPw);
        if(result){
            adminLoginKey = 1;
            return true;
        }else {
            adminLoginKey = 0;
            return false;
        }
    }   // adminLogin() end
    // 로그아웃
    public void adminLogout(){
        adminLoginKey = 0;
    }   // adminLogout() end

    // 로그인상태함수
    public boolean adminLoginState(){
        return adminLoginKey == 0 ? false : true;
    }

    // 회원전체 출력
    public ArrayList<MyAccountDto> accountPrintAll(){
        return AdminDao.getInstance().accountPrintAll();
    }   // accountPrintAll() end

    // 회원검색
    public ArrayList<MyAccountDto> accountSearch(String keyword){
        return AdminDao.getInstance().accountSearch(keyword);
    }   // accountSearch() end

    // 회원수정
    public boolean adminAccountUpdate(int akey , String anum){
        return AdminDao.getInstance().adminAccountUpdate(akey , anum);
    }   // adminAccountUpdate() end

    // 회원삭제
    public boolean adminAccountDelete(int akey){
        return AdminDao.getInstance().adminAccountDelete(akey);
    }   // adminAccountDelete() end

}   // class end
