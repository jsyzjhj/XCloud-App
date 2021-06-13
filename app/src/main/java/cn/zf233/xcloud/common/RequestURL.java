package cn.zf233.xcloud.common;

/**
 * Created by zf233 on 11/28/20
 */
public enum RequestURL {

    LOGIN_URL(0, "https://www.xcloud.show/user/login"),
    HOME_URL(1, "https://www.xcloud.show/user/home"),
    REGIST_URL(2, "https://www.xcloud.show/user/regist"),
    UPDATE_URL(3, "https://www.xcloud.show/user/update"),
    DOWNLOAD_URL(4, "https://www.xcloud.show/file/download"),
    REMOVE_FILE_URL(5, "https://www.xcloud.show/file/delete"),
    UPLOAD_FILE_URL(6, "https://www.xcloud.show/file/upload"),
    CREATE_FOLDER(6, "https://www.xcloud.show/file/createfolder");

    private final Integer code;
    private final String desc;

    RequestURL(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

