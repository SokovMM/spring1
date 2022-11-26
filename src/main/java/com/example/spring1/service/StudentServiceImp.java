package com.example.spring1.service;

import com.example.spring1.model.Student;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentServiceImp implements StudentService {

    // Хранилище учеников
    private static final Map<Integer, Student> STUDENT_REPOSITORY_MAP = new HashMap<>();

    // Переменная для генерации ID учеников
    private static final AtomicInteger STUDENT_ID_HOLDER = new AtomicInteger();


    private static final String URL = "jdbc:postgresql://localhost:5432/test1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static int PEOPLE_COUNT;
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void create(Student student) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO table1 VALUES (?,?)");

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Student> readGroup(String name_studnet) {
        //ArrayList <Student> student = new ArrayList <Student>();
        List<Student> students = new ArrayList<>();
        //Student student = null;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM table1 WHERE fio=?");
            preparedStatement.setString(1,name_studnet);

            ResultSet resultSet = preparedStatement.executeQuery();


            //
            resultSet.next();



            while (resultSet.next()) {
                Student student = new Student();

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("fio"));
                student.setEmail("ddd@34e.ru");
                students.add(student);

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return students;
    }

    @Override
    public List<Student> readAll() {

        List<Student> students = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM table1";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Student student = new Student();

                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("fio"));
                student.setEmail("ddd@34e.ru");
                students.add(student);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return students;

    }



    @Override
    public Student read_by_id(int id) {
        Student student = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM table1 WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("fio"));
                student.setEmail("ddd@34e.ru");
            }


            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;

    }

    @Override
    public boolean update(Student student, int id) {
        return false;
    }
    /*public Student read2(String id_student) {
        Student student = null;


        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM table1 WHERE id=?");
            preparedStatement.setInt(1, id_student);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student = new Student();

            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("fio"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;

    }


    @Override
    public boolean update(Student student, int id) {
        return false;
    }
*/

    @Override
    public boolean delete(int id) {
        Integer kaif = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM table1 WHERE Id=?");

            preparedStatement.setInt(1, id);

            kaif = preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return kaif>0;
    }

    @Override
    public Student read2(String id_student) {
        return null;
    }


}




