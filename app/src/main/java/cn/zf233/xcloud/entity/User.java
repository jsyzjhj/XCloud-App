package cn.zf233.xcloud.entity;

/**
 * Created by zf233 on 2020/12/25
 */
public class User {

    private Integer id;
    private String headUrl;
    private String email;
    private String username;
    private String nickname;
    private String password;
    private String role;
    private Integer roleNum;
    private String question;
    private String answer;
    private Integer freeDownloadCount;
    private Integer freeShareCount;
    private String useCapacity;
    private Long useCapacityNum;
    private String capacity;
    private Long capacityNum;
    private Integer level;
    private Integer growthValue;

    public User() {
    }

    public User(Integer id, String headUrl, String email, String username, String nickname, String password, String role, Integer roleNum, String question, String answer, Integer freeDownloadCount, Integer freeShareCount, String useCapacity, Long useCapacityNum, String capacity, Long capacityNum, Integer level, Integer growthValue) {
        this.id = id;
        this.headUrl = headUrl;
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.roleNum = roleNum;
        this.question = question;
        this.answer = answer;
        this.freeDownloadCount = freeDownloadCount;
        this.freeShareCount = freeShareCount;
        this.useCapacity = useCapacity;
        this.useCapacityNum = useCapacityNum;
        this.capacity = capacity;
        this.capacityNum = capacityNum;
        this.level = level;
        this.growthValue = growthValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(Integer roleNum) {
        this.roleNum = roleNum;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getFreeDownloadCount() {
        return freeDownloadCount;
    }

    public void setFreeDownloadCount(Integer freeDownloadCount) {
        this.freeDownloadCount = freeDownloadCount;
    }

    public Integer getFreeShareCount() {
        return freeShareCount;
    }

    public void setFreeShareCount(Integer freeShareCount) {
        this.freeShareCount = freeShareCount;
    }

    public String getUseCapacity() {
        return useCapacity;
    }

    public void setUseCapacity(String useCapacity) {
        this.useCapacity = useCapacity;
    }

    public Long getUseCapacityNum() {
        return useCapacityNum;
    }

    public void setUseCapacityNum(Long useCapacityNum) {
        this.useCapacityNum = useCapacityNum;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Long getCapacityNum() {
        return capacityNum;
    }

    public void setCapacityNum(Long capacityNum) {
        this.capacityNum = capacityNum;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(Integer growthValue) {
        this.growthValue = growthValue;
    }
}
