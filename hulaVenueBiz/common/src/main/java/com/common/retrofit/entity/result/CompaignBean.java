package com.common.retrofit.entity.result;

import java.io.Serializable;

/**
 * Create by Leo on 2015/11/8.
 */
public class CompaignBean implements Serializable {

    private String ActivityId;
    private String ActivityName;
    private String Image;
    private int JoinUsersCount;
    private int TotalCount;
    private boolean Official;
    private String Url;
    private String StartTime;
    private String EndTime;
    private String ActivityStatus;
    private int ActivityType;
    private String ActivityCategorys;
    private String Price;
    private String UserName;
    private String UserId;
    private String ActivityUrl;
    private int MyActivityState;        // 0已付款  1未付款  2已支付待审核 3报名驳回  4报名成功
    private String PayType;        // 1是免费 2是线上交易 3是线下交易
    private boolean IsApply;
    private String CreateTime;
    private String ReceiveName;
    private String ActivityOrderId;
    private String ShareDescription;
    private String ShareUrl;

    public String getShareDescription() {
        return ShareDescription;
    }

    public void setShareDescription(String shareDescription) {
        ShareDescription = shareDescription;
    }

    public String getActivityOrderId() {
        return ActivityOrderId;
    }

    public void setActivityOrderId(String activityOrderId) {
        ActivityOrderId = activityOrderId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getReceiveName() {
        return ReceiveName;
    }

    public void setReceiveName(String receiveName) {
        ReceiveName = receiveName;
    }

    public boolean isApply() {
        return IsApply;
    }

    public void setIsApply(boolean isApply) {
        IsApply = isApply;
    }

    public int getMyActivityState() {
        return MyActivityState;
    }

    public void setMyActivityState(int myActivityState) {
        MyActivityState = myActivityState;
    }

    public String getPayType() {
        return PayType;
    }

    public void setPayType(String payType) {
        PayType = payType;
    }

    public String getActivityUrl() {
        return ActivityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        ActivityUrl = activityUrl;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getJoinUsersCount() {
        return JoinUsersCount;
    }

    public void setJoinUsersCount(int joinUsersCount) {
        JoinUsersCount = joinUsersCount;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public boolean isOfficial() {
        return Official;
    }

    public void setOfficial(boolean official) {
        Official = official;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getActivityStatus() {
        return ActivityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        ActivityStatus = activityStatus;
    }

    public int getActivityType() {
        return ActivityType;
    }

    public void setActivityType(int activityType) {
        ActivityType = activityType;
    }

    public String getActivityCategorys() {
        return ActivityCategorys;
    }

    public void setActivityCategorys(String activityCategorys) {
        ActivityCategorys = activityCategorys;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }


    public String getShareUrl() {
        return ShareUrl;
    }

    public void setShareUrl(String shareUrl) {
        ShareUrl = shareUrl;
    }
}
