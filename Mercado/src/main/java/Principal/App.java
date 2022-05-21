package Principal;

import Classes.produto;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
    produto p = new produto(0);
        p.setDescricao("Produto 03");
        p.setPreco(6.66);
        p.setQuantidade(69);
        p.setFk_categoria(2);
        p.adicionar();
        
        ArrayList<produto> produtos = produto.listar();
        for(produto produto: produtos){
            int id = produto.getId();
            String descricao = produto.getDescricao();
            double preco = produto.getPreco();
            int quantidade = produto.getQuantidade();
           
            System.out.println(id+descricao+preco+quantidade);
        }
        /*
        p.setDescricao("Produto 01");
        p.setPreco(9.99);
        p.setQuantidade(12);
        p.atualizar();
        
        Produto p1 = new Produto(1);
        p1.excluir();
        */
    }
}
