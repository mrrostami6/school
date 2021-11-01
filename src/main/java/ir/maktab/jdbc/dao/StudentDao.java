package ir.maktab.jdbc.dao;

import ir.maktab.jdbc.config.DataSourceConfig;
import ir.maktab.jdbc.dao.core.BaseDao;
import ir.maktab.jdbc.dao.core.UpdateType;
import ir.maktab.jdbc.entity.Major;
import ir.maktab.jdbc.entity.Student;
import ir.maktab.jdbc.exception.DataNotFoundException;
import ir.maktab.jdbc.exception.ModificationDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements BaseDao<Student, Integer> {

    private static final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    @Override
    public void save(Student entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO student " +
                     "(name, family_name, major_id) " +
                     "VALUES(?, ?, ?)")) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getFamilyName());
            ps.setInt(3, entity.getMajor().getId());
            int rows = ps.executeUpdate();
            checkUpdate(rows, UpdateType.SAVE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to db");
        }
    }

    @Override
    public void update(Integer id, Student newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE student " +
                     "SET name=?, family_name=?, major_id = ? " +
                     "WHERE student_id=?" )) {
            ps.setString(1, newEntity.getName());
            ps.setString(2, newEntity.getFamilyName());
            ps.setInt(3, newEntity.getMajor().getId());
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            checkUpdate(rows, UpdateType.UPDATE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data from db");
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student " +
                     "WHERE student_id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public Student loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                     "FROM school.student WHERE student_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Student student = null;
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("student_id");
                    String name = resultSet.getString("name");
                    String familyName = resultSet.getString("family_name");
                    int majorId = resultSet.getInt("major_id");
                    student = Student.builder()
                            .id(studentId)
                            .name(name)
                            .familyName(familyName)
                            .major(new Major(majorId))
                            .build();
                }
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Student> loadAll() {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT *" +
                     " FROM student");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String familyName = resultSet.getString("family_name");
                int majorId = resultSet.getInt("major_id");
                Student student = Student.builder()
                        .id(studentId)
                        .name(name)
                        .familyName(familyName)
                        .major(new Major(majorId))
                        .build();
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }
}
