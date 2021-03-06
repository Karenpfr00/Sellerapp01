package repository;

import models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
    public List<Customer> getCustomers() {
        List<Customer> persistedCustomers = new ArrayList<>();

        String query = "SELECT * FROM customers;";

        try {
            Connection connection = Configuration.getConnection("jdbc:mysql://localhost/sellerapp");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");

                Customer customer = new Customer(id, firstName, lastName, address, phoneNumber);
                persistedCustomers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persistedCustomers;
    }

    public Customer getCustomerById(String customerId) {
        String query = "SELECT * FROM customers WHERE id = ?";

        try {
            Connection connection = Configuration.getConnection("jdbc:mysql://localhost/sellerapp");

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customerId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");

                return new Customer(id, firstName, lastName, address, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers(id, first_name, last_name, address, phone_number) VALUES (\" " + customer.getFirstName() + "\",\"" + customer.getLastName() + "\",\"" + customer.getAddress() + "\",\"" + customer.getPhoneNumber() + ");";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sellerapp" , "root" , "Konohamaru@101" );
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, customer.getId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhoneNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}