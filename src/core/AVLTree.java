package core;

public class AVLTree {
    private AVLNode raiz;

    // Construtor da Arvore AVL
    public AVLTree() {
        raiz = null;
    }

    private static int altura(AVLNode nodo) {
        if (nodo == null)
            return -1;
        else
            return nodo.getAltura();
    }

    // Seleciona dois nodos, compara a altura entre eles e retorna a maior dentre elas
    private static int getNodoMaisAlto(int NodoEsquerda, int NodoDireita) {
        if (NodoEsquerda > NodoDireita)
            return NodoEsquerda;
        else
            return NodoDireita;
    }

    // Retorna o fator de um nodo
    // Calcula o fator de balanceamento
    private int getFator(AVLNode nodo) {
        return altura(nodo.getEsquerda()) - altura(nodo.getDireita());
    }

    private AVLNode insert(int numero, AVLNode nodo) {
        if (nodo == null) {
            nodo = new AVLNode(numero, null, null);
        } else if (numero < nodo.getChave()) {
            nodo.setEsquerda((insert(numero, nodo.getEsquerda())));
        } else if (numero > nodo.getChave()) {
            nodo.setDireita(insert(numero, nodo.getDireita()));
        }
        if (getFator(nodo) == 2) {
            if (getFator(nodo.getEsquerda()) > 0)
                nodo = rotacaoSimplesDireita(nodo);
            else
                nodo = rotacaoDuplaDireita(nodo);
        } else if (getFator(nodo) == -2) {
            if (getFator(nodo.getDireita()) < 0)
                nodo = rotacaoSimplesEsquerda(nodo);
            else
                nodo = rotacaoDuplaEsquerda(nodo);
        }
        nodo.setAltura(getNodoMaisAlto(altura(nodo.getEsquerda()), altura(nodo.getDireita())) + 1);

        return nodo;
    }

    public void insert(int numero){
        raiz = insert(numero, raiz);
    }


    //Rotaciona a Sub-arvore a Direita.
    private static AVLNode rotacaoSimplesDireita(AVLNode n2) {
        AVLNode n1 = n2.getEsquerda();
        n2.setEsquerda(n1.getDireita());
        n1.setDireita(n2);
        n2.setAltura(getNodoMaisAlto(altura(n2.getEsquerda()), altura(n2.getDireita())) + 1);
        n1.setAltura(getNodoMaisAlto(altura(n1.getEsquerda()), n2.getAltura()) + 1);

        return n1;
    }

    //Rotaciona a Sub-arvore a esquerda.
    private static AVLNode rotacaoSimplesEsquerda(AVLNode n1) {
        AVLNode n2 = n1.getDireita();
        n1.setDireita(n2.getEsquerda());
        n2.setEsquerda(n1);
        n1.setAltura(getNodoMaisAlto(altura(n1.getEsquerda()), altura(n1.getDireita())) + 1);
        n2.setAltura(getNodoMaisAlto(altura(n2.getDireita()), n1.getAltura()) + 1);

        return n2;
    }

    //Rotaciona a Sub-arvore primeiro a esquerda, depois a direita.
    private static AVLNode rotacaoDuplaDireita(AVLNode n3) {
        n3.setEsquerda(rotacaoSimplesEsquerda(n3.getEsquerda()));

        return rotacaoSimplesDireita(n3);
    }

    //Rotaciona a Sub-arvore primeiro a direita, depois a esquerda.
    private static AVLNode rotacaoDuplaEsquerda(AVLNode n1) {
        n1.setDireita(rotacaoSimplesDireita(n1.getDireita()));

        return rotacaoSimplesEsquerda(n1);
    }

    public void exibeEmOrdem() {
        exibeEmOrdem(raiz);
    }

    protected void exibeEmOrdem(AVLNode nodo) {
        if (nodo != null) {
            exibeEmOrdem(nodo.getEsquerda());
            System.out.print(nodo.getChave() + " ");
            exibeEmOrdem(nodo.getDireita());
        }
    }

    public void exibeEmPreOrdem() {
        exibeEmPreOrdem(raiz);
    }

    private void exibeEmPreOrdem(AVLNode nodo) {
        if (nodo != null) {
            System.out.print(nodo.getChave() + " ");
            exibeEmPosOrdem(nodo.getEsquerda());
            exibeEmPosOrdem(nodo.getDireita());
        }
    }

    public void exibeEmPosOrdem() {
        exibeEmPosOrdem(raiz);
    }

    private void exibeEmPosOrdem(AVLNode nodo) {
        if (nodo != null) {
            exibeEmPosOrdem(nodo.getEsquerda());
            exibeEmPosOrdem(nodo.getDireita());
            System.out.print(nodo.getChave() + " ");
        }
    }

    private boolean buscar(AVLNode raiz, int elemento) {
        boolean check = false;
        while ((raiz != null) && !check)
        {
            int elementoRaiz = raiz.getChave();
            if (elemento < elementoRaiz)
                raiz = raiz.getEsquerda();
            else if (elemento > elementoRaiz)
                raiz = raiz.getDireita();
            else
            {
                check = true;
                break;
            }
            check = buscar(raiz, elemento);
        }
        return check;
    }

    public boolean buscar(int element) {
        return buscar(raiz, element);
    }

    private AVLNode removerNodo(AVLNode raiz, int elemento) {

        if(raiz.getChave() == elemento && raiz.getAltura() == 0){
            raiz = null;
        }

        if (raiz == null)
            return raiz;

        if ( elemento < raiz.getChave() )
            raiz.setEsquerda(removerNodo(raiz.getEsquerda(), elemento));

        else if( elemento > raiz.getChave() )
            raiz.setDireita(removerNodo(raiz.getDireita(), elemento));

        else {
            if( (raiz.getEsquerda() == null) || (raiz.getDireita() == null) ) {

                AVLNode avlAux;
                if (raiz.getEsquerda() != null)
                    avlAux = raiz.getEsquerda();
                else
                    avlAux = raiz.getDireita();

                if(avlAux == null) {
                    avlAux = raiz;
                    raiz = null;
                }
                else {
                    raiz = avlAux;
                }
                avlAux = null;
            }
            else {
                AVLNode temp = minValueNode(raiz.getDireita());

                raiz.setChave(temp.getChave());

                raiz.setDireita(removerNodo(raiz.getDireita(), temp.getChave()));
            }
        }

        if (raiz == null)
            return raiz;

        raiz.setAltura(Math.max(altura(raiz.getEsquerda()), altura(raiz.getDireita())) + 1);

        int balance = getFator(raiz);

        if (balance > 1 && getFator(raiz.getEsquerda()) >= 0)
            return rotacaoSimplesDireita(raiz);

        if (balance > 1 && getFator(raiz.getEsquerda()) < 0) {
            raiz.setEsquerda(rotacaoSimplesEsquerda(raiz.getEsquerda()));
            return rotacaoSimplesDireita(raiz);
        }

        if (balance < -1 && getFator(raiz.getDireita()) <= 0)
            return rotacaoSimplesEsquerda(raiz);

        if (balance < -1 && getFator(raiz.getDireita()) > 0) {
            raiz.setDireita(rotacaoSimplesDireita(raiz.getDireita()));
            return rotacaoSimplesEsquerda(raiz);
        }

        return raiz;
    }

    private AVLNode minValueNode(AVLNode raiz) {
        AVLNode nodo = raiz;

        while (nodo.getEsquerda() != null)
            nodo = nodo.getEsquerda();
        return nodo;
    }
    public void removerNodo(int elemento) {
        removerNodo(raiz, elemento);
    }
}
