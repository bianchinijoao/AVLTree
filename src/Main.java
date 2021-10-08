import core.AVLTree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        boolean controle = true;
        while(controle){
            exibeMenu();
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "i": {
                    System.out.println("Digite o elemento a ser inserido: ");
                    int numero = Integer.parseInt(scanner.nextLine());
                    tree.insert(numero);
                    tree.exibeEmOrdem();
                    break;
                }
                case "b": {
                    System.out.println("Digite o elemento a ser buscado: ");
                    int numero = Integer.parseInt(scanner.nextLine());
                    String retorno = tree.buscar(numero) ? "Numero encontrado!" : "Numero não encontrado!";
                    System.out.println(retorno);
                }
                case "r": {
                    //
                }
                case "s": {
                    controle = false;
                }
                default:
                    System.out.println("Digite uma entrada válida!");
            }
        }
    }

     private static void exibeMenu(){
         System.out.println("\nEscolha uma opcao:");
         System.out.println("i - Insercao");
         System.out.println("b - Buscar");
         System.out.println("r - Remover");
         System.out.println("s - Sair");
    }
}
