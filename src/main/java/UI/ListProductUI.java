package UI;

import models.Product;
import repository.ProductsDB;

public class ListProductUI implements IUserInterface {
    ProductsDB productsDB = new ProductsDB();


    public void show() {

        System.out.println("---------------LISTANDO PRODUTOS---------");
        for (Product product : productsDB.getProduct()) {
            System.out.println("------------------------------------------");
            System.out.println("ID: " + product.getId());
            System.out.println("Descrição: " + product.getDescription());
            System.out.println("Preço: " + product.getPrice());
            System.out.println("-------------------------------------");

        }
    }
}
