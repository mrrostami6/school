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

public class MajorDao implements BaseDao<Major, Integer> {
    private static final DataSourceConfig dataSourceConfig = DataSourceConfig.getInstance();

    @Override
    public void save(Major entity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO school.major (name) VALUES(?) ")) {
            preparedStatement.setString(1, entity.getName());
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.SAVE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not insert data to database...");
        }
    }

    @Override
    public void update(Integer id, Major newEntity) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE school.major SET name=? WHERE major_id=?")) {
            preparedStatement.setString(1, newEntity.getName());
            preparedStatement.setInt(2, id);
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM school.major" +
                     " WHERE major_id=?")) {
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            checkUpdate(rows, UpdateType.DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModificationDataException("Can not update data to db");
        }
    }

    @Override
    public Major loadById(Integer id) {
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                     "FROM school.major WHERE major_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Major major = null;
                while (resultSet.next()) {
                    int majorId = resultSet.getInt("major_id");
                    String name = resultSet.getString("name");
                    major = new Major(majorId, name);
                }
                return major;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }

    @Override
    public List<Major> loadAll() {
        List<Major> majors = new ArrayList<>();
        try (Connection connection = dataSourceConfig.createDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                     "FROM school.major ")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int majorId = resultSet.getInt("major_id");
                    String name = resultSet.getString("name");
                    majors.add(new Major(majorId, name));
                }
                return majors;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataNotFoundException("Can not find data from db");
        }
    }
}
