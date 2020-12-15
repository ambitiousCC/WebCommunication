package beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long user_id;
    private String username;
    private String password;
    private String nickname;
    private Date birthday;
    private int age;
    private String phone;
    private String address;
    private String email;
    private String sex;
    private Date create_user_time;
    private String status;
    private String code;
    private String user_img;
    private String user_ico;
    private String user_des;
    private String user_comments;

    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", create_user_time=" + create_user_time +
                ", status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", user_img='" + user_img + '\'' +
                ", user_ico='" + user_ico + '\'' +
                ", user_des='" + user_des + '\'' +
                ", user_comments='" + user_comments + '\'' +
                '}';
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreate_user_time() {
        return create_user_time;
    }

    public void setCreate_user_time(Date create_user_time) {
        this.create_user_time = create_user_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getUser_ico() {
        return user_ico;
    }

    public void setUser_ico(String user_ico) {
        this.user_ico = user_ico;
    }

    public String getUser_des() {
        return user_des;
    }

    public void setUser_des(String user_des) {
        this.user_des = user_des;
    }

    public String getUser_comments() {
        return user_comments;
    }

    public void setUser_comments(String user_comments) {
        this.user_comments = user_comments;
    }

    public User(Long user_id, String nickname, Date birthday, int age, String sex, Date create_user_time, String user_img, String user_ico, String user_des, String phone) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.create_user_time = create_user_time;
        this.user_img = user_img;
        this.user_ico = user_ico;
        this.user_des = user_des;
        this.phone = phone;
    }

    public User(Long user_id, String username, String password, String nickname, Date birthday, int age, String phone, String address, String email, String sex, Date create_user_time, String status, String code, String user_img, String user_ico, String user_des, String user_comments) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.sex = sex;
        this.create_user_time = create_user_time;
        this.status = status;
        this.code = code;
        this.user_img = user_img;
        this.user_ico = user_ico;
        this.user_des = user_des;
        this.user_comments = user_comments;
    }
}
