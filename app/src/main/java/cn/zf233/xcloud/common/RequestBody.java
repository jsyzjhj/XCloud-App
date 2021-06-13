package cn.zf233.xcloud.common;


import cn.zf233.xcloud.entity.User;

/**
 * Created by zf233 on 11/28/20
 */
public class RequestBody {

    private final String appVersionCode = RequestTypeENUM.VERSION_FAILURE.getDesc();
    private User user;
    private Integer sortFlag;
    private Integer sortType;
    private String matchCode;
    private String inviteCode;
    private Integer parentid;
    private Integer fileId;
    private String folderName;

    public RequestBody() {
    }

    public RequestBody(User user, Integer sortFlag, Integer sortType, String matchCode, String inviteCode, Integer parentid, Integer fileId, String folderName) {
        this.user = user;
        this.sortFlag = sortFlag;
        this.sortType = sortType;
        this.matchCode = matchCode;
        this.inviteCode = inviteCode;
        this.parentid = parentid;
        this.fileId = fileId;
        this.folderName = folderName;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(Integer sortFlag) {
        this.sortFlag = sortFlag;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
