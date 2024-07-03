package model.dto;

public class DungeonDto_Dungeon {
    int dkey;
    String dname;
    int ikey;

    public DungeonDto_Dungeon(){}

    public DungeonDto_Dungeon(int dkey, String dname, int ikey) {
        this.dkey = dkey;
        this.dname = dname;
        this.ikey = ikey;
    }

    public int getDkey() {
        return dkey;
    }

    public void setDkey(int dkey) {
        this.dkey = dkey;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getIkey() {
        return ikey;
    }

    public void setIkey(int ikey) {
        this.ikey = ikey;
    }

    @Override
    public String toString() {
        return "DungeonDto_Dungeon{" +
                "dkey=" + dkey +
                ", dname='" + dname + '\'' +
                ", ikey=" + ikey +
                '}';
    }
}
