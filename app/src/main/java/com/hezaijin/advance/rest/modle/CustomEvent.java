package com.hezaijin.advance.rest.modle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomEvent {

    @SerializedName("day")
    @Expose
    public Integer day;
    @SerializedName("des")
    @Expose
    public String des;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("lunar")
    @Expose
    public String lunar;
    @SerializedName("month")
    @Expose
    public Integer month;
    @SerializedName("pic")
    @Expose
    public String pic;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("year")
    @Expose
    public Integer year;

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

    @Override
    public String toString() {
        return "CustomEvent{" +
                "day=" + day +
                ", des='" + des + '\'' +
                ", id=" + id +
                ", lunar='" + lunar + '\'' +
                ", month=" + month +
                ", pic='" + pic + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}