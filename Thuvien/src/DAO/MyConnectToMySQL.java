package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnectToMySQL {

    String Host = "";
    String UserName = "";
    String Password = "";
    String Database = "";

    Connection connect = null;
    Statement statement = null;
    ResultSet rs = null;

    public MyConnectToMySQL(String Host, String UserName, String Password, String Database) {
        this.Host = Host;
        this.UserName = UserName;
        this.Password = Password;
        this.Database = Database;
    }

    public void DriverTest() throws Exception {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            throw new Exception("MySQL Driver JDBC not found");
        }
    }

    protected Connection getConnection() throws Exception {
        if (this.connect == null) {
            DriverTest();
            String url = "jdbc:mysql://" + this.Host + ":3306/" + this.Database
                    + "?autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
            try {
                this.connect = DriverManager.getConnection(url, this.UserName, this.Password);
            } catch (SQLException e) {
                throw new Exception("Không thể kết nói MySQL server " + e);
            }
        }
        
        return this.connect;
    }

    protected Statement getStatement() throws Exception {
        // kiểm tra statement bằng null hay đã đóng
        if (this.statement == null ? true : this.statement.isClosed()) {
            // khởi tài statement mới
            this.statement = this.getConnection().createStatement();
        }
        // trả vể statement
        return this.statement;
    }

    public ResultSet excuteQuery(String query) throws Exception {
        ResultSet temp = null;
        try {
            // thực thi câu lệnh
            this.rs = getStatement().executeQuery(query);
            temp=rs;
        } catch (Exception e) {
            // nếu không thành công thì ném lỗi ra ngoài
            System.out.println(e);
        } 
        // trả vể resultset
        return temp;
    }

    public int executeUpdate(String query) throws Exception {
        int res = Integer.MIN_VALUE;
        try {
            // thực thi câu lệnh
            res = getStatement().executeUpdate(query);
        } catch (Exception e) {
            throw new Exception("Error" + e);
        } finally {
            // đóng kết nối
            this.Close();
        }
        // trả kết quả ra ngoài
        return res;
    }

    public void Close() throws Exception {
        if (this.rs != null && !this.rs.isClosed()) {
            this.rs.close();
            this.rs = null;
        }
        if (this.statement != null && !this.statement.isClosed()) {
            this.statement.close();
            this.statement = null;
        }
        if (this.connect != null && !this.connect.isClosed()) {
            this.connect.close();
            this.connect = null;
        }

    }
}
