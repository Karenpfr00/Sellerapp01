package repository;

import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDB {
    private List<Product> products;

    public ProductsDB() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        String query = "INSERT INTO product(id, description, price) VALUES (?, ?, ?);";


        try {
            Connection connection = Configuration.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, product.getId());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(String productId) {
        String query = "INSERT INTO product(id, description, price) VALUES (?, ?, ?);";

        try {
            Connection connection = Configuration.getConnection();

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");

                return new Product(id, description, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Product> getProduct() {
        List<Product> persistedProducts = new ArrayList<>();

        String query = "SELECT * FROM product;";

        try {
            Connection connection = Configuration.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");

                Product product = new Product(id, description, price);
                persistedProducts.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persistedProducts;
    }
}