package model.dto;

public class MySkillDto {
    // 멤버변수
    private int mskey;
    private int ckey;
    private int skkey;
    private String skname;
    private String skinfo;
    private int skdamage;

    public String getSkname() {
        return skname;
    }

    public void setSkname(String skname) {
        this.skname = skname;
    }

    public String getSkinfo() {
        return skinfo;
    }

    public void setSkinfo(String skinfo) {
        this.skinfo = skinfo;
    }

    public int getSkdamage() {
        return skdamage;
    }

    public void setSkdamage(int skdamage) {
        this.skdamage = skdamage;
    }

    //  생성자
    public MySkillDto(){}

    public MySkillDto(int mskey, int ckey, int skkey) {
        this.mskey = mskey;
        this.ckey = ckey;
        this.skkey = skkey;
    }

    //  메소드(getter and setter, toString())

    public int getMskey() {
        return mskey;
    }

    public void setMskey(int mskey) {
        this.mskey = mskey;
    }

    public int getCkey() {
        return ckey;
    }

    public void setCkey(int ckey) {
        this.ckey = ckey;
    }

    public int getSkkey() {
        return skkey;
    }

    public void setSkkey(int skkey) {
        this.skkey = skkey;
    }

    @Override
    public String toString() {
        return "MySkillDto{" +
                "mskey=" + mskey +
                ", ckey=" + ckey +
                ", skkey=" + skkey +
                '}';
    }
}
