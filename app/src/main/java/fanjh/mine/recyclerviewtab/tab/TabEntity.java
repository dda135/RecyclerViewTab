package fanjh.mine.recyclerviewtab.tab;

/**
 * Created by faker on 2017/8/29.
 */

public class TabEntity {
    private String text;
    private int normalResID;
    private int selectResID;

    public TabEntity(String text, int normalResID, int selectResID) {
        this.text = text;
        this.normalResID = normalResID;
        this.selectResID = selectResID;
    }

    public String getText() {
        return null == text ? "" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNormalResID() {
        return normalResID;
    }

    public void setNormalResID(int normalResID) {
        this.normalResID = normalResID;
    }

    public int getSelectResID() {
        return selectResID;
    }

    public void setSelectResID(int selectResID) {
        this.selectResID = selectResID;
    }
}
