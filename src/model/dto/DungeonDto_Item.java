package model.dto;

public class DungeonDto_Item {
    int ikey;
    String iname;
    String iinfo;

    public DungeonDto_Item(){}

    public DungeonDto_Item(int ikey, String iname, String iinfo) {
        this.ikey = ikey;
        this.iname = iname;
        this.iinfo = iinfo;
    }

    public int getIkey() {
        return ikey;
    }

    public void setIkey(int ikey) {
        this.ikey = ikey;
    }

    public String getIname() {
        return iname;
    }

    public void setIname(String iname) {
        this.iname = iname;
    }

    public String getIinfo() {
        return iinfo;
    }

    public void setIinfo(String iinfo) {
        this.iinfo = iinfo;
    }

    @Override
    public String toString() {
        return "DungeonDto_Item{" +
                "ikey=" + ikey +
                ", iname='" + iname + '\'' +
                ", iinfo='" + iinfo + '\'' +
                '}';
    }
}
