package Prog4.Station.utils;

import Prog4.Station.db.DBConnection;

import java.sql.*;


public class Price {
    public static double getPriceById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        double price = 0.0;
        try {
            String sql = "SELECT price FROM ProductTemplate INNER JOIN Stock ON ProductTemplate.product_template_id = Stock.product_template_id WHERE stock_id = ?";
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                price = resultSet.getDouble("price");
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
        return price;
    }
}
