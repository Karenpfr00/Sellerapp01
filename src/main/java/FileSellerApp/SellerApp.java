package FileSellerApp;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import UI.*;
import repository.Configuration;

public class SellerApp {
    public static void main(String... args) {
              System.out.println("Boas Vindas ao Seller App");

            int option;

        Map<Integer, IUserInterface> optionIUserInterfaces = new HashMap<>();
        optionIUserInterfaces.put(1, new CreateProductUI());
        optionIUserInterfaces.put(2, new ListProductUI());
        optionIUserInterfaces.put(3,new FindProductUI());
        optionIUserInterfaces.put(4, new CreatSupplierUI());
        optionIUserInterfaces.put(5, new CreateCustomerUI());
        optionIUserInterfaces.put(6, new ListCustomerUI());
        optionIUserInterfaces.put(7, new FindCustumerUI());
        optionIUserInterfaces.put(8,new ExportProductsUI());
        optionIUserInterfaces.put(9, new CreateOrderUI());
        optionIUserInterfaces.put(10, new ListOrderUI());

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
                System.out.println("9 - Pedido de Venda");
                System.out.println("10 - Listar Pedidos de Vendas");
                System.out.println("0 - Sair do programa");

                System.out.println("Escolha uma operação: ");
                Scanner scanner = new Scanner(System.in);
                option = scanner.nextInt();

                if (option != 0) {
                    IUserInterface ui = optionIUserInterfaces.get(option);
                    ui.show();
                }
        } while (option != 0);

        Configuration.closeConnection();

    }
}