package util;

import java.sql.*;

public class jdbcUtil {
//    static {
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    private static final String URL="jdbc:mysql://localhost:3306/s_t?useUnicode=true&characterEncoding=utf8";
    private static final String USER="root";
    private static final String PWD="0415";

    public static Connection getConn() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL,USER,PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static <T> T executeOne(String sql,RowMap<T> rowMap,Object...objs) {
        T t = null;
        Connection conn = getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement(sql);
            if(objs!=null){
                for (int i = 0; i < objs.length; i++) {
                    pstmt.setObject(i+1,objs[i]);
                }
            }
            rs = pstmt.executeQuery();

            while(rs.next()) {
                t = rowMap.rowMapping(rs);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,pstmt,rs);
        }
        return t;
    }
    public static int executeUpdate(String sql,Object... objs) {
        Connection conn = getConn();
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i =0;i<objs.length;i++) {
                pstmt.setObject(i+1,objs[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(conn,pstmt,null);
        }
        return result;
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try{
            if(conn!=null) {
                conn.close();
            }
            if(pstmt!=null) {
                pstmt.close();
            }
            if(rs!=null) {
                rs.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
