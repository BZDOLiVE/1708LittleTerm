package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    String driver, url, username, password;
    Connection conn;
    Statement statement1, statementSignInCheck, statementCheckUsername, statementSignUpUser, statementCheckReturnUser,
            statementUpdateUser;
    //public ArrayList<User> UserList;

    public Database(){
        // 驱动程序名
        driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名
        url = "jdbc:mysql://127.0.0.1:3306/1708USERDATA";

        // MySQL配置时的用户名
        username = "root";

        // MySQL配置时的密码
        password = "";

        //UserList = new ArrayList<User>();

        LinktoDB();
    }

    public void LinktoDB(){
        try {
            // 加载驱动程序
            Class.forName(driver);

            // 连续数据库
            Connection conn = DriverManager.getConnection(url, username, password);

            if(!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");

            // statement用来执行SQL语句
            statement1 = conn.createStatement();
            statementSignInCheck = conn.createStatement();
            statementCheckUsername = conn.createStatement();
            statementSignUpUser = conn.createStatement();
            statementCheckReturnUser = conn.createStatement();
        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public User CheckReturnUser(String username){
        String sql1 = "SELECT * FROM userdata WHERE username = ";
        sql1 += ("\"" + username + "\"") ;
        User u = new User(username);
        try {
            ResultSet rs1 = statementCheckReturnUser.executeQuery(sql1);
            if(rs1.next()) {
                u.setPassword(rs1.getString("password"));
                u.setBirthday(new Date(rs1.getString("birthday")));
                u.setInformation(rs1.getString("name"),rs1.getString("sex"),rs1.getString("address"),
                        rs1.getString("telnum"), rs1.getString("recommender"),rs1.getString("job"),
                        rs1.getString("zwh"));
                u.setAuthorization(rs1.getString("authorization"));
                u.setRecomm(rs1.getString("recomm"));
                return u;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean CheckUsername(String username){
        String sql1 = "SELECT * FROM userdata WHERE username = ";
        sql1 += ("\"" + username + "\"" + ";") ;
        try {
            ResultSet rs1 = statementCheckUsername.executeQuery(sql1);
            if(rs1.next()){
                System.out.println("用户名已存在");
                return false;
            }
            else{
                //System.out.println("不存在这个用户");
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Sln> CheckAllSln(){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM slndata;";
        ArrayList<Sln> SlnList = new ArrayList<Sln>();
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                Sln s = new Sln();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setAuthor(rs1.getString("author"));
                s.setDoc(rs1.getString("doc"));
                s.setStatus(rs1.getString("status"));
                s.setDeadline(rs1.getString("deadline"));
                s.setAgree(rs1.getInt("agree"));
                s.setDisagree(rs1.getInt("disagree"));
                SlnList.add(s);
            }
            return SlnList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Sln> CheckUnApproSln(){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM slndata WHERE status = " + "\"" + "未审核" + "\"" + ";";
        ArrayList<Sln> SlnList = new ArrayList<Sln>();
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                Sln s = new Sln();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setAuthor(rs1.getString("author"));
                s.setDoc(rs1.getString("doc"));
                s.setStatus(rs1.getString("status"));
                s.setDeadline(rs1.getString("deadline"));
                s.setAgree(rs1.getInt("agree"));
                s.setDisagree(rs1.getInt("disagree"));
                SlnList.add(s);
            }
            return SlnList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Sln> CheckPrvtSln(String username){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM slndata WHERE author = " + "\"" + username + "\"" + ";";
        ArrayList<Sln> SlnList = new ArrayList<Sln>();
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                Sln s = new Sln();
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setAuthor(rs1.getString("author"));
                s.setDoc(rs1.getString("doc"));
                s.setStatus(rs1.getString("status"));
                s.setDeadline(rs1.getString("deadline"));
                s.setAgree(rs1.getInt("agree"));
                s.setDisagree(rs1.getInt("disagree"));
                SlnList.add(s);
            }
            return SlnList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public Sln CheckOneSln(int id){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM slndata WHERE id = " +  id  + ";";
        Sln s = new Sln();

        try {
            // 结果集
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                s.setId(rs1.getInt("id"));
                s.setName(rs1.getString("name"));
                s.setAuthor(rs1.getString("author"));
                s.setDoc(rs1.getString("doc"));
                s.setStatus(rs1.getString("status"));
                s.setDeadline(rs1.getString("deadline"));
                s.setAgree(rs1.getInt("agree"));
                s.setDisagree(rs1.getInt("disagree"));
            }
            return s;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> CheckSignUpUser(){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM userdata WHERE authorization = " + "\"" + "未审核" + "\"" + ";";
        ArrayList<User> UserList = new ArrayList<User>();
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                User u = new User(rs1.getString("username"),rs1.getString("password"));
                u.setAuthorization(rs1.getString("authorization"));
                u.setName(rs1.getString("name"));
                u.setSex(rs1.getString("sex"));
                u.setBirthday(new Date(rs1.getString("birthday")));
                u.setAddress(rs1.getString("address"));
                u.setTelnum(rs1.getString("telnum"));
                u.setRecommender(rs1.getString("recommender"));
                u.setJob(rs1.getString("job"));
                u.setZwh(rs1.getString("zwh"));
                UserList.add(u);
            }
            return UserList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public String SignInCheck(String username, String password){
        //User u = new User("username", "?");
        String sql1 = "SELECT * FROM userdata WHERE username = ";
        sql1 += ("\"" + username + "\"") ;
        try {
            ResultSet rs1 = statementSignInCheck.executeQuery(sql1);
            if(rs1.next()){
                //u.setPassword(rs1.getString("password"));
				if(password.equals(rs1.getString("password")) && rs1.getString("authorization").equals("admin")){
				    return "admin";
                }
                if(password.equals(rs1.getString("password")) && rs1.getString("authorization").equals("writer")){
                    return "writer";
                }
                else{
				    return "error";
                }
            }
            else{
				System.out.println("不存在这个用户");
                return "error";
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error";
        }

    }

    public void SignUpUser(User u){
        String sql1 = "INSERT INTO USERDATA" +
                " VALUES (" + "\"" + u.getUsername() + "\"" +
                "," + "\"" + u.getPassword() + "\"" +
                "," + "\"" + "未审核" + "\"" +
                "," + "\"" + u.getName() + "\"" +
                "," + "\"" + u.getSex() + "\"" +
                "," + "\"" + u.getBirthday().toString() + "\"" +
                "," + "\"" + u.getAddress() + "\"" +
                "," + "\"" + u.getTelnum() + "\"" +
                "," + "\"" + u.getRecommender() + "\"" +
                "," + "\"" + u.getJob() + "\"" +
                "," + "\"" + u.getZwh() + "\"" +
                "," + "\"" + u.getRecomm() + "\"" +
                ");";
        try {
            statementSignUpUser.executeUpdate(sql1);
            System.out.println("插入成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void UpdateUser(User u){
        String sql1 = "UPDATE USERDATA" +
                " SET password = "  + "\"" + u.getPassword() + "\"" +
                " ,name = " + "\"" + u.getName() + "\"" +
                " ,sex = " + "\"" + u.getSex() + "\"" +
                " ,address = " + "\"" + u.getAddress() + "\"" +
                " ,telnum = " + "\"" + u.getTelnum() + "\"" +
                " ,recommender = " + "\"" + u.getRecommender() + "\"" +
                " ,job = " + "\"" + u.getJob() + "\"" +
                " ,zwh = " + "\"" + u.getZwh() + "\"" +
                " WHERE username = " + "\"" + u.getUsername() + "\"" + ";";
        try {
            statementSignUpUser.executeUpdate(sql1);
            System.out.println("修改成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void UpdateUserAth(String username, String ath){
        String upAth;
        if(ath.equals("writer")){
            upAth = "writer";
        }
        else if(ath.equals("admin")){
            upAth = "admin";
        }
        else{
            return;
        }
        String sql1 = "UPDATE USERDATA" +
                " SET authorization = "  + "\"" + upAth + "\"" +
                " WHERE username = " + "\"" + username + "\"" + ";";
        try {
            statementSignUpUser.executeUpdate(sql1);
            System.out.println("修改成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void DeleteUser(String username){
        String sql1 = "DELETE FROM USERDATA WHERE USERNAME = " + "\"" + username + "\"" + ";";
        try {
            statementSignUpUser.executeUpdate(sql1);
            System.out.println("删除成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addSln(Sln s){
        String sql1 = "INSERT INTO slndata" +
                " VALUES (null, " + "\"" + s.getName() + "\"" +
                "," + "\"" + s.getAuthor() + "\"" +
                "," + "\"" + s.getDoc() + "\"" +
                "," + "\"" + s.getStatus() + "\"" +
                "," + "\"" + s.getDeadline() + "\"" +
                "," +  s.getAgree() +
                "," +  s.getDisagree() +
                ");";

        try {
            statement1.executeUpdate(sql1);
            System.out.println("插入成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void deleteSln(int id){
        String sql1 = "delete from slndata where id = " + Integer.toString(id) + ";";
        try {
            statement1.executeUpdate(sql1);
            System.out.println("删除成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void AgreeSln(int id, String comment, String username, boolean flag){
        String sql1, sql2;
        if(flag){
            sql1 = "UPDATE slndata SET agree = agree + 1 WHERE id = " + Integer.toString(id) + ";";
        }
        else{
            sql1 = "UPDATE slndata SET disagree = disagree + 1 WHERE id = " + Integer.toString(id) + ";";
        }
        sql2 = "INSERT INTO agree VALUES(" + Integer.toString(id) + ", "
                + "\"" + username + "\"" +");";
        try {
            statement1.executeUpdate(sql1);
            statement1.executeUpdate(sql2);
            System.out.println("点赞成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean CheckAgree(int id, String username){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM agree WHERE id = " + Integer.toString(id) + ";";
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                if(rs1.getString("username").equals(username)){
                    return false;
                }
            }
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public void CommentSln(int id, String comment, String username){
        String sql1 = "INSERT INTO comment VALUES( " + Integer.toString(id) + ", "
                + "\"" + comment + "\"" + ", "
                + "\"" + username + "\"" +");";
        try {
            statement1.executeUpdate(sql1);
            System.out.println("评论成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void ApproSln(int id){
        String sql1 = "UPDATE slndata SET status = " + "\"" + "通过" + "\"" + " WHERE id = " + Integer.toString(id) + ";";
        try {
            statement1.executeUpdate(sql1);
            System.out.println("通过成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public ArrayList<comment> Checkcomm(int id){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM comment WHERE id = " + Integer.toString(id) + ";";
        ArrayList<comment> commList = new ArrayList<comment>();
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                comment c = new comment();
                c.setId(rs1.getInt("id"));
                c.setComm(rs1.getString("comment"));
                c.setName(rs1.getString("name"));
                commList.add(c);
            }
            return commList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> CheckRecommUser(String username){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM userdata WHERE authorization = " + "\"" + "未审核" + "\"" + ";";
        ArrayList<User> UserList = new ArrayList<User>();
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                if(rs1.getString("recommender").equals(username)) {
                    User u = new User(rs1.getString("username"), rs1.getString("password"));
                    u.setAuthorization(rs1.getString("authorization"));
                    u.setName(rs1.getString("name"));
                    u.setSex(rs1.getString("sex"));
                    u.setBirthday(new Date(rs1.getString("birthday")));
                    u.setAddress(rs1.getString("address"));
                    u.setTelnum(rs1.getString("telnum"));
                    u.setRecommender(rs1.getString("recommender"));
                    u.setJob(rs1.getString("job"));
                    u.setZwh(rs1.getString("zwh"));
                    UserList.add(u);
                }
            }
            return UserList;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public void UserRecomm(String s, String recusername){
        String sql1 = "UPDATE USERDATA SET recomm = " + "\"" + s + "\"" + " WHERE USERNAME = " + "\"" + recusername + "\"" + ";";
        try {
            statement1.executeUpdate(sql1);
            System.out.println("推荐成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}




