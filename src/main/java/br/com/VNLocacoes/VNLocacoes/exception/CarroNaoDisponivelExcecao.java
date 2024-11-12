package br.com.VNLocacoes.VNLocacoes.exception;

public class CarroNaoDisponivelExcecao extends RuntimeException {
    public CarroNaoDisponivelExcecao() {
        super("O carro não está disponível");
    }

    public CarroNaoDisponivelExcecao(String message) {
        super(message);
    }
}