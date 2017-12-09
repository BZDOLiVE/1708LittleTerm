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
    Statement statement1, statement2, statement3, statement4;
    public ArrayList<User> UserList;

    public Database(){
        // 驱动程序名
        driver = "com.mysql.jdbc.Driver";

        // URL指向要访问的数据库名
        url = "jdbc:mysql://127.0.0.1:3306/1708USERDATA";

        // MySQL配置时的用户名
        username = "root";

        // MySQL配置时的密码
        password = "buzhidao96923";

        UserList = new ArrayList<User>();

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
            statement2 = conn.createStatement();
            statement3 = conn.createStatement();
            statement4 = conn.createStatement();

        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean CheckUsername(String username){
        String sql1 = "SELECT * FROM userdata WHERE username = ";
        sql1 += ("\"" + username + "\"") ;
        try {
            ResultSet rs1 = statement3.executeQuery(sql1);
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

    public void CheckAll(){
        // 要执行的SQL语句
        String sql1 = "SELECT * FROM userdata";
        //String sql2 = "SELECT * FROM sales WHERE pid = ";
        //String sql3 = "";

        String name = null;
        // 结果集
        try {
            ResultSet rs1 = statement1.executeQuery(sql1);
            while(rs1.next()) {
                User u = new User(rs1.getString("username"),rs1.getString("password"));
                //u.setTelnum(rs1.getInt("telnum"));
                UserList.add(u);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		for(User u : UserList){
			System.out.println(u.getUsername());
            System.out.println(u.getPassword());
		}
        //UserList.clear();
    }

    public String SignInCheck(String username, String password){
        //User u = new User("username", "?");
        String sql1 = "SELECT * FROM userdata WHERE username = ";
        sql1 += ("\"" + username + "\"") ;
        try {
            ResultSet rs1 = statement2.executeQuery(sql1);
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
                "," + "\"" + "申请中" + "\"" +
                "," + "\"" + u.getName() + "\"" +
                "," + "\"" + u.getSex() + "\"" +
                "," + "\"" + u.getBirthday().toString() + "\"" +
                "," + "\"" + u.getAddress() + "\"" +
                "," + "\"" + u.getTelnum() + "\"" +
                "," + "\"" + u.getRecommender() + "\"" +
                "," + "\"" + u.getJob() + "\"" +
                "," + "\"" + u.getZwh() + "\"" +
                ");";
        try {
            statement4.executeUpdate(sql1);
            System.out.println("插入成功");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
