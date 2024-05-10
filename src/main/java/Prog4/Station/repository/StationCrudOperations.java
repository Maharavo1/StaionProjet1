package Prog4.Station.repository;

import Prog4.Station.db.DBConnection;
import Prog4.Station.model.Station;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StationCrudOperations implements CrudOperations<Station>{
    @Override
    public List<Station> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM station";
            resultSet = statement.executeQuery(sql);

            List<Station> stations = new ArrayList<>();

            while (resultSet.next()) {
                 Station station = new Station(
                         resultSet.getInt("station_id"),
                         resultSet.getString("location")
                 );
                 stations.add(station);

            }
            return stations;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Station findById(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM station WHERE station_id=" + id;
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                Station station = new Station(
                        resultSet.getInt("station_id"),

                        resultSet.getString("location")
                );
                return station;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void save(Station toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "INSERT INTO station (station_id,  location) VALUES ('" +
                    toSave.getStationId() + "','" +
                    toSave.getLocation() + "')";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void saveAll(List<Station> toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            for (Station station : toSave) {

                String sql = "INSERT INTO station (station_id,coordinates , location) VALUES ('" +
                        station.getStationId() + "','" +
                        station.getLocation() + "')";

                statement.executeQuery(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Station toUpdate) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "UPDATE station SET location='" + toUpdate.getLocation()  + "' WHERE station_id='" + toUpdate.getStationId() + "'";
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "DELETE FROM station WHERE station_id='" + id + "'";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
