package UI;

import models.Product;
import reports.CSVExporter;
import repository.ProductsDB;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ExportProductsUI implements IUserInterface {
    ProductsDB productsDB = new ProductsDB();

    @Override
    public void show() {
        System.out.println("Exportando dados de produtos...");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Para onde você deseja exportar o arquivo: ");
        String path = scanner.nextLine();

        List<Product> products = productsDB.getProduct();

        try {
            CSVExporter.export(path, products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
