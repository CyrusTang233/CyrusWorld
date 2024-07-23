package org.iweb.dao;




import org.iweb.pojo.Student;
import org.iweb.tset1.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiwei
 * @date 2024/7/22 下午3:52
 */
public class StudentDAOimpl implements StudentDAO{
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void insertStudent(Student student) {
        try (
                Connection connection = DBUtil.getConnection();
                Statement statement = connection.createStatement();
                ){
            String sql = "insert into student(name,gender,birthday,addr,qqnumber)"+
                    "values('%s','%s','%s','%s','%d')";
            sql = String.format(sql, student.getName(), student.getGender(), sdf.format(student.getBirthday()),student.getAddr(),student.getQqnumber());
            statement.execute(sql);
    }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(Integer id) {
        Connection connection = null;
        String sql = "delete from student where id=?";
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "update student set name=?,gender=?,birthday=?,addr=?,qqnumber=? where id=?";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getGender());
            preparedStatement.setDate(3,new Date(student.getBirthday().getTime()));
            preparedStatement.setString(4,student.getAddr());
            preparedStatement.setLong(5,student.getQqnumber());
            preparedStatement.setInt(6,student.getId());
            preparedStatement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer count() {
        String sql = "select count(*) from student";
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Student getStudentById(Integer id) {
        String sql = "select * from student where id=?";
        Student student = null;
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                student = new Student();
                student.setId(id);
                student.setName(resultSet.getString("name"));
                student.setGender(resultSet.getString("gender"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddr(resultSet.getString("addr"));
                student.setQqnumber(resultSet.getLong("qqnumber"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAllStudent() {
        return findWithLimit(0,Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findStudentByNameLike(String studentName) {
        return findStudentByNameLikeWithLimit(studentName,0,Integer.MAX_VALUE);
    }

    @Override
    public List<Student> findStudentByNameLikeWithLimit(String studentName, int start, int limit) {
        String sql = "select * from student where name like concat('%',?,'%') limit ?,?";
        List<Student> list = new ArrayList<>();
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setString(1,studentName);
            preparedStatement.setInt(2,start);
            preparedStatement.setInt(3,limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setGender(resultSet.getString("gender"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddr(resultSet.getString("addr"));
                student.setQqnumber(resultSet.getLong("qqnumber"));
                list.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list.size() == 0 ? null : list;
    }

    @Override
    public List<Student> findWithLimit(int start, int limit) {
        String sql = "select * from student limit ?,?";
        List<Student> list = new ArrayList<Student>();
        try (
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setGender(resultSet.getString("gender"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setAddr(resultSet.getString("addr"));
                student.setQqnumber(resultSet.getLong("qqnumber"));
                list.add(student);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list.size() == 0 ? null : list;
    }
}
