package com.downloader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/05
 */
public interface DownLoadService {

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl, @Header("Range") String range);

    @Streaming
    @GET
    Call<ResponseBody> getHttpHeader(@Url String fileUrl, @Header("Range") String range);

    @Streaming
    @GET
    Call<ResponseBody> getHttpHeaderWithIfRange(@Url String fileUri,
                                                @Header("If-Range") String lastModify,
                                                @Header("Range") String range);
}
