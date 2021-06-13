package cn.zf233.xcloud.entity;

/**
 * Created by zf233 on 11/28/20
 */
public class File {

    private Integer id;
    private Integer parentId;
    private Integer folder;
    private String fileName;
    private String fileSize;
    private String remark;
    private String uploadTime;
    private Integer logoID;

    public File() {
    }

    public File(Integer id, Integer parentId, Integer folder, String fileName, String fileSize, String remark, String uploadTime, Integer logoID) {
        this.id = id;
        this.parentId = parentId;
        this.folder = folder;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.remark = remark;
        this.uploadTime = uploadTime;
        this.logoID = logoID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getFolder() {
        return folder;
    }

    public void setFolder(Integer folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getLogoID() {
        return logoID;
    }

    public void setLogoID(Integer logoID) {
        this.logoID = logoID;
    }
}
