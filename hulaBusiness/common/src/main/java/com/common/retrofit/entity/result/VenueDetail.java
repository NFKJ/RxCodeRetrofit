package com.common.retrofit.entity.result;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/29
 */
public class VenueDetail
{

    /**
     * venueId : 08d3b503-8391-4a9c-9cfc-db685779da75
     * venueName : 黄龙体育中心室外网球
     * venueUrl : https://pic.hulasports.com//hlcg/1481795401.png
     * address : 浙江省,杭州市,西湖区,杭州市滨江区滨江区政府
     * phone : 13758127384
     * announcement : null
     * introduce : 这家伙很懒，什么都没留下
     * longitude : 120.218212
     * latitude : 30.214658
     * isRecommend : false
     * openTime : 9:00-21:00
     * isCollect : false
     */

    private String venueId;
    private String venueName;
    private String venueUrl;
    private String address;
    private String phone;
    private Object announcement;
    private String introduce;
    private double longitude;
    private double latitude;
    private boolean isRecommend;
    private String openTime;
    private boolean isCollect;

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setVenueUrl(String venueUrl) {
        this.venueUrl = venueUrl;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAnnouncement(Object announcement) {
        this.announcement = announcement;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setIsRecommend(boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    public String getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueUrl() {
        return venueUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Object getAnnouncement() {
        return announcement;
    }

    public String getIntroduce() {
        return introduce;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public boolean getIsRecommend() {
        return isRecommend;
    }

    public String getOpenTime() {
        return openTime;
    }

    public boolean getIsCollect() {
        return isCollect;
    }
}
