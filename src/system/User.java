package system;

public class User {
    private String username, password;
    private String name, sex;
    private Date birthday;
    private String address;
    private int telnum;
    private String recommender, job;
    private String authorization;

    public User(String _username, String _password){
        this.username = _username; this.password = _password;
        name = "?"; sex = "?"; birthday = new Date(); address = "?";
        telnum = -1; recommender = "?"; job = "?"; authorization = "?";
    }

    public void setInformation(String name, String sex, Date birthday, String address, int telnum, String recommender, String job){
        this.name = name; this.sex = sex; this.birthday = birthday; this.address = address;
        this.telnum = telnum; this.recommender = recommender; this.job = job;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTelnum() {
        return telnum;
    }

    public void setTelnum(int telnum) {
        this.telnum = telnum;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }


}

class Date{
    private int year, month, day;
    public Date(int _year, int _month, int _day){
        this.year = _year; this.month = _month; this.day = _day;
    }
    public Date(){
        year = 0; month = 0; day = 0;
    }
    public void set(int _year, int _month, int _day){
        this.year = _year; this.month = _month; this.day = _day;
    }
    public String toString(){
        return " " + Integer.toString(year) + "年" + Integer.toString(month) + "月" + Integer.toString(day) + "日 ";
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


}
