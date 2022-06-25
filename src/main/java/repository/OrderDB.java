package repository;

import  models.Customer;
import models.Product;
import models.Order;

import java.sql. *;
import java.util.ArrayList;
import java.util.List;

public class OrderDB {
    public void addOrder(Order order) {
        String query = "INSERT INTO orders(id, customer_id, product_id, quantity) VALUES (?, ?, ?, ?)";

        try {
            Connection connection = Configuration.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, order.getId());
            statement.setString(2, order.getCustomer().getId());
            statement.setString(3, order.getProduct().getId());
            statement.setInt(4, order.getQuantity());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders(){
        String query = "SELECT * FROM orders;";

        List<Order> orders = new ArrayList<>();

        try {
            Connection connection = Configuration.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                String id = resultSet.getString("id");
                String customerID = resultSet.getString("customer_id");
                String productID = resultSet.getString("product_id");
                int quantity = resultSet.getInt("quantity");

                Customer customer = new CustomerDB().getCustomerById(customerID);
                Product product = new ProductsDB().getProductById(productID);

                Order order = new Order(id, customer, product, quantity);
                orders.add(order);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

}
