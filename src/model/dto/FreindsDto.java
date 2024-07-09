package model.dto;

public class FreindsDto {
    private int fkey;
    private int fromckey;
    private int tockey;
    private String tocnickname;

    public FreindsDto(){}

    public FreindsDto(int fkey, int fromckey, int tockey) {
        this.fkey = fkey;
        this.fromckey = fromckey;
        this.tockey = tockey;
    }

    public int getFkey() {
        return fkey;
    }

    public void setFkey(int fkey) {
        this.fkey = fkey;
    }

    public int getFromckey() {
        return fromckey;
    }

    public void setFromckey(int fromckey) {
        this.fromckey = fromckey;
    }

    public int getTockey() {
        return tockey;
    }

    public void setTockey(int tockey) {
        this.tockey = tockey;
    }

    public String getTocnickname() {
        return tocnickname;
    }

    public void setTocnickname(String tocnickname) {
        this.tocnickname = tocnickname;
    }
}
