package br.upf.projetogames.facade;

import br.upf.projetogames.entity.GameEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class GameFacade extends AbstractFacade<GameEntity> {

    @PersistenceContext(unitName = "projetogamesPU")
    private EntityManager em;

    public GameFacade() {
        super(GameEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
