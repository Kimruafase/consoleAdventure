package model.dto;

public class CharacterDto {
    //이동객체 DTO생성
    //1. 멤버변수
    private int ckey;
    private String nickname;
    private int chp;
    private int akey;

    //2. 생성자

    //기본생성자
    public CharacterDto(){}
    //전체 생성자

    public CharacterDto(int ckey, String nickname, int chp, int akey) {
        this.ckey = ckey;
        this.nickname = nickname;
        this.chp = chp;
        this.akey = akey;
    }


    //필요 생성자

    public CharacterDto(String nickname) {
        this.nickname = nickname;
    }

    //3. 메소드

    //3-1 getter & setter
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getChp() {
        return chp;
    }

    public void setChp(int chp) {
        this.chp = chp;
    }

    public int getCkey() {
        return ckey;
    }

    public void setCkey(int ckey) {
        this.ckey = ckey;
    }

    public int getAkey() {
        return akey;
    }

    public void setAkey(int akey) {
        this.akey = akey;
    }

    //3-2 toString


    @Override
    public String toString() {
        return "CharacterDto{" +
                "ckey=" + ckey +
                ", nickname='" + nickname + '\'' +
                ", chp=" + chp +
                ", akey=" + akey +
                '}';
    }
}
