package FileSellerApp;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import models.Customer;
import models.Supplier;
import reports.CSVExporter;
import repository.Configuration;
import repository.CustomerDB;
import repository.ProductsDB;
import models.Product;
import repository.SupplierDB;

public class SellerApp {
    static ProductsDB productsDb = new ProductsDB();
    static SupplierDB supplierDB = new SupplierDB();
    static CustomerDB customerDB = new CustomerDB();


    public static void main(String... args) {
              System.out.println("Boas Vindas ao Seller App");

            int option;

            do {
                System.out.println("-------MENU PRINCIPAL--------");
                System.out.println("1 - Cadastrar um novo produto");
                System.out.println("2 - Listar todos os produtos cadastrados");
                System.out.println("3 - Obter dados de um produto");
                System.out.println("4 - Criar um novo fornecedor");
                System.out.println("5 - Cadastrar um novo cliente");
                System.out.println("6 - Listar todos os clientes cadastrados");
                System.out.println("7 - Buscar cliente");
                System.out.println("8 - Exportar dados de produtos");
                System.out.println("0 - Sair do programa");

                System.out.println("Escolha uma operação: ");
                Scanner scanner = new Scanner(System.in);
                option = scanner.nextInt();

                process(option);
            } while (option != 0);

            Configuration.closeConnection();

    }

    static void process(int option){
        switch (option){
            case 1:{
                Scanner scanner = new Scanner(System.in);

                System.out.println("Informe a descrição do produto: ");
                String description = scanner.nextLine();

                System.out.println("Informe um ID do produto: ");
                String id = scanner.nextLine();

                System.out.println("Informe o preço: ");
                double price = scanner.nextDouble();

                Product newProduct = new Product(id,description,price);
                productsDb.addProduct(newProduct);

                break;
            }

            case 2: {
                System.out.println("---------------LISTANDO PRODUTOS---------");
                for(Product product : productsDb.getProduct()){
                    System.out.println("ID: " + product.getId());
                    System.out.println("Descrição: " + product.getDescription());
                    System.out.println("Preço: " + product.getPrice());
                    System.out.println("-------------------------------------");

                }
                break;
            }

            case 3: {
                System.out.println("-------OBTENDO DADOS DE PRODUTO--------");
                Scanner scanner = new Scanner(System.in);

                System.out.print("Informe um ID do produto: ");
                String id = scanner.nextLine();

                Product product = productsDb.getProductById(id);

                System.out.println("ID: " + product.getId());
                System.out.println("Descrição: " + product.getDescription());
                System.out.println("Preço: " + product.getPrice());
                System.out.println("-----------------------------------------");

                break;
            }

            case 4: {
                System.out.println("-------CRIANDO NOVO FORNECEDOR-------");

                Scanner scanner = new Scanner(System.in);

                System.out.print("Informe o ID do fornecedor: ");
                String id = scanner.nextLine();

                System.out.print("Informe o nome: ");
                String nome = scanner.nextLine();

                System.out.print("Agora informe o telefone: ");
                String phoneNumber = scanner.nextLine();

                Supplier supplier = new Supplier(id, nome, phoneNumber);
                supplierDB.addSupplier(supplier);

                break;
            }

            case 5: {
                System.out.println("-------CRIANDO NOVO CLIENTE-------");

                Scanner scanner = new Scanner(System.in);

                System.out.print("Informe o identificador do cliente: ");
                String id = scanner.nextLine();

                System.out.print("Informe o primeiro nome do cliente: ");
                String firstName = scanner.nextLine();

                System.out.print("Informe o último nome: ");
                String lastName = scanner.nextLine();

                System.out.print("Informe o endereço (até 100 caracteres): ");
                String address = scanner.nextLine();

                System.out.print("Informe o telefone de contato: ");
                String phoneNumber = scanner.nextLine();

                Customer customer = new Customer(id, firstName, lastName, address, phoneNumber);

                customerDB.addCustomer(customer);

                break;
            }

            case 6: {
                List<Customer> customers = customerDB.getCustomers();

                System.out.println("------LISTANDO CLIENTES CADASTRADOS------");
                for (Customer customer :  customers) {
                    System.out.println("ID: " + customer.getId() +
                            "\t - Nome: " + customer.getFirstName() + " " + customer.getLastName() +
                            "\t - Endereço: " + customer.getAddress() +
                            "\t - Telefone: " + customer.getPhoneNumber());
                }

                break;
            }

            case 7: {
                System.out.println("-------OBTENDO DADOS DE CLIENTE--------");
                Scanner scanner = new Scanner(System.in);

                System.out.print("Informe o ID do cliente: ");
                String id = scanner.nextLine();

                Customer customer = customerDB.getCustomerById(id);

                if (customer != null) {
                    System.out.println("Primeiro nome: " + customer.getFirstName());
                    System.out.println("Último nome: " +  customer.getLastName());
                    System.out.println("Endereço: " +  customer.getAddress());
                    System.out.println("Telefone de contato: " + customer.getPhoneNumber());
                } else {
                    System.out.println("Cliente não encontrado!");
                }

                break;
            }

            case 8: {
                System.out.println("Exportando dados de produtos...");
                Scanner scanner = new Scanner(System.in);

                System.out.print("Para onde você deseja exportar o arquivo: ");
                String path = scanner.nextLine();

                List<Product> products = productsDb.getProduct();

                try {
                    CSVExporter.export(path, products);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }
}