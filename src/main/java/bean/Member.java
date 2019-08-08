package bean;

/**
 * Created by ASUS on 2019/8/6.
 *
 * 模型层
 */
public class Member {
    private String id;
    private String name;
    private String sex;
    private String joinTime;
    private String work;
    private String birthday;
    private String subject;
    private String phone;
    private String signature;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWork() {

        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getSignature() {

        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSubject() {

        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getJoinTime() {

        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                '}';
    }
}
