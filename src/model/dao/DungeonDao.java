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

    private DungeonDao(){ // JAVA 와 DB 연결
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase","root","1234");
        }catch (Exception e){System.out.println(e);}
    } //DE


    // 스킬 사용 메소드
    public MySkillDto useSkill(int ckey, String skname){
        try{
            // 내 스킬과 스킬 데미지를 출력하는 sql 구문 입력
            String sql = "select *from Myskill inner join skill on Myskill.skkey = skill.skkey where ckey = ? and skname = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);
            ps.setString(2,skname);
            rs = ps.executeQuery();
            // myskillDto 에 DB에 있는 skdamage, skinfo, skname을 저장 후 반환
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
        // DungeonDto_Monster 타입의 ArrayList 선언
        ArrayList<DungeonDto_Monster> list = new ArrayList<>();
        try{
            // 몬스터 테이블 출력하는 구문 작성
            String sql = "select *from monster";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // rs.next 가 false 가 뜰 때 까지 ArrayList 에 mKey 를  add 함
            while (rs.next()){
                DungeonDto_Monster dungeonDtoMonster = new DungeonDto_Monster();
                dungeonDtoMonster.setMkey(rs.getInt("mkey"));
                list.add(dungeonDtoMonster);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        // ArrayList 반환
        return list;
    }

    //  몬스터 정보 표시 메소드
    public ArrayList<DungeonDto_Monster> monsterPrint(int random){
        // DungeonDto_Monster 타입의 ArrayList 선언
        ArrayList<DungeonDto_Monster> list = new ArrayList<>();
        try{
            // mKey 값에 따른 몬스터 테이블 출력하는 구문 작성
            String sql = "select *from monster where mkey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,random);
            rs = ps.executeQuery();
            // rs.next()가 1번 실행되므로 if 사용, DB 에 있는 mname 과 mkey, mimage를 ArrayList에 저장 받고 반환
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
        // 경험치 변수를 초기화
        int cExp = 0;
        try{
            // ckey 에 따른 내 캐릭터 테이블 출력하는 구문 작성
            String sql = "select *from mycharacter where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cKey);
            rs = ps.executeQuery();
            // rs.next()가 1번 실행되므로 if 사용, 경험치 변수에 DB에 있는 cexp 값을 저장 후 반환
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
            // ckey 값에 따른 내 테이블의 경험치 값을 수정할 구문을 작성
            String sql = "update mycharacter set cexp = cexp + ? where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cExp);
            ps.setInt(2,cKey);
            int count = ps.executeUpdate();
            // ps.executeUpdate()가 실행됐다면 count 에 1이 저장되는데
            // 1이 저장된 게 맞다면 true 를 반환 아니라면 false를 반환
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
