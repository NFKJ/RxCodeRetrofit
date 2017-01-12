package com.common.retrofit.entity.params;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/28.
 */
public class ActivityBean implements Serializable {

    private String ActivityLabel;
    private int PageSize;
    private int PageIndex;
    private String venueId;

    private String token;

    public ActivityBean(String venueId) {
        this.venueId = venueId;
    }

    public ActivityBean(String activityLabel, int pageSize, int pageIndex, String token) {
        ActivityLabel = activityLabel;
        PageSize = pageSize;
        PageIndex = pageIndex;
        this.token = token;
    }

    public ActivityBean(String activityLabel, int pageSize, int pageIndex) {
        ActivityLabel = activityLabel;
        PageSize = pageSize;
        PageIndex = pageIndex;
    }
}
