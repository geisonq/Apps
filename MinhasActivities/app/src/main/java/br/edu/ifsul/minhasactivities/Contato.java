package br.edu.ifsul.minhasactivities;

public class Contato
{

    int _id;
    String _nome;
    String _senha;
    String _email;

    public Contato(){

    }

    public int getId(){
        return this._id;
    }

    public void setId(int id){
        this._id = id;
    }

    public String getNome(){
        return this._nome;
    }

    public void setNome(String nome){
        this._nome = nome;
    }

    public String getSenha(){
        return this._nome;
    }

    public void setSenha(String senha){
        this._senha = senha;
    }

    public String getEmail(){
        return this._email;
    }

    public void setEmail(String email){
        this._email = email;
    }

    @Override
    public String toString() {
        return "Nome: " + _nome + " Senha: " +
                _senha + " Email: " + _email;
    }

}
