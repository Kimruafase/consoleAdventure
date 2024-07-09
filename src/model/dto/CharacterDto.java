package model.dto;

import controller.CharacterController;

public class CharacterDto {
    //멤버변수
    private  int ckey;
    private String cnickname;
    private int chp;
    private int akey;
    private SkillDto SkillDto;
    private int cHpChange;
    private String aid;
    private int cExp;
    private int cLevel;
    private int cmoney;


    //생성자
    public CharacterDto(){
        this.chp = 100;
        this.cHpChange = 50;
        this.ckey = CharacterController.cController.loginCno;
        this.cLevel = 1;
        this.cExp = 20;
    }

    public CharacterDto(int ckey, String cnickname, int chp, int akey, model.dto.SkillDto skillDto, int cHpChange, String aid, int cExp, int cLevel, int cmoney) {
        this.ckey = ckey;
        this.cnickname = cnickname;
        this.chp = chp;
        this.akey = akey;
        SkillDto = skillDto;
        this.cHpChange = cHpChange;
        this.aid = aid;
        this.cExp = cExp;
        this.cLevel = cLevel;
        this.cmoney = cmoney;
    }

    public CharacterDto(String cnickname){
        this.cnickname = cnickname;
    }

    //메소드
    //toString

    @Override
    public String toString() {
        return "CharacterDto{" +
                "ckey=" + ckey +
                ", cnickname='" + cnickname + '\'' +
                ", chp=" + chp +
                ", akey=" + akey +
                ", SkillDto=" + SkillDto +
                ", cHpChange=" + cHpChange +
                ", aid='" + aid + '\'' +
                ", cExp=" + cExp +
                ", cLevel=" + cLevel +
                ", cmoney=" + cmoney +
                '}';
    }


    //getter setter

    public int getcHpChange() {
        return cHpChange;
    }

    public void setcHpChange(int cHpChange) {
        this.cHpChange = cHpChange;
    }

    public model.dto.SkillDto getSkillDto() {
        return SkillDto;
    }

    public void setSkillDto(model.dto.SkillDto skillDto) {
        SkillDto = skillDto;
    }

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

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public int getcExp() {
        return cExp;
    }

    public void setcExp(int cExp) {
        this.cExp = cExp;
    }

    public int getcLevel() {
        return cLevel;
    }

    public void setcLevel(int cLevel) {
        this.cLevel = cLevel;
    }

    public int getCmoney() {
        return cmoney;
    }

    public void setCmoney(int cmoney) {
        this.cmoney = cmoney;
    }
}
