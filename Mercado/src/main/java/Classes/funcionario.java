package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class funcionario{
    protected int id;
    protected String nome;
    protected String cpf;
    protected String dataNascimento;
    protected double salario;
    
    public funcionario(int id){
        if(id != 0){
            String sql = "SELECT * FROM funcionario WHERE id = ?";
            try{
                Connection con = DB.conexao();
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet registro = stmt.executeQuery();
                
                while(registro.next()){
                    this.setId(registro.getInt("id"));
                    this.setNome(registro.getString("nome"));
                    this.setCPF(registro.getString("cpf"));
                    this.setDataNascimento(registro.getString("dataNascimento"));
                    this.setSalario(registro.getDouble("salario"));
                }
            }catch(SQLException e){
                System.out.println("Erro na consulta de funcionario:"+e.toString());
            }
        }
    }
    
    public void setId(int i){
        this.id = i;
    }
    public void setNome(String n){
        this.nome = n;
    }
    public void setCPF(String c){
        this.cpf = c;
    }
    public void setDataNascimento(String d){
        this.dataNascimento = d;
    }
    public void setSalario(double s){
        this.salario = s;
    }
    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getCPF(){
        return this.cpf;
    }
    public String getDataNascimento(){
        return this.dataNascimento;
    }
    public double getSalario(){
        return this.salario;
    }
    
    public void adicionar(){
        String sql = "INSERT INTO funcionario(nome, cpf, dataNascimento, salario)VALUES(?,?,?,?)";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
           
            stmt.setString(1,this.getNome());
            stmt.setString(2,this.getCPF());
            stmt.setString(3,this.getDataNascimento());
            stmt.setDouble(4,this.getSalario());
            stmt.execute();
        }catch(SQLException e){
            System.out.println("Erro no adicionar funcionario: "+e.toString());
        }
    }
    
    public static ArrayList<funcionario> listar(){
        String sql = "SELECT * FROM funcionario";
        ArrayList<funcionario> funcionarios = new ArrayList<>();

        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet registros = stmt.executeQuery();

            while(registros.next()){
                funcionario temp = new funcionario(0);
                temp.setId(registros.getInt("id"));
                temp.setNome(registros.getString("nome"));
                temp.setCPF(registros.getString("cpf"));
                temp.setDataNascimento(registros.getString("dataNascimento"));
                temp.setSalario(registros.getDouble("salario"));

                funcionarios.add(temp);
            }
        }catch(SQLException e){
            System.out.println("Erro no Listar funcionarios: "+ e.toString());
        }

        return funcionarios;
    }  
    
    public void atualizar(){
        String sql = "UPDATE funcionario SET nome =?, cpf =?, dataNascimento=?, salario=? Where id =?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getCPF());
            stmt.setString(3, this.getDataNascimento());
            stmt.setDouble(4, this.getSalario());
            stmt.setInt(5, getId());

            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Atualizar funcionario: "+e.toString());
        }
    }
    
    public void excluir(){
        String sql = "DELETE FROM funcionario WHERE id = ?";
        try{
            Connection con = DB.conexao();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, this.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro no Delete funcionario: "+ e.toString());
        }
    }
    
}
