package br.com.cadastro.objeto;

public class Carro {
	private Integer idcarro;
	private String fabricante;
	private String modelo;
	private String cor;
	private String portas;
	private String combustivel;
	private String ano;
	private int id_cliente;
	
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getPortas() {
		return portas;
	}
	public void setPortas(String portas) {
		this.portas = portas;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Integer getIdcarro() {
		return idcarro;
	}
	public void setIdcarro(Integer idcarro) {
		this.idcarro = idcarro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idcarro == null) ? 0 : idcarro.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (idcarro == null) {
			if (other.idcarro != null)
				return false;
		} else if (!idcarro.equals(other.idcarro))
			return false;
		return true;
	}
	
	
	
}
