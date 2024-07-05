package model.dao;

import model.dto.CharacterDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminGameDao {
    private static AdminGameDao adminGameDao = new AdminGameDao();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    private AdminGameDao(){
        try {   // 예기치 못한 상황으로 인해 종료되는 것을 방지하고 어떤 오류인지 파악하기 위해 try
            Class.forName("com.mysql.cj.jdbc.Driver");     // (JDBC 구현체) 드라이버 클래스 호출
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbase" , "root" , "1234");
            //  DriverManager.getConnection(" DB SEVER URL" , "계정명" , "비밀번호")
        }catch (Exception e){   // 예외가 발생하면
            System.out.println(">> 연동 실패"+e);   // 발생한 이유 출력
        }   // try end
    }
    public static AdminGameDao getInstance(){
        return adminGameDao;
    }

    // 캐릭터 전체 출력
    public ArrayList<CharacterDto> characterAllPrint(){
        ArrayList<CharacterDto> list = new ArrayList<>();
        try{
            String sql = "select * from mycharacter innerjoin myaccount on mycharacter.akey = mycharacter.akey ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int ckey = rs.getInt("ckey");
                String cnickname = rs.getString("cnickname");
                String aid = rs.getString("aid");
                CharacterDto characterDto = new CharacterDto();
                characterDto.setCkey(ckey);
                characterDto.setCnickname(cnickname);
                characterDto.setAid(rs.getString("aid"));

                list.add(characterDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }   // characterAllPrint() end
}
