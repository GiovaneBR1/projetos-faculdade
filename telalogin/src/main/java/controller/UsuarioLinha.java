package controller;

import javafx.beans.property.*;

public class UsuarioLinha {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty email;
    private final IntegerProperty tentativas;
    private final BooleanProperty ativo;

    public UsuarioLinha(int id, String nome, String email, int tentativas, boolean ativo) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.email = new SimpleStringProperty(email);
        this.tentativas = new SimpleIntegerProperty(tentativas);
        this.ativo = new SimpleBooleanProperty(ativo);
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getNome() { return nome.get(); }
    public StringProperty nomeProperty() { return nome; }

    public String getEmail() { return email.get(); }
    public StringProperty emailProperty() { return email; }

    public int getTentativas() { return tentativas.get(); }
    public IntegerProperty tentativasProperty() { return tentativas; }
    public void setTentativas(int v) { tentativas.set(v); }

    public boolean isAtivo() { return ativo.get(); }
    public BooleanProperty ativoProperty() { return ativo; }
    public void setAtivo(boolean v) { ativo.set(v); }
}
