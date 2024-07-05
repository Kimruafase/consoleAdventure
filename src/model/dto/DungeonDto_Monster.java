package model.dto;

public class DungeonDto_Monster {
    //  멤버변수
    private int mkey;
    private String mname;
    private int mHp;
    private int mDamage;

    //  생성자
    public DungeonDto_Monster(){
        this.mHp = 100;
        this.mDamage = 10;
    }

    public DungeonDto_Monster(int mkey, String mname, int mHp, int mDamage) {
        this.mkey = mkey;
        this.mname = mname;
        this.mHp = mHp;
        this.mDamage = mDamage;
    }

    public int getmDamage() {
        return mDamage;
    }

    public void setmDamage(int mDamage) {
        this.mDamage = mDamage;
    }

    public int getmHp() {
        return mHp;
    }

    public void setmHp(int mHp) {
        this.mHp = mHp;
    }

    public int getMkey() {
        return mkey;
    }

    public void setMkey(int mkey) {
        this.mkey = mkey;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "DungeonDto_Monster{" +
                "mkey=" + mkey +
                ", mname='" + mname + '\'' +
                ", mHp=" + mHp +
                ", mDamage=" + mDamage +
                '}';
    }
}
