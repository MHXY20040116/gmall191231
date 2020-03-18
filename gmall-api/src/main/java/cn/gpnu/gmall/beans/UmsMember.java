package cn.gpnu.gmall.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class UmsMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String id;
    @Column
    private String memberLevelId;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String nickname;
    @Column
    private String phone;
    @Column
    private int status;
    @Column
    private Date createTime;
    @Column
    private String icon;
    @Column
    private int gender;
    @Column
    private Date birthday;
    @Column
    private String city;
    @Column
    private String job;
    @Column
    private String personalizedSignature;
    @Column
    private int sourceType;
    @Column
    private int integration;
    @Column
    private int growth;
    @Column
    private int luckeyCount;
    @Column
    private int historyIntegration;

    public UmsMember() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMemberLevelId() {
        return memberLevelId;
    }
    public void setMemberLevelId(String memberLevelId) {
        this.memberLevelId = memberLevelId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public String getPersonalizedSignature() {
        return personalizedSignature;
    }
    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }
    public int getSourceType() {
        return sourceType;
    }
    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }
    public int getIntegration() {
        return integration;
    }
    public void setIntegration(int integration) {
        this.integration = integration;
    }
    public int getGrowth() {
        return growth;
    }
    public void setGrowth(int growth) {
        this.growth = growth;
    }
    public int getLuckeyCount() {
        return luckeyCount;
    }
    public void setLuckeyCount(int luckeyCount) {
        this.luckeyCount = luckeyCount;
    }
    public int getHistoryIntegration() {
        return historyIntegration;
    }
    public void setHistoryIntegration(int historyIntegration) {
        this.historyIntegration = historyIntegration;
    }

    @Override
    public String toString() {
        return "UmsMember{" +
                "id='" + id + '\'' +
                ", memberLevelId='" + memberLevelId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", icon='" + icon + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", city='" + city + '\'' +
                ", job='" + job + '\'' +
                ", personalizedSignature='" + personalizedSignature + '\'' +
                ", sourceType=" + sourceType +
                ", integration=" + integration +
                ", growth=" + growth +
                ", luckeyCount=" + luckeyCount +
                ", historyIntegration=" + historyIntegration +
                '}';
    }

}
