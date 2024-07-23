package org.iweb.tset1;




import org.iweb.pojo.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiwei
 * @date 2024/7/22 下午2:21
 */
public class Test {
    public static void main(String[] args) {
/*
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("驱动加载成功");

        String url = "jdbc:mysql://localhost:3306/nuist?characterEncoding=utf8";
        String user = "root";
        String password = "a12345";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("获取连接成功");


        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO student (NAME,gender,birthday,addr,qqnumber) VALUES ('dafwfwf','男','2003-2-15','jiangsu',123456);";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
/*        Student s1 = new Student(0,"zhangsan","男",new Date(),"nanjing", 110L);
        StudentDAO studentDAO = new StudentDAOimpl();
        // studentDAO.insertStudent(s1);
        Student s2 = new Student(6,"fhuwf","女",new Date(),"nanjing", 110L);
        studentDAO.updateStudent(s2);*/
        List<Student> list = new ArrayList<Student>();
        System.out.println(list);

    }
}
