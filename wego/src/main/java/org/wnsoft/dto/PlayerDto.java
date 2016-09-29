/**
 * Created by wunan on 16-9-29.
 */
package org.wnsoft.dto;

import java.io.Serializable;

public class PlayerDto implements Serializable {
    private String userid;
    private String name;
    private String position;
    private String mobile;
    private String gender;   //性别。0表示未定义，1表示男性，2表示女性
    private String email;
    private String weixinid;
    private String avatar;   //头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
    private int status;      //0-未报名 1-已报名 2-被报名

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
