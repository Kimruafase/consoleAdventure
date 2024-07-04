package model.dao;

import java.sql.*;

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
    public boolean createChar(model.dto.CharacterDto characterDTO){ //ccs
        try {
            String sql = "insert into mycharacter(cnickname,akey) values (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, characterDTO.getCnickname());
            ps.setInt(2, characterDTO.getAkey());
            int count = ps.executeUpdate();
            if (count == 1){return true;}
        } catch (Exception e) {System.out.println(e);} return false;
    } //cce

    //2. 캐릭터 접속함수
    public int joinGame(model.dto.CharacterDto characterDTO){
        try{
            String sql = "select * from mycharacter where cnickname = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,characterDTO.getCnickname());
            rs = ps.executeQuery();

            if (rs.next()){return rs.getInt("ckey");}
        }catch (Exception e){System.out.println(e);} return 0;
    }

    //3. 캐릭터삭제함수
    public boolean delChar(model.dto.CharacterDto characterDTO){
        //로그인 정보번호 받아와서 저장
        try{
            String sql = "delete from mycharacter where akey = ? and cnickname = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,characterDTO.getAkey());
            ps.setString(2,characterDTO.getCnickname());
            int count = ps.executeUpdate();
            if (count == 1)return true;
        }catch (Exception e){System.out.println(e);} return false;
    }

} //ce
