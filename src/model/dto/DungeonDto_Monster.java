package model.dto;

public class DungeonDto_Monster {
    //  멤버변수
    private int mkey;
    private String mname;
    private int mhp;
    private int mdamage;

    //  생성자
    public DungeonDto_Monster(){}

    public DungeonDto_Monster(int mkey, String mname, int mhp, int mdamage) {
        this.mkey = mkey;
        this.mname = mname;
        this.mhp = mhp;
        this.mdamage = mdamage;
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

    public int getMhp() {
        return mhp;
    }

    public void setMhp(int mhp) {
        this.mhp = mhp;
    }


    public int getMdamage() {
        return mdamage;
    }

    public void setMdamage(int mdamage) {
        this.mdamage = mdamage;
    }


    @Override
    public String toString() {
        return "DungeonDto_Monster{" +
                "mkey=" + mkey +
                ", mname='" + mname + '\'' +
                ", mhp=" + mhp +
                ", mdamage=" + mdamage +
                '}';
    }
}
