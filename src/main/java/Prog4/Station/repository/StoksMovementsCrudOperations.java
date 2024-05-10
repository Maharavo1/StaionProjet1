package Prog4.Station.repository;


import Prog4.Station.db.DBConnection;
import Prog4.Station.model.StockMovements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StoksMovementsCrudOperations implements CrudOperations<StockMovements>{
    @Override
    public List<StockMovements> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM stock_movements";
            resultSet = statement.executeQuery(sql);

            List<StockMovements> stockMovements = new ArrayList<>();

            while (resultSet.next()) {
                 StockMovements stockMovement = new StockMovements(
                         resultSet.getInt("stock_movement_id"),
                resultSet.getInt("stock_id"),
                resultSet.getInt("station_id"),
                resultSet.getString("type"),
                resultSet.getInt("quantity"),
               resultSet.getTimestamp("datetime").toInstant()
                 );
                stockMovements.add(stockMovement);
            }
            return stockMovements;

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
    public StockMovements findById(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM stock_movements WHERE stock_movement_id=" + id;
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()){
                StockMovements stockMovement = new StockMovements(
                        resultSet.getInt("stock_movement_id"),
                        resultSet.getInt("stock_id"),
                        resultSet.getInt("station_id"),
                        resultSet.getString("type"),
                        resultSet.getInt("quantity"),
                        resultSet.getTimestamp("datetime").toInstant()
                );
                return stockMovement;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public void save(StockMovements toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "INSERT INTO stock_movements (stock_movement_id, stock_id, station_id, type, quantity, datetime) VALUES ('" +
                    toSave.getStockMovementId() + "','" +
                    toSave.getStockId() + "','" +
                    toSave.getStationId() + "','" +
                    toSave.getType() + "','" +
                    toSave.getQuantity() + "','" +
                    java.sql.Timestamp.from(toSave.getDatetime()) + "')";

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
    public void saveAll(List<StockMovements> toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            for (StockMovements stockMovement : toSave) {

                String sql = "INSERT INTO stock_movements (stock_movement_id,stock_id, station_id, type, quantity, datetime) VALUES ('" +
                        stockMovement.getStockMovementId() + "','" +
                        stockMovement.getStockId() + "','" +
                        stockMovement.getStationId() + "','" +
                        stockMovement.getType() + "','" +
                        stockMovement.getQuantity() + "','" +
                        java.sql.Timestamp.from(stockMovement.getDatetime()) + "')";

                statement.addBatch(sql);
            }
            statement.executeBatch();
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
    public void update(StockMovements toUpdate) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "UPDATE product SET stock_id='" + toUpdate.getStockId() + "', " +
                    "station_id='" + toUpdate.getStationId() + "' " +
                    "type='" + toUpdate.getType() + "' " +
                    "quantity='" + toUpdate.getQuantity() + "' " +
                    "datetime='" + toUpdate.getDatetime() + "' " +
                    "WHERE stock_movement_id='" + toUpdate.getStockMovementId() + "'";

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

            String sql = "DELETE FROM product WHERE stock_movement_id='" + id + "'";
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
