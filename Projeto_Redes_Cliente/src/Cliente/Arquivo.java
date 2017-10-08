package Cliente;

import java.io.Serializable;

/**
 *
 */

/**
 * @author ra00170556
 *
 */
public class Arquivo implements Serializable {

	private String nomeArquivo;

	public String getNomeArquivo() {
		return this.nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public long getTamanhoArquivo() {
		return this.tamanhoArquivo;
	}

	public void setTamanhoArquivo(long tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}

	public byte[] getConteudo() {
		return this.conteudo;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	public String getIpServer() {
		return this.ipServer;
	}

	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

	public int getPortaServer() {
		return this.portaServer;
	}

	public void setPortaServer(int portaServer) {
		this.portaServer = portaServer;
	}

	private long tamanhoArquivo;
	private byte[] conteudo;

	// não serializaveis
	private transient String ipServer;
	private transient int portaServer;

}
