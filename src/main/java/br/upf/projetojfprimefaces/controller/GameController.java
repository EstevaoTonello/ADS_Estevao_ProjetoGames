package br.upf.projetojfprimefaces.controller;

import br.upf.projetojfprimefaces.entity.GameEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador responsável por gerenciar operações com Games
 * Implementa padrão Managed Bean com escopo de sessão
 */
@Named(value = "gameController")
@SessionScoped
public class GameController implements Serializable {
    
    private GameEntity game = new GameEntity();
    private List<GameEntity> gameList = new ArrayList<>();
    private GameEntity selected;
    
    // ==================== CONSTRUTOR ====================
    
    public GameController() {
        inicializarDados();
    }
    
    // ==================== MÉTODOS PRIVADOS ====================
    
    /**
     * Gera um novo ID para o game baseado no tamanho da lista
     * @return próximo ID disponível
     */
    private int gerarID() {
        if (gameList.isEmpty()) {
            return 1;
        }
        int maxId = gameList.stream().mapToInt(GameEntity::getId).max().orElse(0);
        return maxId + 1;
    }
    
    /**
     * Exibe mensagem de sucesso ao usuário
     * @param msg mensagem a ser exibida
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
     * @param msg mensagem de erro
     */
    private void exibirErro(String msg) {
        FacesMessage fm = new FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            "Erro",
            msg
        );
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    /**
     * Inicializa a lista com games de exemplo
     */
    private void inicializarDados() {
        // Game 1 - Elden Ring
        GameEntity g1 = new GameEntity();
        g1.setId(1);
        g1.setNome("Elden Ring");
        g1.setPlataforma("PlayStation");
        g1.setGenero("Ação");
        g1.setAnoLancamento(2022);
        g1.setDesenvolvadora("FromSoftware");
        g1.setPreco(299.90);
        g1.setDescricao("RPG de ação com mundo aberto desafiador e combate épico");
        gameList.add(g1);
        
        // Game 2 - The Legend of Zelda
        GameEntity g2 = new GameEntity();
        g2.setId(2);
        g2.setNome("The Legend of Zelda: Tears of the Kingdom");
        g2.setPlataforma("Nintendo");
        g2.setGenero("Aventura");
        g2.setAnoLancamento(2023);
        g2.setDesenvolvadora("Nintendo");
        g2.setPreco(349.90);
        g2.setDescricao("Aventura épica no reino de Hyrule com puzzles desafiadores");
        gameList.add(g2);
        
        // Game 3 - Starfield
        GameEntity g3 = new GameEntity();
        g3.setId(3);
        g3.setNome("Starfield");
        g3.setPlataforma("Xbox");
        g3.setGenero("RPG");
        g3.setAnoLancamento(2023);
        g3.setDesenvolvadora("Bethesda");
        g3.setPreco(299.90);
        g3.setDescricao("RPG futurista com exploração espacial e múltiplas civilizações");
        gameList.add(g3);
        
        // Game 4 - Hogwarts Legacy
        GameEntity g4 = new GameEntity();
        g4.setId(4);
        g4.setNome("Hogwarts Legacy");
        g4.setPlataforma("PC");
        g4.setGenero("RPG");
        g4.setAnoLancamento(2023);
        g4.setDesenvolvadora("Avalanche Software");
        g4.setPreco(279.90);
        g4.setDescricao("RPG baseado no universo de Harry Potter com magia e exploração");
        gameList.add(g4);
        
        // Game 5 - FIFA 24
        GameEntity g5 = new GameEntity();
        g5.setId(5);
        g5.setNome("EA Sports FC 24");
        g5.setPlataforma("PlayStation");
        g5.setGenero("Esportes");
        g5.setAnoLancamento(2023);
        g5.setDesenvolvadora("EA Sports");
        g5.setPreco(349.90);
        g5.setDescricao("Simulador de futebol com times e jogadores reais");
        gameList.add(g5);
    }
    
    // ==================== MÉTODOS CRUD ====================
    
    /**
     * CASO DE USO 1: Adicionar novo game à lista
     * Valida os campos obrigatórios e insere o game
     */
    public void adicionarGame() {
        try {
            if (game.getNome() == null || game.getNome().trim().isEmpty()) {
                exibirErro("O nome do game é obrigatório!");
                return;
            }
            
            game.setId(gerarID());
            gameList.add(game);
            exibirMensagem("Game '" + game.getNome() + "' adicionado com sucesso!");
            game = new GameEntity();
        } catch (Exception e) {
            exibirErro("Erro ao adicionar game: " + e.getMessage());
        }
    }
    
    /**
     * CASO DE USO 3: Editar um game selecionado
     * Atualiza as informações do game na lista
     */
    public void editarGame() {
        try {
            if (selected == null) {
                exibirErro("Selecione um game para editar!");
                return;
            }
            
            int index = gameList.indexOf(selected);
            if (index >= 0) {
                gameList.set(index, selected);
                exibirMensagem("Game '" + selected.getNome() + "' alterado com sucesso!");
                selected = null;
            }
        } catch (Exception e) {
            exibirErro("Erro ao editar game: " + e.getMessage());
        }
    }
    
    /**
     * CASO DE USO 4: Deletar um game selecionado
     * Remove o game da lista de forma permanente
     */
    public void deletarGame() {
        try {
            if (selected == null) {
                exibirErro("Selecione um game para excluir!");
                return;
            }
            
            String nome = selected.getNome();
            gameList.remove(selected);
            exibirMensagem("Game '" + nome + "' excluído com sucesso!");
            selected = null;
        } catch (Exception e) {
            exibirErro("Erro ao excluir game: " + e.getMessage());
        }
    }
    
    /**
     * CASO DE USO 2: Listar todos os games
     * Retorna a lista completa de games para exibição na tabela
     */
    public List<GameEntity> getGameList() {
        return gameList;
    }
    
    /**
     * CASO DE USO 5: Visualizar detalhes de um game selecionado
     * Retorna o game atualmente selecionado
     */
    public GameEntity getSelected() {
        return selected;
    }
    
    // ==================== GETTERS E SETTERS ====================
    
    public GameEntity getGame() {
        return game;
    }
    
    public void setGame(GameEntity game) {
        this.game = game;
    }
    
    public void setGameList(List<GameEntity> gameList) {
        this.gameList = gameList;
    }
    
    public void setSelected(GameEntity selected) {
        this.selected = selected;
    }
    
    // ==================== MÉTODOS DE SUPORTE ====================
    
    /**
     * Formata o preço para exibição em moeda brasileira
     */
    public String formatarPreco(double preco) {
        return String.format("R$ %.2f", preco);
    }
    
    /**
     * Conta o total de games cadastrados
     */
    public int contarGames() {
        return gameList.size();
    }
    
    /**
     * Filtra games por plataforma
     */
    public List<GameEntity> filtrarPorPlataforma(String plataforma) {
        List<GameEntity> filtrados = new ArrayList<>();
        for (GameEntity g : gameList) {
            if (g.getPlataforma().equalsIgnoreCase(plataforma)) {
                filtrados.add(g);
            }
        }
        return filtrados;
    }
    
    /**
     * Filtra games por gênero
     */
    public List<GameEntity> filtrarPorGenero(String genero) {
        List<GameEntity> filtrados = new ArrayList<>();
        for (GameEntity g : gameList) {
            if (g.getGenero().equalsIgnoreCase(genero)) {
                filtrados.add(g);
            }
        }
        return filtrados;
    }
}
