package 프로젝트2.Model.Dao;

import DAY16.model.dto.BoardDTO;
import 프로젝트2.Model.Dto.CharacterDTO;

import java.sql.*;
import java.util.ArrayList;

public class CharacterDAO { //cs

    public static CharacterDAO characterDAO = new CharacterDAO();

    //DB연결용
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    CharacterDAO(){ //DS
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase","root","1234");
        }catch (Exception e){System.out.println(e);}
    } //DE

    //1. 캐릭터생성함수
    public boolean createChar(CharacterDTO characterDTO){ //ccs
        try {
            String sql = "insert into mycharacter(cnickname) values (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, characterDTO.getCnickname());
            int count = ps.executeUpdate();
            if (count == 1){return true;}
        } catch (Exception e) {System.out.println(e);} return false;
    } //cce

    //2. 캐릭터 접속함수
    public int joinGame(CharacterDTO characterDTO){
        try{
            String sql = "select * from mycharacter where cnickname = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,characterDTO.getCnickname());
            rs = ps.executeQuery();

            if (rs.next()){return rs.getInt("ckey");}
        }catch (Exception e){System.out.println(e);} return 0;
    }

    //3. 캐릭터삭제함수
    public boolean delChar(int bno){
        //로그인 정보번호 받아와서 저장
        try{
            String sql = "delete from mycharacter where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);
            int count = ps.executeUpdate();
            if (count == 1)return true;
        }catch (Exception e){System.out.println(e);} return false;
    }

    //4. 캐릭터 목록 함수
    public void showChar(){}
    //매개변수 X 리턴값 계정키번호와 맞는 캐릭터 닉네임 출력
} //ce
