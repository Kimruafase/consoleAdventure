package model.dao;

import model.dto.MySkillDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DungeonDao {
        //  싱글톤
        public static DungeonDao dungeonDao = new DungeonDao();


        // getInstance() 메소드
        public static DungeonDao getInstance(){
            return dungeonDao;
        }

    //DB연결용
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    private DungeonDao(){ //DS
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase","root","1234");
        }catch (Exception e){System.out.println(e);}
    } //DE


    // 스킬 사용 메소드
    public MySkillDto useSkill(int ckey, String skname){
        try{
            String sql = "select *from Myskill inner join skill on Myskill.skkey = skill.skkey where ckey = ? and skname = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);
            ps.setString(2,skname);
            rs = ps.executeQuery();

            if(rs.next()){
                MySkillDto mySkillDto = new MySkillDto();
                mySkillDto.setSkdamage(rs.getInt("skdamage"));
                mySkillDto.setSkinfo(rs.getString("skinfo"));
                mySkillDto.setSkname(rs.getString("skname"));
                return mySkillDto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
