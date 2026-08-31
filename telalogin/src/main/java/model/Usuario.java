package model;

import java.time.LocalDate;

public class Usuario {
	private int id;
	private String email;
	private String senhaHash;
	private String nome;
	private LocalDate dataNascimento;

	public Usuario() {
	}

	public Usuario(String email, String senhaHash, String nome, LocalDate dataNascimento) {
		this.email = email;
		this.senhaHash = senhaHash;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenhaHash() {
		return senhaHash;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
