package repositorio;

import models.Product;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class ProdutosDB {
    private List<Product> products;

    public ProdutosDB() {
        products = new ArrayList<>();
    }

    public void addProduto (Product product){
        String query = "INSERT INTO product(id, descricao, preco) VALUES (\"" + product.getId() + "\",\"" + product.getDescricao()+ "\"," + product.getPreco() + ");";

        try {
            Class.forName(("com.mysql.cj.jdbc.Driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/sellerapp" , "root" , "Konohamaru@101" );
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, product.getId());
            statement.setString(2, product.getDescricao());
            statement.setDouble(3, product.getPreco());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Product getProdutoById (int produtoId) {
        for(Product product : products){
            if(product.getId().equals(produtoId));
            return product;

        }
        return null;
    }

    public List<Product> getProdutos() {
        List<Product> persistedProducts = new ArrayList<>();

        String query = "SELECT * FROM product;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection( "jdbc:mysql://localhost/sellerapp" , "root" , "Konohamaru@101");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String descricao = resultSet.getString("descricao");
                double preco = resultSet.getDouble("preco");

                Product product = new Product(id, descricao, preco);
                persistedProducts.add(product);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return persistedProducts;
    }


}