package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class carrinho{
    protected int id;
    protected int fk_produto;
    protected int quantidade;
    protected double preco;
    protected double total;
    
    public carrinho(int id){
        if(id != 0){
            String sql = "SELECT * FROM carrinho WHERE id = ?";
            try{
                Connection con = DB.conexao();
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet registro = stmt.executeQuery();
                
                while(registro.next()){
                    this.setId(registro.getInt("id"));
                    this.setFk_produto(registro.getInt("fk_produto"));
                    this.setQuantidade(registro.getInt("quantidade"));
                    this.setPreco(registro.getDouble("preco"));
                    this.setTotal(registro.getDouble("total"));
                }
            }catch(SQLException e){
                System.out.println("Erro na consulta de carrinho:"+e.toString());
            }
        }
    }
    
    public void setId(int i){
        this.id = i;
    }
    public int getId(){
        return this.id;
    }
    public void setFk_produto(int f){
        this.fk_produto = f;
    }
    public int getFk_produto(){
        return this.fk_produto;
    }
    public void setQuantidade(int q){
        this.quantidade = q;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public void setPreco(double p){
        this.preco = p;
    }
    public double getPreco(){
        return this.preco;
    }
    public void setTotal(double t){
        this.total = t;
    }
    public double getTotal(){
        return this.total;
    }
    
    public void adicionar(){
        String sql = "INSERT INTO carrinho(fk_produto, quantidade, preco, total)VALUES(?,?,?,?)";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
           
            stmt.setInt(1,this.getFk_produto());
            stmt.setInt(2,this.getQuantidade());
            stmt.setDouble(3,this.getPreco());
            stmt.setDouble(4,this.getTotal());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro no adicionar carrinho: "+e.toString());
        }
    }
    
    public static ArrayList<carrinho> listar(){
        String sql = "SELECT * FROM carrinho";
        ArrayList<carrinho> carrinhos = new ArrayList<>();

        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            while(registros.next()){
                carrinho temp = new carrinho(0);
                temp.setId(registros.getInt("id"));
                temp.setFk_produto(registros.getInt("fk_produto"));
                temp.setQuantidade(registros.getInt("quantidade"));
                temp.setPreco(registros.getDouble("preco"));
                temp.setTotal(registros.getDouble("total"));

                carrinhos.add(temp);
            }
        }catch(SQLException e){
            System.out.println("Erro no Listar carrinho: "+ e.toString());
        }

        return carrinhos;
    }  
    
    public void atualizar(){
        String sql = "UPDATE carrinho SET fk_produto =?, quantidade =?, preco=?, total=? Where id =?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getFk_produto());
            stmt.setInt(2, this.getQuantidade());
            stmt.setDouble(3, this.getPreco());
            stmt.setDouble(4, this.getTotal());
            stmt.setInt(5, getId());

            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Atualizar carrinho: "+e.toString());
        }
    }
    
    public void excluir(){
        String sql = "DELETE FROM carrinho WHERE id = ?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Delete carrinho: "+ e.toString());
        }
    }
    
}
