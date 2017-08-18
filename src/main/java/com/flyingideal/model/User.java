package com.flyingideal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.flyingideal.jsonview.UserJsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/18.
 */
@XmlRootElement
public class User implements Serializable {
    @JsonView(UserJsonView.class)
    private Long id;
    @JsonView(UserJsonView.class)
    private String username;
    private String password;
    private String salt;
    @JsonView(UserJsonView.class)
    private Boolean locked;
    @JsonView(UserJsonView.class)
    private Integer source;
    @JsonView(UserJsonView.class)
    private String sourceUserName;
    @JsonView(UserJsonView.class)
    private Integer organizationId;
    @JsonView(UserJsonView.class)
    private String email;
    @JsonView(UserJsonView.class)
    private String telephone;
    @JsonView(UserJsonView.class)
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  //使用@DateTimeFormat对java8中的LocalDate等日期、时间格式进行格式化的时候需要添加相关的jar包
    @JsonFormat(pattern = "yyyy-MM-dd")               //使用jackson的@JsonFormat进行日期的自动转换，在此可以代替@DateTimeFormat标签
    private LocalDate birthday;
    @JsonView(UserJsonView.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtCreate;
    @JsonView(UserJsonView.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtModified;
    @JsonView(UserJsonView.class)
    private List<Integer> roleIds;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourceUserName() {
        return sourceUserName;
    }

    public void setSourceUserName(String sourceUserName) {
        this.sourceUserName = sourceUserName;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", locked=" + locked +
                ", source=" + source +
                ", sourceUserName='" + sourceUserName + '\'' +
                ", organizationId=" + organizationId +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthday=" + birthday +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", roleIds=" + roleIds +
                '}';
    }
}
