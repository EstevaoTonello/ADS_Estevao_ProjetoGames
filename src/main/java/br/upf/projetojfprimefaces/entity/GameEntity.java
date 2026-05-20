package br.upf.projetojfprimefaces.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 * Entidade que representa um Game no sistema
 * Mapeia os atributos essenciais de um videogame
 */
@Entity
public class GameEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // Atributos do Game
    private String nome;
    private String plataforma;      // PC, PlayStation, Xbox, Nintendo
    private String genero;           // Ação, RPG, Estratégia, Esportes, Aventura, Simulação
    private int anoLancamento;
    private String desenvolvedora;
    private double preco;
    private String descricao;
    private String senha;            // Para autenticação
    
    // ==================== CONSTRUTORES ====================
    
    public GameEntity() {
    }
    
    public GameEntity(String nome, String plataforma, String genero, 
                     int anoLancamento, String desenvolvedora, double preco, 
                     String descricao) {
        this.nome = nome;
        this.plataforma = plataforma;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.desenvolvedora = desenvolvedora;
        this.preco = preco;
        this.descricao = descricao;
    }
    
    // ==================== GETTERS E SETTERS ====================
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getPlataforma() {
        return plataforma;
    }
    
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public int getAnoLancamento() {
        return anoLancamento;
    }
    
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    
    public String getDesenvolvadora() {
        return desenvolvedora;
    }
    
    public void setDesenvolvadora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }
    
    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    // ==================== MÉTODOS AUXILIARES ====================
    
    @Override
    public String toString() {
        return "GameEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", genero='" + genero + '\'' +
                ", anoLancamento=" + anoLancamento +
                ", desenvolvedora='" + desenvolvedora + '\'' +
                ", preco=" + preco +
                '}';
    }
}
