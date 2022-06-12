import models.Product;
import repositorio.ProdutosDB;


import java.util.Scanner;

public class SellerApp {
    static ProdutosDB produtosDB = new ProdutosDB();

    public static void main(String... args) {
        System.out.println("Boas Vindas ao Seller App");

        int option;

        do{
            System.out.println("---------MENU PRINCIPAL---------");
            System.out.println("1 - Cadastrar um novo produto");
            System.out.println("2 - Listar todos os produtos cadastrados");
            System.out.println("3 - Criar um novo fornecedor");
            System.out.println("0 - Sair do programa");

            System.out.println("Escolha uma operação: ");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();

            process(option);
        } while (option != 0);
    }

    static void process(int option){
        switch (option){
            case 1:{
                Scanner scanner = new Scanner(System.in);

                System.out.println("Informe a descrição do produto: ");
                String descricao = scanner.nextLine();

                System.out.println("Informe um ID do produto: ");
                String id = scanner.nextLine();

                System.out.println("Informe o preço: ");
                double preco = scanner.nextDouble();

                Product novoProduct = new Product(id,descricao,preco);
                produtosDB.addProduto(novoProduct);

                break;
            }

            case 2: {
                System.out.println("---------------LISTANDO PRODUTOS---------");
                for(Product product : produtosDB.getProdutos()){
                    System.out.println("ID: " + product.getId());
                    System.out.println("Descrição: " + product.getDescricao());
                    System.out.println("Preço: " + product.getPreco());
                    System.out.println("-------------------------------------");

                }
                break;
            }

            case 3: {
                System.out.println("-------------CRIANDO NOVO FORNECEDOR");
                Scanner scanner = new Scanner(System.in);

                System.out.print("Informe o ID do fornecedor: ");
                String id = scanner.nextLine();

                System.out.println("Informe o Nome: ");
                String nome = scanner.nextLine();

                System.out.println("Informe o telefone: ");
                String telefone = scanner.nextLine();

                //  Fornecedor fornecedor = new Fornecedor(id, nome, telefone);
                // fornecedorDB.addFornecedor (fornecedor);
            }

        }
    }
}