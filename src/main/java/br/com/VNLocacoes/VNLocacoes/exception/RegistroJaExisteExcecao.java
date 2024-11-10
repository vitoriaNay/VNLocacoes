package br.com.VNLocacoes.VNLocacoes.exception;

public class RegistroJaExisteExcecao extends  RuntimeException {
    public RegistroJaExisteExcecao() {super("O registro já existe na base na base de dados"); }

    public RegistroJaExisteExcecao(String message) {
        super(message);
    }
}
