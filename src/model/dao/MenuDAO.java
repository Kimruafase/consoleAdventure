package model.dao;

import controller.MenuController;
import model.dto.CharacterDto;
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
    public ArrayList<model.dto.CharacterDto> charinfo(int ckey){ //1s
        ArrayList<model.dto.CharacterDto> list = new ArrayList<>(); //배열 list 생성

        try{
            String sql = "select * from mycharacter where ckey = ?"; //ckey 가 ?인 mycharacter 조회
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);
            rs = ps.executeQuery();
            while(rs.next()){
                model.dto.CharacterDto characterDTO = new model.dto.CharacterDto(); //charDTO객체 생성
                characterDTO.setCkey(rs.getInt("ckey")); //charDTO.set을 사용하여 ckey 저장
                characterDTO.setCnickname(rs.getString("cnickname")); //charDTO.set을 사용하여 cnickname 저장
                characterDTO.setChp(rs.getInt("chp")); //charDTO.set을 사용하여 chp 저장
                characterDTO.setCmoney(rs.getInt("cmoney")); //charDTO.set을 사용하여 cmoney 저장
                list.add(characterDTO); //list에 characterDTO 추가
            }
        } catch (Exception e){System.out.println(e);} return list;
    }//1e
    //2. 던전 메뉴 이동 함수
    public void godungeon(){ //2s

    } //2e
    //3. 스킬정보 함수
    public ArrayList<MySkillDto> skillinfo(int ckey){ //3s
        ArrayList<MySkillDto> list = new ArrayList<>(); //배열 list생성

        try{
            String sql = "select * from myskill inner join skill on myskill.skkey = skill.skkey where myskill.ckey = ?";   //inner join 을 이용하여 ckey 가 가지고있는 스킬 정보출력
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);
            rs = ps.executeQuery();
            while(rs.next()){
                MySkillDto mySkillDto = new MySkillDto(); //myskilldto 객체 생성
                mySkillDto.setSkname(rs.getString("skname")); //myskilldto에 set하여 skname 추가
                mySkillDto.setSkinfo(rs.getString("skinfo"));//myskilldto에 set하여 skinfo 추가
                mySkillDto.setSkdamage(rs.getInt("skdamage")); //myskilldto에 set하여 skdamage 추가

                list.add(mySkillDto); //리스트에 myskilldto 추가
            }
        } catch (Exception e){System.out.println(e);} return list;
    } //3e

    //4-1 스킬 목록 기능
    public ArrayList<SkillDto> showshopskill(){
        ArrayList<SkillDto> list = new ArrayList<>(); //list배열 생성
        try{
            String sql = "select * from skill"; //skill 전체 조회
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                SkillDto skillDto = new SkillDto();
                skillDto.setSkkey(rs.getInt("skkey")); //skillDTO에 set하여 skkey 저장
                skillDto.setSkname(rs.getString("skname")); //skillDTO에 set하여 skname저장
                skillDto.setSkinfo(rs.getString("skinfo")); //skillDTO에 set하여 skinfo저장
                skillDto.setSkdamage(rs.getInt("skdamage")); //skillDTo에 set하여 skdamage저장

                list.add(skillDto); //리스트에 skilldto 추가
            }
        }
        catch (Exception e){System.out.println(e);} return list;
    }

    //4-2 스킬 구입(추가) 기능
    public boolean buyskill(int ch,int ckey){
        try{
            String sql = "insert into Myskill(ckey, skkey) values (?,?)"; //내스킬 추가 ckey 와 skkey 받아와서 내스킬 추가
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ckey);  //ckey 호출
            ps.setInt(2,ch);// 입력한 스킬 번호 호출
            int count = ps.executeUpdate();
            if (count == 1){return true;}
        }
        catch(Exception e){System.out.println(e);} return false;
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

    //7 금액 증가
    public boolean plusMoney(int pm, int ckey){ //던
        try{
            String sql = "update mycharacter set cmoney = cmoney + ? where ckey = ?"; //내 캐릭터의 금액 추가 문
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pm); //던전 클리어시 들어올 금액
            ps.setInt(2,ckey); // 캐릭터 식별번호
            int count = ps.executeUpdate();
            if(count == 1){return true;}
        }
        catch (Exception e) {System.out.println(e);} return false;
    }

    //8 캐릭터 금액 상태 업데이트
    public  boolean upMoney(int mone, int ckey){
        try{
            String sql = "update mycharacter set cmoney = ? where ckey = ?"; //내 캐릭터의 금액 업데이트
            ps = conn.prepareStatement(sql);
            ps.setInt(1,mone); //업데이트된 금액 호출
            ps.setInt(2, ckey); //내 캐릭터번호 호출
            int count = ps.executeUpdate();
            if(count == 1){return true;}

        } catch (Exception e){System.out.println(e);} return  false;
    }

    //8-1 캐릭터 금액 호출
    public int minMoney(int ckey){
        int mmm = 0;    //지역변수 생성
        try{
            String sql = "select * from mycharacter where ckey = ?"; //내 캐릭터의 ckey가 ? 인것 전체 호출
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ckey); //내가 접속하고있는 캐릭터의 식별키 호출
            rs = ps.executeQuery();
            if (rs.next()){ //호출 성공시
                mmm = rs.getInt("cmoney"); //캐릭터의 현재금액 호출
            }

        }
        catch (Exception e){System.out.println(e);}  return mmm;
    }

    //8-2 스킬 금액 호출
    public int minskillmoney(int ch){
        int mmm = 0;//지역 변수 생성
        try{
            String sql = "select * from skill where skkey = ?"; //스킬 테이블의 skkey 스킬식별 키 호출
            ps = conn.prepareStatement(sql);
            ps.setInt(1, ch); //내가 입력한 번호 호출
            rs = ps.executeQuery();
            if (rs.next()){ //호출 성공시
                mmm = rs.getInt("skmoney"); //스킬테이블의 해당 skmoney 필드 호출
            }

        }
        catch (Exception e){System.out.println(e);}  return mmm;
    }
}
