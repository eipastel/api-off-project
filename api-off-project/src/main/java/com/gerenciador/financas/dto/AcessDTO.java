package com.gerenciador.financas.dto;

public class AcessDTO {
    //TODO implementar retornar o usuario e liberações (authorities)
    private String token;

    public AcessDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
