public class Main {
    public static void main(String[] args) {
        Usuario usuario = new UsuarioComum("Nicolas");

        if (usuario.temAcessoAdmin()) {
            System.out.println("Acesso administrativo liberado!");
        } else {
            System.out.println("Acesso negado!");
        }
    }
}
