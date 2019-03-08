package mysqltest;

import java.sql.*;

public class Mysqltest {
    	// JDBC 驱动名及数据库 URL
        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
        static final String DB_URL = "jdbc:mysql://localhost:3306/db_test?useSSL=false&serverTimezone=UTC";
     
        // 数据库的用户名与密码，需要根据自己的设置
        static final String USER = "root";
        static final String PASS = "wt123";
     
        public static void main(String[] args) throws Exception {
            Connection conn = null;
            Statement stmt = null;
            try{
                // 注册 JDBC 驱动
                Class.forName(JDBC_DRIVER);
            
                // 打开链接
                System.out.println("连接数据库...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
                // 执行查询
                System.out.println(" 实例化Statement对象...");
                stmt = conn.createStatement();
//                String sql="insert into tb2 values('Bob','4321','Shanghai','Y')";
                String sql = "UPDATE tb2 SET name=\"Bob\" WHERE phone=\"123\"";
                stmt.executeUpdate(sql);
                sql = "SELECT name,phone,address,marriage FROM tb2";
                ResultSet rs = stmt.executeQuery(sql);
            
                // 展开结果集数据库
                while(rs.next()){
                    // 通过字段检索
                	String name = rs.getString("name");
                	String phone = rs.getString("phone");
                	String address = rs.getString("address");
                	String marriage = rs.getString("marriage");
                	System.out.println(name);
                	System.out.println(phone);
                	System.out.println(address);
                	System.out.println(marriage); 	
                }
                // 完成后关闭
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                // 处理 JDBC 错误
                se.printStackTrace();
            }catch(Exception e){
                // 处理 Class.forName 错误
                e.printStackTrace();
            }finally{
                // 关闭资源
                try{
                    if(stmt!=null) stmt.close();
                }catch(SQLException se2){
                }// 什么都不做
                try{
                    if(conn!=null) conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            System.out.println("Goodbye!");
        }
}