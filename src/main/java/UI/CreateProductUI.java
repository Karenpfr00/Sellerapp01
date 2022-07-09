package UI;

import models.Product;
import repository.ProductsDB;

import java.util.Scanner;

public class CreateProductUI implements IUserInterface {
    ProductsDB productsDB = new ProductsDB();

    @Override
    public void show() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------CRIANDO NOVO PRODUTO-------");

        System.out.println("Informe a descrição do produto: ");
        String description = scanner.nextLine();

        System.out.println("Informe um ID do produto: ");
        String id = scanner.nextLine();

        System.out.println("Informe o preço: ");
        double price = scanner.nextDouble();

        Product newProduct = new Product(id, description, price);
        productsDB.addProduct(newProduct);
    }
}
