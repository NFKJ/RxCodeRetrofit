package com.common.bean.params;

import java.io.Serializable;

public class ActivityBean implements Serializable {

    private String ActivityLabel;
    private int PageSize;
    private int PageIndex;
    private String token;

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
