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
                    int numero;
                    try {
                        numero = Integer.parseInt(scanner.nextLine());
                    } catch(Exception e) {
                        System.out.println("Voce só pode inserir numeros inteiros!");
                        break;
                    }
                    tree.insert(numero);
                    tree.exibeEmOrdem();
                    break;
                }
                case "b": {
                    System.out.println("Digite o elemento a ser buscado: ");
                    int numero = Integer.parseInt(scanner.nextLine());
                    String retorno = tree.buscar(numero) ? "Numero encontrado!" : "Numero não encontrado!";
                    System.out.println(retorno);
                    break;
                }
                case "r": {
                    System.out.println("Digite o elemento a ser removido: ");
                    int numero = Integer.parseInt(scanner.nextLine());
                    tree.removerNodo(numero);
                    System.out.println("Feito");
                    tree.exibeEmOrdem();
                    break;
                }
                case "s": {
                    controle = false;
                    break;
                }
                default:
                    System.out.println("Digite uma entrada válida!");
                    break;
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
