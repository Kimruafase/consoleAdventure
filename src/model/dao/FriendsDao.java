package model.dao;

import model.dto.FreindsDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FriendsDao {
    private static FriendsDao friendsDao = new FriendsDao();
    // JDBC 인터페이스 3개
    Connection conn;        // 데이터베이스와의 연결을 관리하기 위한 변수
    PreparedStatement ps;   // SQL 쿼리를 실행하기 위해 사용되는 인터페이스
    ResultSet rs;
    // 생성자 연동 코드
    private FriendsDao(){
        try {   // 예기치 못한 상황으로 인해 종료되는 것을 방지하고 어떤 오류인지 파악하기 위해 try
            Class.forName("com.mysql.cj.jdbc.Driver");     // (JDBC 구현체) 드라이버 클래스 호출
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase" , "root" , "1234");
            //  DriverManager.getConnection(" DB SEVER URL" , "계정명" , "비밀번호")
        }catch (Exception e){   // 예외가 발생하면
            System.out.println(">> 연동 실패"+e);   // 발생한 이유 출력
        }   // try end
    }
    // 3. 해당 객체(싱글톤)를 외부로부터 접근할 수 있도록 간접 호출 메소드
    public static FriendsDao getInstance(){
        return friendsDao;
    }
    // 5. 친구 목록 출력
    public ArrayList<String > friendsPrint(int loginCno){
        ArrayList<String> list = new ArrayList<>();                                         // 접속한 캐릭터의 닉네임을 제외하고 출력 그리고 닉네임만 가져오면 되므로 ArrayList<String> 으로 생성
        try{
            String sql = "SELECT f.*, from_char.*, to_char.*\n" +                           // friends와 mycharacter 테이블을 조인하여 특정 조건에 맞는 데이터 가져오기
                    // friends f는 friends 테이블을 f라는 별칭으로 사용
                    // mycharacter from_char는 mycharacter 테이블을 from_char라는 별칭으로 사용
                    // mycharacter to_char는 mycharacter 테이블을 to_char라는 별칭으로 사용
                    "FROM friends f\n" +
                    "INNER JOIN mycharacter from_char ON f.fromckey = from_char.ckey\n" +
                    "INNER JOIN mycharacter to_char ON f.tockey = to_char.ckey\n" +
                    "WHERE (f.fromckey = '"+loginCno+"' OR f.tockey = '"+loginCno+"')\n" +  // friends 테이블에서 fromckey 또는 tockey가 loginCno 값과 같은 경우 선택
                    "AND f.state = '1'";                                                    // 그리고 friends 테이블에서 state 열이 '1'인 경우를 선택
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){

                int fromckey = rs.getInt(5);
                int tockey = rs.getInt(11);
                String name = "";
                if(fromckey == loginCno ){                                                  // 만약 fromckey가 loginCno가 같으면
                    name = rs.getString(12);                                    // tockey의 닉네임만 가져오기
                }else {                                                                     // 아니면 내가 tockey인 경우이니까
                    name = rs.getNString(6);                                    // fromckey의 닉네임 가져오기
                }
                list.add(name);
            }   // while end
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // friendsPrint() end

    // 6-1 친구 추가
    public boolean addFriends(String newFreinds , int loginCno){
        try{
            String sql = "insert into friends(fromckey, tockey) select ckey , (select ckey from mycharacter where cnickname = ?) " +
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
            String sql = "select * from friends inner join mycharacter on friends.fromckey = mycharacter.ckey where tockey = '"+loginCno+"' and state = '0'";
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
            String sql = "update friends set state = '1' where fromckey = '"+fromckey+"' ";
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

    // 6-4 친구삭제
    public boolean deleteFriends(String cnickname , int loginCno){
        try{
            String sql = "delete from friends where tockey = (select tockey from(select mycharacter.ckey from friends friends inner join mycharacter on friends.tockey = mycharacter.ckey where cnickname = ?)as a_t) and fromckey = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,cnickname);
            ps.setInt(2,loginCno);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }   // deleteFriends() end

   /* // 6-5 있는 친구 인지 유효성 검사
    public void checkFriends(){

    }*/
}
