package model.dao;

import controller.MenuController;
import model.dto.FreindsDto;
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

    // 5. 친구 목록 출력
    public ArrayList<FreindsDto> friendsPrint(int loginCno){
        ArrayList<FreindsDto> list = new ArrayList<>();
        try{
            String sql = "select * from freinds inner join mycharacter on freinds.tockey = mycharacter.ckey where fromckey = '"+loginCno+"' and state = '1'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int tockey = rs.getInt("ckey");
                String  tocnickname = rs.getString("cnickname");
                FreindsDto freindsDto = new FreindsDto();
                freindsDto.setTockey(tockey);
                freindsDto.setTocnickname(tocnickname);
                list.add(freindsDto);
            }   // while end
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // friendsPrint() end

    // 6-1 친구 추가
    public boolean addFriends(String newFreinds , int loginCno){
        try{
            String sql = "insert into freinds(fromckey, tockey) select ckey , (select ckey from mycharacter where cnickname = ?) " +
                    "from mycharacter where ckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1 , newFreinds);
            ps.setInt(2,loginCno);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // addFriends() end

    // 6-2 받은 친구 요청
    public ArrayList<FreindsDto> receivedFriends(int loginCno){
        ArrayList<FreindsDto> list = new ArrayList<>();
        try{
            String sql = "select * from freinds inner join mycharacter on freinds.fromckey = mycharacter.ckey where tockey = '"+loginCno+"' and state = '0'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int fromckey = rs.getInt("ckey");
                String  tocnickname = rs.getString("cnickname");
                FreindsDto freindsDto = new FreindsDto();
                freindsDto.setFromckey(fromckey);
                freindsDto.setTocnickname(tocnickname);
                list.add(freindsDto);
            }   // while end
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // receivedFriends() end

    // 6-3 친구요청 수락
    public boolean acceptRequest(int fromckey){
        try{
            String sql = "update freinds set state = '1' where fromckey = '"+fromckey+"' ";
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // acceptRequest() end
}
