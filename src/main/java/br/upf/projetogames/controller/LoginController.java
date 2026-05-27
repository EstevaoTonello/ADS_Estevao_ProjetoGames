package br.upf.projetogames.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String usuario;
    private String senha;
    private boolean logado;

    public String entrar() {
        if ("admin".equals(usuario) && "123".equals(senha)) {
            logado = true;
            return "index.xhtml?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Usuário ou senha inválidos."));
        return null;
    }

    public String sair() {
        usuario = null;
        senha = null;
        logado = false;
        return "login.xhtml?faces-redirect=true";
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public boolean isLogado() { return logado; }
    public void setLogado(boolean logado) { this.logado = logado; }
}
