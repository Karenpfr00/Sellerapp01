package UI;

import models.Product;
import repository.ProductsDB;

import java.util.Scanner;

public class FindProductUI implements IUserInterface {
    ProductsDB productsDB = new ProductsDB();

    @Override
    public void show() {
        System.out.println("-------OBTENDO DADOS DE PRODUTO--------");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe um ID do produto: ");
        String id = scanner.nextLine();

        Product product = productsDB.getProductById(id);

        System.out.println("ID: " + product.getId());
        System.out.println("Descrição: " + product.getDescription());
        System.out.println("Preço: " + product.getPrice());
        System.out.println("-----------------------------------------");

    }
}
