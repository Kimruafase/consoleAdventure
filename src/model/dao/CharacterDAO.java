package model.dao;

import controller.CharacterController;
import model.dto.CharacterDto;
import model.dto.FreindsDto;

import javax.annotation.processing.Generated;
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
    public int createChar(model.dto.CharacterDto characterDTO){ //ccs
        try {
            String sql = "insert into mycharacter(cnickname,akey) values (?,?)";
            ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS ); // 기재된 SQL에서 생성된 키를 리턴 하겠다는 속성
            ps.setString(1, characterDTO.getCnickname());
            ps.setInt(2, characterDTO.getAkey());
            int count = ps.executeUpdate();
            if (count == 1){
                rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt( 1 );
            }

        } catch (Exception e) {System.out.println(e);} return 0;
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

    //4. 캐릭터 기본공격 추가 함수
    public boolean charattack(CharacterDto characterDto){
        try{
            String sql = "insert into Myskill( ckey, skkey) values (?,1)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,characterDto.getCkey());
            int count = ps.executeUpdate();
            if (count == 1 )return true;
        }
        catch (Exception e){System.out.println(e);} return false;
    }

} //ce
