package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class categorias {
    protected int id;
    protected String nome;
    
    public categorias(int id){
        if(id != 0){
            String sql = "SELECT * FROM categorias WHERE id = ?";
            try{
                Connection con = DB.conexao();
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet registro = stmt.executeQuery();
                
                while(registro.next()){
                    this.setId(registro.getInt("id"));
                    this.setNome(registro.getString("nome"));
                }
            }catch(SQLException e){
                System.out.println("Erro na consulta de categorias:"+e.toString());
            }
        }
    }
    
    public void setId(int i){
        this.id = i;
    }
    public int getId(){
        return this.id;
    }
    public void setNome(String n){
        this.nome = n;
    }
    public String getNome(){
        return this.nome;
    }
    
    public void adicionar(){
        String sql = "INSERT INTO categorias(nome)VALUES(?)";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
           
            stmt.setString(1,this.getNome());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro no adicionar categorias: "+e.toString());
        }
    }
    
    public static ArrayList<categorias> listar(){
        String sql = "SELECT * FROM categorias";
        ArrayList<categorias> categorias = new ArrayList<>();

        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            while(registros.next()){
                categorias temp = new categorias(0);
                temp.setId(registros.getInt("id"));
                temp.setNome(registros.getString("nome"));
                
                categorias.add(temp);
            }
        }catch(SQLException e){
            System.out.println("Erro no Listar categorias: "+ e.toString());
        }

        return categorias;
    }  
    
    public void atualizar(){
        String sql = "UPDATE categorias SET nome =? Where id =?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.getNome());
            stmt.setInt(2, getId());

            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Atualizar categorias: "+e.toString());
        }
    }
    
    public void excluir(){
        String sql = "DELETE FROM categorias WHERE id = ?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Delete categorias: "+ e.toString());
        }
    }
}
