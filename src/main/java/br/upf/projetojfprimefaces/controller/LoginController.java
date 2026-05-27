package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.GameEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 * Controlador responsável pela autenticação e login de usuários
 * Gerencia credenciais de acesso ao sistema
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {
    
    private GameEntity game;
    private boolean autenticado = false;
    
    // ==================== CONSTRUTOR E INICIALIZAÇÃO ====================
    
    @PostConstruct
    public void init() {
        prepareAutenticarGame();
    }
    
    /**
     * Prepara uma nova instância de GameEntity para autenticação
     * Limpa os campos de email e senha
     */
    public void prepareAutenticarGame() {
        game = new GameEntity();
        game.setNome("");
        game.setSenha("");
    }
    
    // ==================== MÉTODOS DE AUTENTICAÇÃO ====================
    
    /**
     * Valida as credenciais do usuário
     * Credenciais padrão: usuario@gmail.com / 123
     * 
     * @return URL para redirecionamento em caso de sucesso, null caso contrário
     */
    public String validarLogin() {
        try {
            // Validações básicas
            if (game.getNome() == null || game.getNome().trim().isEmpty()) {
                exibirErro("E-mail é obrigatório!");
                return null;
            }
            
            if (game.getSenha() == null || game.getSenha().trim().isEmpty()) {
                exibirErro("Senha é obrigatória!");
                return null;
            }
            
            // Verificar credenciais
            // Para um sistema real, fazer consulta ao banco de dados
            if (validarCredenciais(game.getNome(), game.getSenha())) {
                autenticado = true;
                exibirMensagem("Bem-vindo ao Sistema de Games!");
                return "/index.xhtml?faces-redirect=true";
            } else {
                exibirErro("E-mail ou senha incorretos!");
                return null;
            }
            
        } catch (Exception e) {
            exibirErro("Erro ao fazer login: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Valida as credenciais contra uma lista de usuários permitidos
     * 
     * @param email email do usuário
     * @param senha senha do usuário
     * @return true se as credenciais são válidas
     */
    private boolean validarCredenciais(String email, String senha) {
        // Usuários padrão do sistema
        return (email.equalsIgnoreCase("usuario@gmail.com") && senha.equals("123")) ||
               (email.equalsIgnoreCase("admin@gmail.com") && senha.equals("admin")) ||
               (email.equalsIgnoreCase("gamer@gmail.com") && senha.equals("gamer123"));
    }
    
    /**
     * Realiza o logout do usuário
     * Limpa a sessão e retorna para login
     */
    public String logout() {
        try {
            autenticado = false;
            prepareAutenticarGame();
            
            // Invalidar sessão
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            
            exibirMensagem("Logout realizado com sucesso!");
            return "/login.xhtml?faces-redirect=true";
        } catch (Exception e) {
            exibirErro("Erro ao fazer logout: " + e.getMessage());
            return null;
        }
    }
    
    // ==================== MÉTODOS DE MENSAGEM ====================
    
    /**
     * Exibe mensagem de sucesso ao usuário
     */
    private void exibirMensagem(String msg) {
        FacesMessage fm = new FacesMessage(
            FacesMessage.SEVERITY_INFO,
            "Sucesso",
            msg
        );
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    /**
     * Exibe mensagem de erro ao usuário
     */
    private void exibirErro(String msg) {
        FacesMessage fm = new FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            "Erro",
            msg
        );
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    // ==================== GETTERS E SETTERS ====================
    
    public GameEntity getGame() {
        return game;
    }
    
    public void setGame(GameEntity game) {
        this.game = game;
    }
    
    public boolean isAutenticado() {
        return autenticado;
    }
    
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    
    // ==================== MÉTODOS DE SUPORTE ====================
    
    /**
     * Verifica se o usuário está autenticado
     */
    public boolean estaAutenticado() {
        return autenticado;
    }
    
    /**
     * Retorna as instruções de login
     */
    public String getInstrucoesLogin() {
        return "Use um dos usuários padrão:\n" +
               "usuario@gmail.com / 123\n" +
               "admin@gmail.com / admin\n" +
               "gamer@gmail.com / gamer123";
    }
}
