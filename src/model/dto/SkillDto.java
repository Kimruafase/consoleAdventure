package model.dto;

public class SkillDto {
    //  멤버변수
    private int skkey;
    private String skname;
    private String skinfo;
    private int skdamage;
    private int skmoney;

    //  생성자
    public SkillDto(){
        this.skdamage = 20;
    }

    public SkillDto(int skkey, String skname, String skinfo, int skdamage, int skmoney) {
        this.skkey = skkey;
        this.skname = skname;
        this.skinfo = skinfo;
        this.skdamage = skdamage;
        this.skmoney = skmoney;
    }

    //  메소드(getter and setter, toString())
    public int getSkkey() {
        return skkey;
    }

    public void setSkkey(int skkey) {
        this.skkey = skkey;
    }

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

    public int getSkmoney() {return skmoney;}

    public void setSkmoney(int skmoney) {this.skmoney = skmoney;}

    @Override
    public String toString() {
        return "SkillDto{" +
                "skkey=" + skkey +
                ", skname='" + skname + '\'' +
                ", skinfo='" + skinfo + '\'' +
                ", skdamage=" + skdamage +
                ", skmoney=" + skmoney +
                '}';
    }
}
