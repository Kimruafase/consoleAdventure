package model.dto;

public class CharacterDto {
    //멤버변수
    private  int ckey;
    private String cnickname;
    private int chp = 100;
    private int akey;
    SkillDto SkillDto;
    //생성자
    public CharacterDto(){

    }

    public CharacterDto(int ckey, String cnickname, int chp, int akey) {
        this.ckey = ckey;
        this.cnickname = cnickname;
        this.chp = chp;
        this.akey = akey;
    }

    public CharacterDto(String cnickname){
        this.cnickname = cnickname;
    }

    //메소드
    //toString

    @Override
    public String toString() {
        return "CharacterDTO{" +
                "ckey=" + ckey +
                ", cnickname='" + cnickname + '\'' +
                ", chp=" + chp +
                ", akey=" + akey +
                '}';
    }

    //getter setter

    public int getCkey() {
        return ckey;
    }

    public void setCkey(int ckey) {
        this.ckey = ckey;
    }

    public String getCnickname() {
        return cnickname;
    }

    public void setCnickname(String cnickname) {
        this.cnickname = cnickname;
    }

    public int getChp() {
        return chp;
    }

    public void setChp(int chp) {
        this.chp = chp;
    }

    public int getAkey() {
        return akey;
    }

    public void setAkey(int akey) {
        this.akey = akey;
    }
}
