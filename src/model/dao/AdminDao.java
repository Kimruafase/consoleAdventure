package model.dao;

import model.dto.MyAccountDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminDao {
    private static AdminDao adminDao = new AdminDao();
    // JDBC 인터페이스 3개
    Connection conn;        // 데이터베이스와의 연결을 관리하기 위한 변수
    PreparedStatement ps;   // SQL 쿼리를 실행하기 위해 사용되는 인터페이스
    ResultSet rs;
    // 생성자 연동 코드
    private AdminDao(){
        try {   // 예기치 못한 상황으로 인해 종료되는 것을 방지하고 어떤 오류인지 파악하기 위해 try
            Class.forName("com.mysql.cj.jdbc.Driver");     // (JDBC 구현체) 드라이버 클래스 호출
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase" , "root" , "1234");
            //  DriverManager.getConnection(" DB SEVER URL" , "계정명" , "비밀번호")
        }catch (Exception e){   // 예외가 발생하면
            System.out.println(">> 연동 실패"+e);   // 발생한 이유 출력
        }   // try end
    }
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static AdminDao getInstance(){
        return adminDao;
    }
    // 관리자 로그인
    public boolean adminLogin(String adminID , String adminPw){
        try{
            String sql = "select * from admin where adid = '"+adminID+"' and adpwd = '"+adminPw+"'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // adminLogin() end

    // 회원전체 출력
    public ArrayList<MyAccountDto> accountPrintAll(){
        ArrayList<MyAccountDto> list = new ArrayList<>();
        try{
            String sql = "select * from myaccount";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int akey = rs.getInt("akey");
                String aid = rs.getString("aid");
                String apwd = rs.getString("apwd");
                String aname = rs.getString("aname");
                String anum = rs.getString("anum");
                String abirth = rs.getString("abirth");
                String adate = rs.getString("adate");
                MyAccountDto myAccountDto = new MyAccountDto(akey, aid, apwd, aname, anum, abirth, adate);
                list.add(myAccountDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // accountPrintAll() end

    // 회원검색
    public ArrayList<MyAccountDto> accountSearch(String keyword){
        ArrayList<MyAccountDto> list = new ArrayList<>();
        try{
            String sql = "select * from myaccount where aname = ? or anum = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,keyword);
            ps.setString(2,keyword);
            rs = ps.executeQuery();
            while(rs.next()) {
                int akey = rs.getInt("akey");
                String aid = rs.getString("aid");
                String apwd = rs.getString("apwd");
                String aname = rs.getString("aname");
                String anum = rs.getString("anum");
                String abirth = rs.getString("abirth");
                String adate = rs.getString("adate");
                MyAccountDto myAccountDto = new MyAccountDto(akey, aid, apwd, aname, anum, abirth, adate);
                list.add(myAccountDto);
            }   // while end
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // accountSearch() end

}   // class end
