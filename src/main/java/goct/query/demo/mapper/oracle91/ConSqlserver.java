package goct.query.demo.mapper.oracle91;

import java.sql.*;

public class ConSqlserver {/*

    public static void main(String[] args) {
        String url="jdbc:sqlserver://10.1.0.122:1433;DatabaseName=ePortal";
        String user="eportal";//sa超级管理员
        String password="eportal!@#";//密码
        // TODO Auto-generated method stub
        PreparedStatement ps=null;      //(这里也可以使用statement,视情况而定)
        Connection con=null;
        ResultSet rs=null;
        Statement stmt= null;
        CallableStatement cs= null;

        try {

            //1.加载驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //2.连接
            con= DriverManager.getConnection( url,user,password);
            String sql= "SELECT TOP 1000 [DocumentType],[Description]FROM [ePortal].[Inspection].[DocumentType] where documentType= ?";
            ps=con.prepareStatement(sql);
            ps.setString(1,"1");
            rs= ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("Description"));
            }

        }
     catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }finally{

        //关闭资源,加强程序的健壮性
        try {
            if(rs!=null){
                rs.close();
            }
            if(ps!=null){
                ps.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}*/}


