package cc.hiifong.androidwork.model;

public class Country {
    private String name;
    private int imgId;
    private String desc;

    public Country() {

    }

    public Country(String name,int imgId, String desc){
        super();
        this.name = name;
        this.imgId = imgId;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
