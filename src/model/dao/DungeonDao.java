package model.dao;

import model.dto.DungeonDto_Monster;
import model.dto.MySkillDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
    //  8-1. 몬스터 테이블 먼저 가져오는 함수
    public ArrayList<DungeonDto_Monster> monsterTable(){
        ArrayList<DungeonDto_Monster> list = new ArrayList<>();
        try{
            String sql = "select *from monster";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                DungeonDto_Monster dungeonDtoMonster = new DungeonDto_Monster();
                dungeonDtoMonster.setMkey(rs.getInt("mkey"));
                list.add(dungeonDtoMonster);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    //  몬스터 정보 표시 메소드
    public ArrayList<DungeonDto_Monster> monsterPrint(int random){
        ArrayList<DungeonDto_Monster> list = new ArrayList<>();
        try{
            String sql = "select *from monster where mkey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,random);
            rs = ps.executeQuery();

            if(rs.next()){
                DungeonDto_Monster dungeonDtoMonster = new DungeonDto_Monster();
                dungeonDtoMonster.setMname(rs.getString("mname"));
                dungeonDtoMonster.setMkey(rs.getInt("mkey"));
                dungeonDtoMonster.setMimage(rs.getString("mimage"));
                list.add(dungeonDtoMonster);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    //  9. 레벨 및 경험치 함수
    public int characterLevelAndExp(int cKey){
        int cExp = 0;
        try{
            String sql = "select *from mycharacter where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cKey);
            rs = ps.executeQuery();
            if(rs.next()){
                cExp = rs.getInt("cexp");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return cExp;
    }

    //  10. 던전 클리어 후 경험치 추가 함수
    public boolean getDungeonClearExp(int cExp, int cKey){
        try{
            String sql = "update mycharacter set cexp = cexp + ? where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cExp);
            ps.setInt(2,cKey);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //  11. 레벨업 함수
//    public boolean levelUp(int cKey){
//        try{
//            String sql = "update mycharacter set cexp = cexp - 100 where ckey = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,cKey);
//            int count = ps.executeUpdate();
//            if(count == 1){
//                return true;
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return false;
//    }
}
