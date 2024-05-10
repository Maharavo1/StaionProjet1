package Prog4.Station.repository;

import Prog4.Station.db.DBConnection;
import Prog4.Station.model.ProductTemplate;
import Prog4.Station.model.Station;
import Prog4.Station.model.Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockCrudOperations implements CrudOperations<Stock> {


    @Override
    public List<Stock> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM stock";
            resultSet = statement.executeQuery(sql);

            List<Stock> listStock = new ArrayList<>();
            while (resultSet.next()) {
                Stock stock = new Stock(
                        resultSet.getInt("stock_id"),
                        resultSet.getInt("station_id"),
                        resultSet.getInt("product_template_id"),
                        resultSet.getTimestamp("datetime").toInstant(),
                        resultSet.getInt("quantity")
                );
                listStock.add(stock);
            }
            return listStock;


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
    public Stock findById(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM stock WHERE stock_id=" + id;
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                Stock stock = new Stock(
                        resultSet.getInt("stock_id"),
                        resultSet.getInt("station_id"),
                        resultSet.getInt("product_template_id"),
                        resultSet.getTimestamp("datetime").toInstant(),
                        resultSet.getInt("quantity")
                );
                return stock;
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
    public void save(Stock toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "INSERT INTO stock (stock_id, station_id, product_template_id, datetime, quantity) VALUES ('" +
                    toSave.getStockId() + "','" +
                    toSave.getStationId() + "','" +
                    toSave.getProductTemplateId() + "','" +
                    toSave.getDatetime() + "','" +
                    toSave.getQuantity()+ "')";

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
    public void saveAll(List<Stock> toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            for (Stock product : toSave) {

                String sql = "INSERT INTO stock (stock_id, station_id, product_template_id, datetime, quantity) VALUES ('" +
                        product.getStockId() + "','" +
                        product.getStationId() + "','" +
                        product.getProductTemplateId() + "','" +
                        product.getDatetime() + "','" +
                        product.getQuantity()+ "')";

                statement.executeUpdate(sql);
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
    public void update(Stock toUpdate) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "UPDATE product SET station_id='" + toUpdate.getStationId() + "', " +
                    "product_template_id='" + toUpdate.getProductTemplateId() + "' " +
                    "datetime='" + toUpdate.getDatetime() + "' " +
                    "quantity='" + toUpdate.getQuantity() + "' " +
                    "WHERE stock='" + toUpdate.getStockId() + "'";

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

            String sql = "DELETE FROM product WHERE stock_id='" + id + "'";
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

