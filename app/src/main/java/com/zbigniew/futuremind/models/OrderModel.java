package com.zbigniew.futuremind.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.zbigniew.futuremind.StaticValues;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;

/**
 * Created by ossek on 2016-10-16.
 */

public class OrderModel extends SugarRecord {

    @SerializedName("orderId")
    int orderId;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("modificationDate")
    private Date modificationDate;

    public OrderModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        Matcher matcher = StaticValues.URL_PATTERN.matcher(description);
        while (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();
            // now you have the offsets of a URL match
            return description.substring(0, matchStart);
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL(){
        Matcher matcher = StaticValues.URL_PATTERN.matcher(description);
        while (matcher.find()) {
            int matchStart = matcher.start(1);
            int matchEnd = matcher.end();
            // now you have the offsets of a URL match
            return description.substring(matchStart, matchEnd);
        }
        return "";
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public String getDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return formatter.format(modificationDate);
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
