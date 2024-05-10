package Prog4.Station.utils;

import Prog4.Station.db.DBConnection;

import Prog4.Station.model.Stock;
import Prog4.Station.model.StockMovements;

import Prog4.Station.repository.StockCrudOperations;

import Prog4.Station.repository.StoksMovementsCrudOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Evaporation {
    private final StockCrudOperations stockCrudOperations;
   private final StoksMovementsCrudOperations stoksMovementsCrudOperations;

    @Autowired
    public Evaporation(StockCrudOperations stockCrudOperations, StoksMovementsCrudOperations stoksMovementsCrudOperations) {
        this.stockCrudOperations = stockCrudOperations;
        this.stoksMovementsCrudOperations = stoksMovementsCrudOperations;

    }

    public static LocalDateTime getLastMovement() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        LocalDateTime lastMovementDate = null;

        try {
            String sql = "SELECT MAX(datetime) AS last_movement_date FROM StockMovement";
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                lastMovementDate = resultSet.getTimestamp("last_movement_date").toLocalDateTime();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return lastMovementDate;
    }

    public static String getNameProduct(int stockId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String productName = null;

        try {
            String sql = "SELECT ProductTemplate.name FROM ProductTemplate INNER JOIN" +
                    " Stock ON ProductTemplate.product_template_id = Stock.product_template_id" +
                    " WHERE stock_id = ?";
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, stockId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                productName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error closing database  connection", e);
            }
        }

        return productName;
    }

    public static int calculateDaysDifference(LocalDateTime startDate, LocalDateTime endDate) {
        Duration duration = Duration.between(startDate, endDate);
        return (int) duration.toDays();
    }

    public static double operationOfEvaporation(LocalDateTime last, LocalDateTime inDate,
                                                String productName) {
        int daysEvaporation = calculateDaysDifference(last, inDate);
        double valueEvaporation = 0.0;
        if (productName.equals("Oil")) {
            valueEvaporation = (daysEvaporation * 10);
        } else if (productName.equals("Diesel") || productName.equals("Gasoline")) {
            valueEvaporation = (daysEvaporation * 100);
        }
        return valueEvaporation;
    }

    public double evaporationByDay(StockMovements stockMovements) throws SQLException {
        Stock stock = stockCrudOperations.findById(stockMovements.getStockId());
        LocalDateTime lastDate = getLastMovement();
        LocalDateTime applyEvaporationDate = LocalDateTime.from(stockMovements.getDatetime());
        String nameProduct = getNameProduct(stock.getStockId());
        double evaporation = operationOfEvaporation(lastDate, applyEvaporationDate, nameProduct);
        double value = 0.0;
        if (stock.getQuantity() < evaporation || stock.getQuantity() < 0) {
            value = 0;
        } else {
            value = (stock.getQuantity() - evaporation);
        }
        return value;
    }
}
