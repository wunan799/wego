/**
 * Created by wunan on 16-9-21.
 */
package org.wnsoft.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String userid;
    private String name;
    private List<Integer> department;
    private String position;
    private String mobile;
    private String gender;   //性别。0表示未定义，1表示男性，2表示女性
    private String email;
    private String weixinid;
    private String avatar;   //头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
    private int status;      //关注状态: 1=已关注，2=已禁用，4=未关注
    private Extattr extattr; //	扩展属性

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
    }

    public List<Integer> getDepartment() {
        return this.department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getWeixinid() {
        return this.weixinid;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setExtattr(Extattr extattr) {
        this.extattr = extattr;
    }

    public Extattr getExtattr() {
        return this.extattr;
    }

    public static class Extattr implements Serializable {
        private List<Attrs> attrs;

        public void setAttrs(List<Attrs> attrs) {
            this.attrs = attrs;
        }

        public List<Attrs> getAttrs() {
            return this.attrs;
        }

        @Override
        public String toString() {
            return "Extattr{" +
                    "attrs=" + attrs +
                    '}';
        }
    }

    public static class Attrs implements Serializable {
        private String name;

        private String value;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "Attrs{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", position='" + position + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", weixinid='" + weixinid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", extattr=" + extattr +
                '}';
    }
}
