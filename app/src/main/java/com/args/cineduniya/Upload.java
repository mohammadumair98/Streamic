package com.args.cineduniya;

public class Upload {

    private String mName;//movie code
    private String mImageUrl;
    private String mposterurl;
    private String movie_name;
    private String description;
    private String trailer;
    private Float rating;
    private Float release_year;
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

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMposterurl() {
        return mposterurl;
    }

    public void setMposterurl(String mposterurl) {
        this.mposterurl = mposterurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Float release_year) {
        this.release_year = release_year;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
