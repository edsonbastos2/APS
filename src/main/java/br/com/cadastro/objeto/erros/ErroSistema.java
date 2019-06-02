package br.com.cadastro.objeto.erros;

public class ErroSistema extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ErroSistema(String msg) {
		super(msg);
	}
	
	public ErroSistema(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}

}
