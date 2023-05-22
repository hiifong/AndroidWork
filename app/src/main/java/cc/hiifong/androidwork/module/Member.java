package cc.hiifong.androidwork.module;

public class Member {
    String name;
    int imgId;
    public Member(String name, int imgId){
        this.name = name;
        this.imgId = imgId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public int getImgId(){
        return imgId;
    }
    public void setImgId(int imgId){
        this.imgId = imgId;
    }
}
