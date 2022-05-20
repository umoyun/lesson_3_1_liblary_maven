package com.company.repository;

import com.company.enums.StudentRole;
import com.company.enums.StudentStatus;
import com.company.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    @Autowired
    public DataSource dataSource;
    public boolean isPhoneExists(String phone) {
        try {
            Connection con = DBConnection.getConnection();

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select phone from student where phone = '" + phone + "'");
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public void save_student(Student student) {
        try {

            Connection con = DBConnection.getConnection();
            String sql = "Insert into student(name,surname,phone,password,role,status) " +
                    "values(?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getPassword());

            statement.setString(5, "USER");
            statement.setString(6, "ACTIVE");

            statement.execute();
            System.out.println("Success.");
            con.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public Student auth2(String phone, String password){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        String sql = "SELECT * FROM student where phone = '"
                + phone + "' and password = '" + password + "';";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        System.out.println();

        return null;

    }
//    public Student auth(String phone, String password) {
//        try {
//            Connection con = DBConnection.getConnection();
//
//            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("Select * from student where phone = '" + phone +
//                    "' and password ='" + password + "'");
//            if (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String surname = resultSet.getString("surname");
//                String status = resultSet.getString("status");
//                String role = resultSet.getString("role");
//
//                Student student = new Student();
//                student.setName(name);
//                student.setSurname(surname);
//                student.setPassword(password);
//                student.setPhone(phone);
//                student.setRole(StudentRole.valueOf(role));
//                student.setStatus(StudentStatus.valueOf(status));
//                con.close();
//                return student;
//            }
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return null;
//    }

    public List<Student> selectAll() {
        List<Student> studentList = new LinkedList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "select * from student";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));

                student.setSurname(resultSet.getString("surname"));
                student.setPhone(resultSet.getString("phone"));
                student.setPassword(resultSet.getString("password"));
                student.setStatus(StudentStatus.valueOf(resultSet.getString("status")));
                student.setRole(StudentRole.valueOf(resultSet.getString("role")));
                studentList.add(student);
            }

            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return studentList;
    }
    public Student getStudentByPhone(String phone) {
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from student where phone='" + phone + "'");
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));

                student.setSurname(resultSet.getString("surname"));
                student.setPhone(resultSet.getString("phone"));
                student.setPassword(resultSet.getString("password"));
                student.setStatus(StudentStatus.valueOf(resultSet.getString("status")));
                student.setRole(StudentRole.valueOf(resultSet.getString("role")));
                return student;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    public void changeStatus(Integer id, StudentStatus status) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "update student set status=? where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status.name());
            statement.setInt(2, id);

            statement.execute();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
