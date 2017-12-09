package system;

public class User {
    private String username, password;
    private String name, sex;
    private Date birthday;
    private String address;
    private String telnum;
    private String recommender, job, zwh;
    private String authorization;
    private String recomm;



    public User(String _username, String _password){
        this.username = _username; this.password = _password;
        name = "?"; sex = "?"; birthday = new Date(); address = "?";
        telnum = "?"; recommender = "?"; job = "?"; authorization = "未审核"; zwh = "?";
        recomm = "?";
    }

    public User(String _username){
        this.username = _username; password = "?";
        name = "?"; sex = "?"; birthday = new Date(); address = "?";
        telnum = "?"; recommender = "?"; job = "?"; authorization = "未审核"; zwh = "?";
    }

    public void setBirthday(String year, String month, String date){
        birthday = new Date(year, month, date);
    }

    public void setInformation(String name, String sex, String address, String telnum, String recommender, String job, String zwh){
        this.name = name; this.sex = sex;  this.address = address;
        this.telnum = telnum; this.recommender = recommender; this.job = job; this.zwh = zwh;
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

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
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

    public String getZwh() {
        return zwh;
    }

    public void setZwh(String zwh) {
        this.zwh = zwh;
    }

    public String getRecomm() {
        return recomm;
    }

    public void setRecomm(String recomm) {
        this.recomm = recomm;
    }
}

class Date{
    private String year, month, day, birthday;
    public Date(String _year, String _month, String _day){
        this.year = _year; this.month = _month; this.day = _day;
        birthday = "?";
    }
    public Date(String _birthday){
        this.birthday = _birthday;
    }
    public Date(){
        year = "?"; month = "?"; day = "?";
    }
    public void set(String _year, String _month, String _day){
        this.year = _year; this.month = _month; this.day = _day;
    }
    public String toString(){
        if(birthday.equals("?")) {
            return year + "年" + month + "月" + day + "日 ";
        }
        else{
            return birthday;
        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}
