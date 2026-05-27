package br.upf.projetogames.controller;

import br.upf.projetogames.entity.GameEntity;
import br.upf.projetogames.facade.GameFacade;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("gameController")
@SessionScoped
public class GameController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private GameFacade gameFacade;

    private List<GameEntity> games;
    private GameEntity game;
    private GameEntity selectedGame;
    private boolean editando;

    @PostConstruct
    public void init() {
        novoGame();
        carregarGames();
    }

    public void carregarGames() {
        games = gameFacade.findAll();
    }

    public void novoGame() {
        game = new GameEntity();
        game.setMultiplayer(false);
        editando = false;
    }

    public void prepararEdicao() {
        if (selectedGame == null) {
            mensagem("Selecione um game para editar.");
            return;
        }

        game = selectedGame;
        editando = true;
    }

    public void salvar() {
        if (game.getMultiplayer() == null) {
            game.setMultiplayer(false);
        }

        if (editando) {
            gameFacade.edit(game);
            mensagem("Game atualizado com sucesso.");
        } else {
            gameFacade.create(game);
            selectedGame = game;
            mensagem("Game cadastrado com sucesso.");
        }

        novoGame();
        carregarGames();
    }

    public void excluir() {
        if (selectedGame == null) {
            mensagem("Selecione um game para excluir.");
            return;
        }

        gameFacade.remove(selectedGame);
        selectedGame = null;
        novoGame();
        carregarGames();
        mensagem("Game excluído com sucesso.");
    }

    private void mensagem(String texto) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, texto, null));
    }

    public List<GameEntity> getGames() {
        if (games == null) {
            carregarGames();
        }
        return games;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public GameEntity getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(GameEntity selectedGame) {
        this.selectedGame = selectedGame;
    }

    public boolean isEditando() {
        return editando;
    }
}
