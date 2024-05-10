package Prog4.Station.repository;
import Prog4.Station.db.DBConnection;
import Prog4.Station.model.ProductTemplate;
import Prog4.Station.model.Stock;
import Prog4.Station.model.StockMovements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductTemplateCrudOperations implements CrudOperations<ProductTemplate> {



    @Override
    public List<ProductTemplate> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM product_template";
            resultSet = statement.executeQuery(sql);

            List<ProductTemplate> productTemplates = new ArrayList<>();

            while (resultSet.next()) {
                ProductTemplate productTemplate = new ProductTemplate(
                        resultSet.getInt("product_template_id"),
                        resultSet.getDouble("price"),
                        resultSet.getString("name")

                );
                productTemplates.add(productTemplate);
            }
            return productTemplates;

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
    public ProductTemplate findById(int id) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM product_template WHERE product_template_id=" + id;
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                ProductTemplate productTemplate = new ProductTemplate(
                        resultSet.getInt("product_template_id"),
                        resultSet.getDouble("price"),
                        resultSet.getString("name")

                );
                return productTemplate;
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
    public void save(ProductTemplate toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "INSERT INTO product_template (product_template_id, price, name) VALUES ('" +
                    toSave.getProductTemplateId() + "','" +
                    toSave.getPrice() + "','" +
                    toSave.getName() + "')";

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
    public void saveAll(List<ProductTemplate> toSave) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            for (ProductTemplate productTemplate : toSave) {

                String sql = "INSERT INTO product_template (product_template_id, price, name) VALUES ('" +
                        productTemplate.getProductTemplateId() + "','" +
                        productTemplate.getPrice() + "','" +
                        productTemplate.getName() + "')";

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
    public void update(ProductTemplate toUpdate) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();

            String sql = "UPDATE product_template SET price='" + toUpdate.getPrice() + "', " +
                    "name='" + toUpdate.getName() + "' " +
                    "WHERE product_template_id='" + toUpdate.getProductTemplateId() + "'";

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

            String sql = "DELETE FROM product_template WHERE product_template_id='" + id + "'";
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
