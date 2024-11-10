package br.com.VNLocacoes.VNLocacoes.entity;

public enum UsuarioPapel {

    ADMIN("admin"),
    USER("user");

    private String papel;

    UsuarioPapel(String papel) {
        this.papel = papel;
    }


    public String getPapel() {
        return papel;
    }
}
