package com.haozhang.retrofit.info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataInfo {

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("des")
    @Expose
    private String des;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lunar")
    @Expose
    private String lunar;
    @SerializedName("month")
    @Expose
    private Integer month;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("year")
    @Expose
    private Integer year;

    /**
     *
     * @return
     * The day
     */
    public Integer getDay() {
        return day;
    }

    /**
     *
     * @param day
     * The day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     *
     * @return
     * The des
     */
    public String getDes() {
        return des;
    }

    /**
     *
     * @param des
     * The des
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The lunar
     */
    public String getLunar() {
        return lunar;
    }

    /**
     *
     * @param lunar
     * The lunar
     */
    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    /**
     *
     * @return
     * The month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     *
     * @param month
     * The month
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     *
     * @return
     * The pic
     */
    public String getPic() {
        return pic;
    }

    /**
     *
     * @param pic
     * The pic
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The year
     */
    public Integer getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

}