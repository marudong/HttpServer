package team.ml.mysql;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  
  
public class DBHelper {  
      
    //mysql数据库驱动，固定写法。连接Oracle时又与之不同,为："oracle.jdbc.driver.OracleDriver"  
    private static final String driver = "com.mysql.jdbc.Driver";   
  
    /** 
    * 如下是连接数据库的URL地址， 
    * 其中，"jdbc:mysql://"   为固定写法 
    * "localhost"是连接本机数据库时的写法，当不是连接本机数据库时，要写数据库所在计算机的IP地址。如：172.26.132.253 
    * "shopping"是数据库的名称，一定要根据自己的数据库更改。 
    * "?useUnicode=true&characterEncoding=UTF-8" 指定编码格式，无需时可省略， 
    * 即地址直接为："jdbc:mysql://localhost:3306/shopping" 
    */  
    private static final String url="jdbc:mysql://localhost:3306/ZhongLi?useUnicode=true&characterEncoding=UTF-8";   
      
    private static final String username="root";//数据库的用户名  
    private static final String password="root";//数据库的密码:这个是自己安装数据库的时候设置的，每个人不同。  
         //声明数据库连接对象  
      
    //静态代码块负责加载驱动  
    static   
    {  
        try  
        {  
            Class.forName(driver);  
        }  
        catch(Exception ex)  
        {  
            ex.printStackTrace();  
        }  
    }  
      
    //单例模式返回数据库连接对象，供外部调用  
    public static Connection getConnection() throws Exception  
    {  
    	Connection conn=null;
        if(conn==null)  
        {  
            conn = DriverManager.getConnection(url, username, password); //连接数据库  
            return conn;  
        }  
        return conn;  
    }  
      
    
    //查询数据库数据
    public ResultSet getRS(Connection conn, String sql) throws SQLException
    {
        Statement s;
        s = conn.createStatement();
        return s.executeQuery(sql);

    }
    
    //插入数据
    public void DoInsert(Connection conn, String sql )
    {
        Statement s;
        try {
            s = conn.createStatement();
            s.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    
    public void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
   
}  