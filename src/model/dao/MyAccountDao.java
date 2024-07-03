package model.dao;

import model.dto.MyAccountDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyAccountDao {
    private static MyAccountDao myAccountDao = new MyAccountDao();
    // JDBC 인터페이스 3개
    Connection conn;        // 데이터베이스와의 연결을 관리하기 위한 변수
    PreparedStatement ps;   // SQL 쿼리를 실행하기 위해 사용되는 인터페이스
    ResultSet rs;
    // 생성자 연동 코드
    private MyAccountDao(){
        try {   // 예기치 못한 상황으로 인해 종료되는 것을 방지하고 어떤 오류인지 파악하기 위해 try
            Class.forName("com.mysql.cj.jdbc.Driver");     // (JDBC 구현체) 드라이버 클래스 호출
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase" , "root" , "1234");
            //  DriverManager.getConnection(" DB SEVER URL" , "계정명" , "비밀번호")
        }catch (Exception e){   // 예외가 발생하면
            System.out.println(">> 연동 실패"+e);   // 발생한 이유 출력
        }   // try end
    }
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static MyAccountDao getInstance(){
        return myAccountDao;
    }
    // 1. 회원가입
    public boolean signup(MyAccountDto myAccountDto){
        try{
            // sql 작성
            String sql = "insert into myaccount(aid,apwd,aname,anum,abirth) values (?,?,?,?,?)";
            // sql 기재
            ps = conn.prepareStatement(sql);
            ps.setString(1,myAccountDto.getAid());
            ps.setString(2,myAccountDto.getApwd());
            ps.setString(3,myAccountDto.getAname());
            ps.setString(4,myAccountDto.getAnum());
            ps.setString(5,myAccountDto.getAbirth());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
            // 처리
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // signup() end

    // 2. 로그인
    public int login(MyAccountDto myAccountDto){
        try{
            String sql = "select * from myaccount where aid = ? and apwd = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,myAccountDto.getAid());
            ps.setString(2,myAccountDto.getApwd());
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("akey");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }   // login() end

    // 3. 아이디찾기
    public String findID(MyAccountDto myAccountDto){
        try{
            String sql = "select * from myaccount where aname = ? and anum = ? and abirth = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, myAccountDto.getAname());
            ps.setString(2, myAccountDto.getAnum());
            ps.setString(3, myAccountDto.getAbirth());
            rs = ps.executeQuery();
            if(rs.next()){
                String result = rs.getString("aid");
                return result;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }   // findID() end

    // 4. 비밀번호찾기
    public String findPWD(MyAccountDto myAccountDto){
        try{
            String sql = "select * from myaccount where aid = ? and anum = ? and abirth = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, myAccountDto.getAid());
            ps.setString(2, myAccountDto.getAnum());
            ps.setString(3, myAccountDto.getAbirth());
            rs = ps.executeQuery();
            if(rs.next()){
                String result = rs.getString("apwd");
                return result;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }   // findPWD() end

    // 6. 내정보 출력
    public MyAccountDto myInfo(int loginAkey){
        try{
            String sql = "select * from myaccount where akey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,loginAkey);
            rs = ps.executeQuery();
            if(rs.next()){
                int akey = loginAkey;
                String aid = rs.getString("aid");
                String apwd = rs.getString("apwd");
                String aname = rs.getString("aname");
                String anum = rs.getString("anum");
                String abirth = rs.getString("abirth");
                String adate = rs.getString("adate");
                MyAccountDto myAccountDto = new MyAccountDto( akey , aid , apwd, aname, anum, abirth, adate);
                return myAccountDto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }   // myInfo() end

    // 7. 회원수정
    public boolean aUpdate(MyAccountDto myAccountDto){
        try{
            String sql = "update myaccount set anum = ? , apwd = ? where akey = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, myAccountDto.getAnum());
            ps.setString(2, myAccountDto.getApwd());
            ps.setInt(3,myAccountDto.getAkey());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // aUpdate() end

    // * 비밀번호 확인
    public boolean confirmPw(String confirmPwd , int loginAkey){
        try{
            String sql = "select * from myaccount where akey = ? and apwd = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,loginAkey);
            ps.setString(2,confirmPwd);
            rs =  ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // confirmPw() end

    // 8. 회원탈퇴
    public boolean aDelete(String confirmPwd , int loginAkey){
        try{
            String sql = "delete from myaccount where akey = ? and apwd = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,loginAkey);
            ps.setString(2,confirmPwd);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // aDelete() end

}   // class end
