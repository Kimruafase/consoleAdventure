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

    CharacterDAO(){ //DS //DB연결
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase","root","1234");
        }catch (Exception e){System.out.println(e);}
    } //DE

    //1. 캐릭터생성함수
    public int createChar(model.dto.CharacterDto characterDTO){ //ccs
        try { //ts
            String sql = "insert into mycharacter(cnickname,akey) values (?,?)";
            ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS ); // 기재된 SQL에서 생성된 키를 리턴 하겠다는 속성
            ps.setString(1, characterDTO.getCnickname());   //chardto에 저장된 nickname 호출
            ps.setInt(2, characterDTO.getAkey());   //chardto에 저장된 akey호출
            int count = ps.executeUpdate();
            if (count == 1){
                rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt( 1 );
            } //te

        } catch (Exception e) {System.out.println(e);} return 0;
    } //cce

    //2. 캐릭터 접속함수
    public int joinGame(model.dto.CharacterDto characterDTO){
        try{ //ts
            String sql = "select * from mycharacter where cnickname = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,characterDTO.getCnickname()); //charDTO에 저장된 nickname이 동일한지 유효성검사
            rs = ps.executeQuery();

            if (rs.next()){return rs.getInt("ckey");}   //rs에 값이 존재하면 ckey를 반환
        }//te
        catch (Exception e){System.out.println(e);} return 0;
    }

    //3. 캐릭터삭제함수
    public boolean delChar(model.dto.CharacterDto characterDTO){
        //로그인 정보번호 받아와서 저장
        try{
            String sql = "delete from mycharacter where akey = ? and cnickname = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,characterDTO.getAkey());    //charDTO에 저장된 akey 호출
            ps.setString(2,characterDTO.getCnickname());    //charDTO에 저장된 nickname호출
            int count = ps.executeUpdate();
            if (count == 1)return true; //count에 값이 1이면 ture값 반환하여 삭제 성공
        }catch (Exception e){System.out.println(e);} return false;
    }

    //4. 캐릭터 생성시 기본공격 추가 함수
    public boolean charattack(CharacterDto characterDto){
        try{
            String sql = "insert into Myskill( ckey, skkey) values (?,1)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,characterDto.getCkey()); //charDTO에 있는 캐릭터의 식별키 호출하여 기본공격 대입
            int count = ps.executeUpdate();
            if (count == 1 )return true; //대입 성공시 //count가 1이면 return true값 반환
        }
        catch (Exception e){System.out.println(e);} return false;
    }

} //ce
