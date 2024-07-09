package model.dao;

import model.dto.MySkillDto;
import model.dto.SkillDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MenuDAO {

public static MenuDAO MDAO = new MenuDAO();

private MenuDAO(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testbase",
                    "root","1234"
            );
        }
        catch (Exception e){System.out.println(e);}
    }

Connection conn;
PreparedStatement ps;
ResultSet rs;

    //1. 캐릭터 정보 함수
    public ArrayList<model.dto.CharacterDto> charinfo(int ckey){
        ArrayList<model.dto.CharacterDto> list = new ArrayList<>();

        try{
            String sql = "select * from mycharacter where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);
            rs = ps.executeQuery();
            while(rs.next()){
                model.dto.CharacterDto characterDTO = new model.dto.CharacterDto();
                characterDTO.setCkey(rs.getInt("ckey"));
                characterDTO.setCnickname(rs.getString("cnickname"));
                characterDTO.setChp(rs.getInt("chp"));
                characterDTO.setCmoney(rs.getInt("cmoney"));
                list.add(characterDTO);
            }
        } catch (Exception e){System.out.println(e);} return list;
    }
    //2. 던전 메뉴 이동 함수
    public void godungeon(){

    }
    //3. 스킬정보 함수
    public ArrayList<MySkillDto> skillinfo(int ckey){
        ArrayList<MySkillDto> list = new ArrayList<>();

        try{
            String sql = "select * from myskill inner join skill on myskill.skkey = skill.skkey where myskill.ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);
            rs = ps.executeQuery();
            while(rs.next()){
                MySkillDto mySkillDto = new MySkillDto();
                mySkillDto.setSkname(rs.getString("skname"));
                mySkillDto.setSkinfo(rs.getString("skinfo"));
                mySkillDto.setSkdamage(rs.getInt("skdamage"));

                list.add(mySkillDto);
            }
        } catch (Exception e){System.out.println(e);} return list;
    }

    //4-1 스킬 목록 기능
    public ArrayList<SkillDto> showshopskill(){
        ArrayList<SkillDto> list = new ArrayList<>();
        try{
            String sql = "select * from skill";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                SkillDto skillDto = new SkillDto();
                skillDto.setSkkey(rs.getInt("skkey"));
                skillDto.setSkname(rs.getString("skname"));
                skillDto.setSkinfo(rs.getString("skinfo"));
                skillDto.setSkdamage(rs.getInt("skdamage"));

                list.add(skillDto);
            }
        }
        catch (Exception e){System.out.println(e);} return list;
    }

    //4-2 스킬 구입 기능
    public boolean buyskill(int ch,int ckey){
        try{
            String sql = "insert into Myskill(ckey, skkey) values (?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);
            ps.setInt(2,ch);
            int count = ps.executeUpdate();
            if (count == 1){return true;}
        }
        catch(Exception e){System.out.println(e);} return false;
    }

    //5
    public boolean getskill(int cmoney,int ckey){
        try{
            String sql = "update mycharacter set cmoney = ? where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cmoney);
            ps.setInt(2,ckey);
            int count = ps.executeUpdate();
            if (count == 1) {return true;}
        }
        catch (Exception e){System.out.println(e);} return false;
    }
}
