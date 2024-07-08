package model.dto;

public class DungeonDto_Dungeon {
    private int dkey;
    private String dname;
    // private int ikey;
    private int dungeonState;
    private int dungeonStateChange;
    private int dungeonDiff;

    public DungeonDto_Dungeon(){
        this.dungeonState = 0;
        this.dungeonStateChange = 20;
    }

    public DungeonDto_Dungeon(int dkey, String dname/*, int ikey*/) {
        this.dkey = dkey;
        this.dname = dname;
        // this.ikey = ikey;
    }

    public int getDungeonState() {
        return dungeonState;
    }

    public void setDungeonState(int dungeonState) {
        this.dungeonState = dungeonState;
    }

    public int getDungeonStateChange() {
        return dungeonStateChange;
    }

    public void setDungeonStateChange(int dungeonStateChange) {
        this.dungeonStateChange = dungeonStateChange;
    }

    public int getDungeonDiff() {
        return dungeonDiff;
    }

    public void setDungeonDiff(int dungeonDiff) {
        this.dungeonDiff = dungeonDiff;
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

    // public int getIkey() {
   //      return ikey;
    // }

    // public void setIkey(int ikey) {
   //      this.ikey = ikey;
    // }

    @Override
    public String toString() {
        return "DungeonDto_Dungeon{" +
                "dkey=" + dkey +
                ", dname='" + dname + '\'' +
                ", dungeonState=" + dungeonState +
                ", dungeonStateChange=" + dungeonStateChange +
                ", dungeonDiff=" + dungeonDiff +
                '}';
    }
}
