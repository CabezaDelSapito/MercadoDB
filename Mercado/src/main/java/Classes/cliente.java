package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class cliente{
    protected int id;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String email;
    
    public cliente(int id){
        if(id != 0){
            String sql = "SELECT * FROM cliente WHERE id = ?";
            try{
                Connection con = DB.conexao();
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet registro = stmt.executeQuery();
                
                while(registro.next()){
                    this.setId(registro.getInt("id"));
                    this.setNome(registro.getString("nome"));
                    this.setCPF(registro.getString("cpf"));
                    this.setTelefone(registro.getString("telefone"));
                    this.setEmail(registro.getString("email"));
                }
            }catch(SQLException e){
                System.out.println("Erro na consulta de cliente:"+e.toString());
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
    public void setCPF(String c){
        this.cpf = c;
    }
    public String getCPF(){
        return this.cpf;
    }
    public void setTelefone(String t){
        this.telefone = t;
    }
    public String getTelefone(){
        return this.telefone;
    }
    public void setEmail(String e){
        this.email = e;
    }
    public String getEmail(){
        return this.email;
    }
    
    public void adicionar(){
        String sql = "INSERT INTO cliente(nome, cpf, telefone, email)VALUES(?,?,?,?)";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
           
            stmt.setString(1,this.getNome());
            stmt.setString(2,this.getCPF());
            stmt.setString(3,this.getTelefone());
            stmt.setString(4,this.getEmail());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro no adicionar cliente: "+e.toString());
        }
    }
    
    public static ArrayList<cliente> listar(){
        String sql = "SELECT * FROM cliente";
        ArrayList<cliente> clientes = new ArrayList<>();

        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            while(registros.next()){
                cliente temp = new cliente(0);
                temp.setId(registros.getInt("id"));
                temp.setNome(registros.getString("nome"));
                temp.setCPF(registros.getString("cpf"));
                temp.setTelefone(registros.getString("telefone"));
                temp.setEmail(registros.getString("email"));

                clientes.add(temp);
            }
        }catch(SQLException e){
            System.out.println("Erro no Listar cliente: "+ e.toString());
        }

        return clientes;
    }  
    
    public void atualizar(){
        String sql = "UPDATE cliente SET nome =?, cpf =?, telefone=?, email=? Where id =?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getCPF());
            stmt.setString(3, this.getTelefone());
            stmt.setString(4, this.getEmail());
            stmt.setInt(5, getId());

            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Atualizar cliente: "+e.toString());
        }
    }
    
    public void excluir(){
        String sql = "DELETE FROM cliente WHERE id = ?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Delete cliente: "+ e.toString());
        }
    }
    
}
