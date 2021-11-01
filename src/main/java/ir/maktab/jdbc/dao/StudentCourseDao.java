package ir.maktab.jdbc.dao;

import ir.maktab.jdbc.config.DataSourceConfig;
import ir.maktab.jdbc.dao.core.BaseDao;
import ir.maktab.jdbc.dao.core.UpdateType;
import ir.maktab.jdbc.entity.Course;
import ir.maktab.jdbc.entity.Student;
import ir.maktab.jdbc.entity.StudentCourse;
import ir.maktab.jdbc.exception.DataNotFoundException;
import ir.maktab.jdbc.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao implements BaseDao<StudentCourse, Integer> {
    private static final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();


    @Override
    public void save(StudentCourse entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO school.student_course (student_id, course_id) VALUES(?, ?)")) {
            preparedStatement.setInt(1, entity.getStudentId());
            preparedStatement.setInt(2, entity.getCourseId());
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.SAVE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to database...");
        }
    }

    @Override
    public void update(Integer id, StudentCourse newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE school.student_course SET student_id = ? , course_id = ? WHERE student_id=?")) {
            preparedStatement.setInt(1, newEntity.getStudentId());
            preparedStatement.setInt(2, newEntity.getCourseId());
            preparedStatement.setInt(3, id);
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.UPDATE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Cannot Update Row from DB...");
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM school.student_course WHERE student_id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public StudentCourse loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                     "FROM school.student_course WHERE student_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                StudentCourse studentCourse = null;
                while (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    int studentId = resultSet.getInt("student_id");
                    studentCourse = new StudentCourse(studentId, courseId);
                }

                return studentCourse;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<StudentCourse> loadAll() {
        List<StudentCourse> studentCourses = new ArrayList<>();
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM school.course")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    int studentId = resultSet.getInt("student_id");
                    studentCourses.add(new StudentCourse(studentId, courseId));
                }
                return studentCourses;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }
}

