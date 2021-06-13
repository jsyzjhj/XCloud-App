package cn.zf233.xcloud.util;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import cn.zf233.xcloud.common.RequestBody;
import cn.zf233.xcloud.common.RequestTypeENUM;
import cn.zf233.xcloud.common.ResponseCodeENUM;
import cn.zf233.xcloud.common.ServerResponse;
import cn.zf233.xcloud.entity.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zf233 on 11/28/20
 * Singleton
 */
public class RequestUtil {

    private static final String REQUEST_BODY_NAME = "requestBody";

    private Boolean isUsed;
    private Integer requestType;
    private String fileName;

    public static final RequestUtil requestUtil = new RequestUtil();

    public static synchronized RequestUtil getRequestUtil() {
        return requestUtil;
    }

    // common request
    public <T> ServerResponse<T> requestUserXCloudServer(String url, RequestBody requestBody, TypeToken<ServerResponse<T>> token) {
        try {
            OkHttpClient client = new OkHttpClient();
            /*FormBody formBody = new FormBody
                    .Builder()
                    .add(REQUEST_BODY_NAME, JsonUtil.tojson(requestBody))
                    .build();*/
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JsonUtil.toJson(requestBody));

            Request request = new Request
                    .Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body() != null ? response.body().string() : null;
                if (json != null) {
                    return JsonUtil.toObject(json, token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServerResponse<T> serverResponse = new ServerResponse<>();
        serverResponse.setStatus(ResponseCodeENUM.ERROR.getCode());
        serverResponse.setMsg("请求超时");
        return serverResponse;
    }


    // file download
    public ServerResponse<File> fileDownload(User user, String url, Integer fileId, String fileName) {
        ServerResponse<File> serverResponse = new ServerResponse<>();
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new RequestBody();
            requestBody.setUser(user);
            requestBody.setFileId(fileId);
            /*FormBody formBody = new FormBody
                    .Builder()
                    .add(REQUEST_BODY_NAME, JsonUtil.toJson(body))
                    .build();*/

            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JsonUtil.toJson(requestBody));

            Request request = new Request
                    .Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body() != null ? response.body().string() : null;
                if (json != null) {
                    ServerResponse serverResponseUrl = JsonUtil.toObject(json, new TypeToken<ServerResponse>() {
                    });
                    serverResponse.setStatus(serverResponseUrl.getStatus());
                    serverResponse.setMsg(serverResponseUrl.getMsg());
                    if (serverResponseUrl.isSuccess()) {
                        serverResponse.setData(fileDownload(serverResponseUrl.getData().toString(), fileName));
                        return serverResponse;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverResponse;
    }

    // file download
    private File fileDownload(String url, String fileName) {
        try {
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build();
            Request request = new Request
                    .Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                InputStream inputStream = response.body() != null ? response.body().byteStream() : null;
                if (null == inputStream) {
                    return null;
                }
                return FileUtil.outputFile(inputStream, fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // file remove
    public ServerResponse fileRemove(String url, User user, Integer fileID) {
        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new RequestBody();
            requestBody.setUser(user);
            requestBody.setFileId(fileID);

            /*FormBody formBody = new FormBody
                    .Builder()
                    .add(REQUEST_BODY_NAME, JsonUtil.toJson(body))
                    .build();*/


            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JsonUtil.toJson(requestBody));

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body() != null ? response.body().string() : null;
                if (json != null) {
                    return JsonUtil.toObject(json, ServerResponse.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(ResponseCodeENUM.ERROR.getCode());
        serverResponse.setMsg("请求超时");
        return serverResponse;
    }

    // create folder
    public ServerResponse createFolder(String url, User user, String folderName, Integer parentid) {
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new RequestBody();
            requestBody.setUser(user);
            requestBody.setFolderName(folderName);
            requestBody.setParentid(parentid);
            /*FormBody formBody = new FormBody
                    .Builder()
                    .add(REQUEST_BODY_NAME, JsonUtil.toJson(body))
                    .build();*/
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, JsonUtil.toJson(requestBody));
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body() != null ? response.body().string() : null;
                if (json != null) {
                    return JsonUtil.toObject(json, ServerResponse.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(ResponseCodeENUM.ERROR.getCode());
        serverResponse.setMsg("请求超时");
        return serverResponse;
    }

    // file upload
    public ServerResponse uploadFile(String url, User user, File uploadFile, Integer parentid) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        RequestBody body = new RequestBody();
        body.setUser(user);
        body.setParentid(parentid);

        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("requestBody", JsonUtil.toJson(body))
                .addFormDataPart("myFile", uploadFile.getName(),
                        okhttp3.RequestBody.create(MediaType.parse("multipart/form-data"), uploadFile))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String json = response.body() != null ? response.body().string() : null;
                if (json != null) {
                    return JsonUtil.toObject(json, ServerResponse.class);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServerResponse serverResponse = new ServerResponse();
        serverResponse.setStatus(ResponseCodeENUM.ERROR.getCode());
        serverResponse.setMsg("请求超时");
        return serverResponse;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean used) {
        isUsed = used;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private RequestUtil() {
        this.isUsed = false;
        this.requestType = RequestTypeENUM.UNKNOWN_TYPE.getCode();
        this.fileName = null;
    }
}
