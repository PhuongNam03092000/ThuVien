package DAO;

import java.sql.ResultSet;
import java.util.HashMap;

public class MyConnectUnit {

    private MyConnectToMySQL connect=null;

    public MyConnectUnit(String host, String userName, String password, String database,String vitri) {
        connect = new MyConnectToMySQL(host, userName, password, database);
    }

    // SELECT * FROM tableName WHERE codition ORDER BY OrderBy
    public ResultSet Select(String tableName, String condition, String OrderBy) throws Exception {
        StringBuilder query = new StringBuilder("SELECT * FROM " + tableName);
        // đưa câu lệnh đi�?u kiện vào câu query
        this.AddCondition(query, condition);
        // đưa câu lệnh Order vào câu query
        this.AddOrderBy(query, OrderBy);
        // thêm ký tực ";" vào cuối các câu lệnh
        query.append(";");
        return this.connect.excuteQuery(query.toString());
    }

    public ResultSet Select(String tableName, String condition) throws Exception {
        return this.Select(tableName, condition, null);
    }

    public ResultSet Select(String tableName) throws Exception {
        return Select(tableName, null);
    }

    public void AddCondition(StringBuilder query, String condition) {
        if (condition != null) {
            query.append(" WHERE " + condition);
        }
    }

    public void AddOrderBy(StringBuilder query, String OrderBy) {
        if (OrderBy != null) {
            query.append("ORDER BY " + OrderBy);
        }
    }

    public boolean Insert(String tableName, HashMap<String, Object> ColumnValues) throws Exception {
        // Khai báo StringBuilder để tạo chuỗi Insert
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName);
        // Khai báo biến StringBuilder để khai báo ColumnValues
        StringBuilder valueInsert = new StringBuilder();

        query.append("(");
        for (String key : ColumnValues.keySet()) {
            query.append(key + ",");
            valueInsert.append("'" + ColumnValues.get(key).toString() + "' ,");
        }
        // cắt ký tự , ở cuối câu
        query = query.delete(query.length() - 1, query.length());
        valueInsert = valueInsert.delete(valueInsert.length() - 1, valueInsert.length());
        // đưa giá trị vào câu query
        query.append(") VALUES (" + valueInsert.toString() + ")");
        // thêm ký tực ; vào cuối câu đ�? ngăn cách giữa các câu
        query.append(";");
        // thực thi câu lệnh và trả ra ngoài
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public boolean Update(String tableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {

        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        for (String key : ColumnValues.keySet()) {
            query.append(key + " = '" + ColumnValues.get(key).toString() + "',");
        }
        query = query.delete(query.length() - 1, query.length());
        this.AddCondition(query, Condition);
        query.append(";");
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public boolean UpdateNoComma(String tableName, HashMap<String, Object> ColumnValues, String Condition) throws Exception {

        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        for (String key : ColumnValues.keySet()) {
            query.append(key + " = " + ColumnValues.get(key).toString() + ",");
        }
        query = query.delete(query.length() - 1, query.length());
        this.AddCondition(query, Condition);
        query.append(";");
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public boolean DeleteReal(String tableName, String condition) throws Exception {
        //khai báo biến StringBuilder để tạo chuỗi 
        StringBuilder query = new StringBuilder("DELETE FROM " + tableName + " ");
        this.AddCondition(query, condition);
        query.append(";");
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public boolean Delete(String tableName, String condition) throws Exception {
        // khai báo biến StringBuilder để tạo chuỗi
        StringBuilder query = new StringBuilder("UPDATE  " + tableName + " SET ");
        query.append("trangthai='0' ");
        this.AddCondition(query, condition);
        query.append(";");
        return this.connect.executeUpdate(query.toString()) > 0;
    }

    public void Close() throws Exception {
        this.connect.Close();
    }
    


}
