package UI;

import models.Customer;
import models.Order;
import models.Product;
import repository.CustomerDB;
import repository.OrderDB;
import repository.ProductsDB;

import java.util.Scanner;

public class CreateOrderUI implements IUserInterface {
    OrderDB orderDB = new OrderDB();
    CustomerDB customerDB = new CustomerDB();
    ProductsDB productsDB = new ProductsDB();

    @Override
    public void show() {
        System.out.println("Pedido de Venda");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o ID do pedido: ");
        String id = scanner.nextLine();

        System.out.print("Informe o ID do produto: ");
        String productId = scanner.nextLine();

        System.out.print("Informe o ID do cliente: ");
        String customerId = scanner.nextLine();

        System.out.println("Informe a quantidade: ");
        int quantity = scanner.nextInt();

        Customer customer = customerDB.getCustomerById(customerId);
        Product product = productsDB.getProductById(productId);

        Order order = new Order(id, customer,product, quantity);
    }
}
