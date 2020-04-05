package com.args.cineduniya;

public class Upload {

    private String mName;
    private String mImageUrl;
    private int mRate;

    // for the image adapter to display the images from firebase


    public Upload(){

        //empty
    }
    public Upload(String name, String imageUrl,int rate){

        if(name.trim().equals("")){
            name ="No Name";
        }

        mName = name;
        mImageUrl= imageUrl;
        mRate = rate;
    }

    public String getmName(){
        return mName;
    }

    public void setmName(String name){
        mName = name;
    }

    public  String getmImageUrl(){
        return mImageUrl;
    }
    public void setmImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }

    public int getmRate() {
        return mRate;
    }

    public void setmRate(int rate) {
        this.mRate = rate;
    }
}
