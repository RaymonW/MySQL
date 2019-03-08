package mysqltest;

import java.sql.*;

public class Mysqltest {
    	// JDBC �����������ݿ� URL
        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
        static final String DB_URL = "jdbc:mysql://localhost:3306/db_test?useSSL=false&serverTimezone=UTC";
     
        // ���ݿ���û��������룬��Ҫ�����Լ�������
        static final String USER = "root";
        static final String PASS = "wt123";
     
        public static void main(String[] args) throws Exception {
            Connection conn = null;
            Statement stmt = null;
            try{
                // ע�� JDBC ����
                Class.forName(JDBC_DRIVER);
            
                // ������
                System.out.println("�������ݿ�...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
                // ִ�в�ѯ
                System.out.println(" ʵ����Statement����...");
                stmt = conn.createStatement();
//                String sql="insert into tb2 values('Bob','4321','Shanghai','Y')";
                String sql = "UPDATE tb2 SET name=\"Bob\" WHERE phone=\"123\"";
                stmt.executeUpdate(sql);
                sql = "SELECT name,phone,address,marriage FROM tb2";
                ResultSet rs = stmt.executeQuery(sql);
            
                // չ����������ݿ�
                while(rs.next()){
                    // ͨ���ֶμ���
                	String name = rs.getString("name");
                	String phone = rs.getString("phone");
                	String address = rs.getString("address");
                	String marriage = rs.getString("marriage");
                	System.out.println(name);
                	System.out.println(phone);
                	System.out.println(address);
                	System.out.println(marriage); 	
                }
                // ��ɺ�ر�
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                // ���� JDBC ����
                se.printStackTrace();
            }catch(Exception e){
                // ���� Class.forName ����
                e.printStackTrace();
            }finally{
                // �ر���Դ
                try{
                    if(stmt!=null) stmt.close();
                }catch(SQLException se2){
                }// ʲô������
                try{
                    if(conn!=null) conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            System.out.println("Goodbye!");
        }
}