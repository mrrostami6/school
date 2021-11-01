package ir.maktab.jdbc.dao;

import ir.maktab.jdbc.config.DataSourceConfig;
import ir.maktab.jdbc.dao.core.BaseDao;
import ir.maktab.jdbc.dao.core.UpdateType;
import ir.maktab.jdbc.entity.Course;
import ir.maktab.jdbc.entity.Major;
import ir.maktab.jdbc.exception.DataNotFoundException;
import ir.maktab.jdbc.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDao implements BaseDao<Course, Integer> {
    private static final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    @Override
    public void save(Course entity) {

        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO school.course (name, unit) VALUES(?, ?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getUnit());
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.SAVE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to database...");
        }
    }

    @Override
    public void update(Integer id, Course newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE school.course SET name=?, unit=? WHERE course_id=?")) {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setInt(2, newEntity.getUnit());
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
                     "DELETE FROM school.course WHERE course_id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public Course loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                     "FROM school.course WHERE course_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Course course = null;
                while (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    String name = resultSet.getString("name");
                    int unit = resultSet.getInt("unit");
                    course = new Course(courseId, name, unit);
                }
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Course> loadAll() {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM school.course")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    String name = resultSet.getString("name");
                    int unit = resultSet.getInt("unit");
                    courses.add(new Course(courseId, name, unit));
                }
                return courses;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

}
