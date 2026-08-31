package model;

public class Acesso {
	private int id;
	private boolean ativo;
	private int tentativasRest;
	private int idUsr;

	public Acesso() {
	}

	public Acesso(boolean ativo, int tentativasRest, int idUsr) {
		this.ativo = ativo;
		this.tentativasRest = tentativasRest;
		this.idUsr = idUsr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getTentativasRest() {
		return tentativasRest;
	}

	public void setTentativasRest(int tentativasRest) {
		this.tentativasRest = tentativasRest;
	}

	public int getIdUsr() {
		return idUsr;
	}

	public void setIdUsr(int idUsr) {
		this.idUsr = idUsr;
	}
}
