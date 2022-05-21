package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class produto {
    protected int id;
    protected String descricao;
    protected double preco;
    protected int quantidade;
    protected int fk_categoria;
    
    public produto(int id){
        if(id != 0){
            String sql = "SELECT * FROM produto WHERE id = ?";
            try{
                Connection con = DB.conexao();
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet registro = stmt.executeQuery();
                
                while(registro.next()){
                    this.setId(registro.getInt("id"));
                    this.setDescricao(registro.getString("descricao"));
                    this.setPreco(registro.getDouble("preco"));
                    this.setQuantidade(registro.getInt("quantidade"));
                    this.setFk_categoria(registro.getInt("fk_categoria"));
                }
            }catch(SQLException e){
                System.out.println("Erro na consulta de Produto:"+e.toString());
            }
        }
    }
    
    public void setFk_categoria(int i){
        this.fk_categoria = i;
    }
    public int getFk_categoria(){
        return this.fk_categoria;
    }
    
    public void setId(int i){
        this.id = i;
    }
    public int getId(){
        return this.id;
    }
    public void setDescricao(String d){
        this.descricao = d;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public void setPreco(double p){
        this.preco = p;
    }
    public double getPreco(){
        return this.preco;
    }
    public void setQuantidade(int q){
        this.quantidade = q;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public void adicionar(){
        String sql = "INSERT INTO produto(descricao, preco, quantidade, fk_categoria)VALUES(?,?,?)";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
           
            stmt.setString(1,this.getDescricao());
            stmt.setDouble(2,this.getPreco());
            stmt.setInt(3,this.getQuantidade());
            stmt.setInt(4,this.getFk_categoria());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro no adicionar Produto: "+e.toString());
        }
    }
    
    public static ArrayList<produto> listar(){
        String sql = "SELECT * FROM produto";
        ArrayList<produto> produtos = new ArrayList<>();

        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            while(registros.next()){
                produto temp = new produto(0);
                temp.setId(registros.getInt("id"));
                temp.setDescricao(registros.getString("descricao"));
                temp.setPreco(registros.getDouble("preco"));
                temp.setQuantidade(registros.getInt("quantidade"));
                temp.setFk_categoria(registros.getInt("fk_categoria"));

                produtos.add(temp);
            }
        }catch(SQLException e){
            System.out.println("Erro no Listar Produto: "+ e.toString());
        }

        return produtos;
    }  
    
    public void atualizar(){
        String sql = "UPDATE produto SET descricao =?, preco =?, quantidade=?, fk_categoria=? Where id =?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.getDescricao());
            stmt.setDouble(2, this.getPreco());
            stmt.setInt(3, this.getQuantidade());
            stmt.setInt(4, this.getFk_categoria());
            stmt.setInt(5, getId());

            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Atualizar: "+e.toString());
        }
    }
    
    public void excluir(){
        String sql = "DELETE FROM produto WHERE id = ?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Delete: "+ e.toString());
        }
    }

}
