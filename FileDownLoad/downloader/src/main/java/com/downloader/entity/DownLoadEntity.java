package com.downloader.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @desc:         文件下载实体类
 * @author:       Leo
 * @date:         2016/12/6
 */
public class DownLoadEntity implements Parcelable
{
    private int dataId;                       // 标识任务序号
    private String Url;                       // 下载链接地址
    private long end;                         // 下载结束位置
    private long start;                       // 下载起始位置
    private long downed;                      // 任务下载大小
    private long total;                       // 文件大小
    private String saveName;                  // 下载保存名称
    private String lastModify;                // 服务器文件内容是否改变对比标志
    private boolean isSupportMulti;           // 是否支持多任务
    private List<DownLoadEntity> multiList;   // 多任务列表

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getDowned() {
        return downed;
    }

    public void setDowned(long downed) {
        this.downed = downed;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public List<DownLoadEntity> getMultiList() {
        return multiList;
    }

    public void setMultiList(List<DownLoadEntity> multiList) {
        this.multiList = multiList;
    }

    public String getLastModify() {
        return lastModify;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public boolean isSupportMulti() {
        return isSupportMulti;
    }

    public void setSupportMulti(boolean supportMulti) {
        isSupportMulti = supportMulti;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dataId);
        dest.writeString(this.Url);
        dest.writeLong(this.end);
        dest.writeLong(this.start);
        dest.writeLong(this.downed);
        dest.writeLong(this.total);
        dest.writeString(this.saveName);
        dest.writeTypedList(multiList);
    }

    public DownLoadEntity() {
    }

    protected DownLoadEntity(Parcel in) {
        this.dataId = in.readInt();
        this.Url = in.readString();
        this.end = in.readLong();
        this.start = in.readLong();
        this.downed = in.readLong();
        this.total = in.readLong();
        this.saveName = in.readString();
        this.multiList = in.createTypedArrayList(DownLoadEntity.CREATOR);
    }

    public static final Creator<DownLoadEntity> CREATOR = new Creator<DownLoadEntity>() {
        public DownLoadEntity createFromParcel(Parcel source) {
            return new DownLoadEntity(source);
        }

        public DownLoadEntity[] newArray(int size) {
            return new DownLoadEntity[size];
        }
    };

    @Override
    public String toString() {
        return "dataId = " + dataId +
        "\nurl = " + dataId +
        "\nstart = " + start +
        "\nend = " + end +
        "\ndowned = " + downed +
        "\ntotal = " + total +
        "\nsavaname = " + saveName +
        "\nlastmodify = " + lastModify +
        "\nisSupportMutil = " + isSupportMulti;
    }
}