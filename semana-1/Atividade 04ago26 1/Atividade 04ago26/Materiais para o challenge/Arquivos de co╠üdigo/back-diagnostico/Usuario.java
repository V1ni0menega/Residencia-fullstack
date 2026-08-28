public abstract class Usuario {
    protected String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public boolean temAcessoAdmin(){
        return true;
    }
}
