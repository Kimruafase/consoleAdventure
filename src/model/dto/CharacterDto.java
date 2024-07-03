package 프로젝트2.Model.Dto;

public class CharacterDTO {
    //멤버변수
    private  int ckey;
    private String cnickname;
    private int chp;
    private int akey;
    //생성자
    public CharacterDTO(){}

    public CharacterDTO(int ckey, String cnickname, int chp, int akey) {
        this.ckey = ckey;
        this.cnickname = cnickname;
        this.chp = chp;
        this.akey = akey;
    }

    public CharacterDTO (String cnickname){
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
