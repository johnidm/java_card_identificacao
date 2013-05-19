package br.com.johni.pessoa;

public class Pessoa {
	
	private byte[] nome;
	private byte[] idade; 
	private byte[] telefone;
	private byte[] observacao;
	/**
	 * @return the nome
	 */
	public byte[] getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(byte[] nome) {
		this.nome = nome;
	}
	/**
	 * @return the idade
	 */
	public byte[] getIdade() {
		return idade;
	}
	/**
	 * @param idade the idade to set
	 */
	public void setIdade(byte[] idade) {
		this.idade = idade;
	}
	/**
	 * @return the telefone
	 */
	public byte[] getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(byte[] telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return the observacao
	 */
	public byte[] getObservacao() {
		return observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(byte[] observacao) {
		this.observacao = observacao;
	}
	public Pessoa() {
		super();
		
		nome = new byte[60];
		idade = new byte[3];
		observacao = new byte[127];
		telefone = new byte[16];		
		
	}
	
	
	
	
	

}
