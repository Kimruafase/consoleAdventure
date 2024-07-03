package model.dto;

public class MyAccountDto {
    // 1. 멤버변수
    private int akey;
    private String aid;
    private String apwd;
    private String aname;
    private String anum;
    private String abirth;
    private String adate;

    // 2. 생성자
    public MyAccountDto(){}

    public MyAccountDto(int akey, String aid, String apwd, String aname, String anum, String abirth, String adate) {
        this.akey = akey;
        this.aid = aid;
        this.apwd = apwd;
        this.aname = aname;
        this.anum = anum;
        this.abirth = abirth;
        this.adate = adate;
    }

    // 회원가입용

    public MyAccountDto(String aid, String apwd, String aname, String anum, String abirth) {
        this.aid = aid;
        this.apwd = apwd;
        this.aname = aname;
        this.anum = anum;
        this.abirth = abirth;
    }

    // 3. 메소드
    public int getAkey() {
        return akey;
    }

    public void setAkey(int akey) {
        this.akey = akey;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getApwd() {
        return apwd;
    }

    public void setApwd(String apwd) {
        this.apwd = apwd;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAnum() {
        return anum;
    }

    public void setAnum(String anum) {
        this.anum = anum;
    }

    public String getAbirth() {
        return abirth;
    }

    public void setAbirth(String abirth) {
        this.abirth = abirth;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }

    @Override
    public String toString() {
        return "MyAccountDto{" +
                "akey=" + akey +
                ", aid='" + aid + '\'' +
                ", apwd='" + apwd + '\'' +
                ", aname='" + aname + '\'' +
                ", anum='" + anum + '\'' +
                ", abirth='" + abirth + '\'' +
                ", adate='" + adate + '\'' +
                '}';
    }
}
