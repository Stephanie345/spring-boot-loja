package br.gov.sp.fatec.springbootloja.security;

public class Login {

    private String username;

    private String password;

    private String autorizacao;

    private String token;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getAutorizacao() {
        return autorizacao;
    }
    
    public void setAutorizacao(String autorizacao) {
        this.autorizacao = autorizacao;
    }

    
    
}
