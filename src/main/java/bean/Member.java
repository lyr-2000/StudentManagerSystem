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
    private String admin;

    // /**
    //  *
    //  * 用户是否为管理员
    //  * */
    // private boolean isAdmin;

    public Member() {

    }

    {
        /*
        *
        * 用户默认不是管理员，如果数据库 访问到是 ，再 设置
        *
        * */
        // this.isAdmin = false;
        this.admin = "普通成员";

    }




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

    //
    // public boolean isAdmin() {
    //     return isAdmin;
    // }


    // /**
    //  *
    //  * 设置用户是否为管理员 的身份
    //  * */
    // public void setIsAdmin(boolean admin) {
    //     isAdmin = admin;
    // }





    /**
     *
     * 用户 的管理员字段
     *
     *
     * */

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != null ? !id.equals(member.id) : member.id != null) return false;
        return name != null ? name.equals(member.name) : member.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                '}';
    }


}
