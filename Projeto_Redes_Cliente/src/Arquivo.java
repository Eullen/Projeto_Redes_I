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
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public long getTamanhoArquivo() {
		return tamanhoArquivo;
	}
	public void setTamanhoArquivo(long tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}
	public String getDiretorioDestino() {
		return diretorioDestino;
	}
	public void setDiretorioDestino(String diretorioDestino) {
		this.diretorioDestino = diretorioDestino;
	}
	public byte[] getConteudo() {
		return conteudo;
	}
	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}
	public String getIpServer() {
		return ipServer;
	}
	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}
	public int getPortaServer() {
		return portaServer;
	}
	public void setPortaServer(int portaServer) {
		this.portaServer = portaServer;
	}
	private long tamanhoArquivo;
	private String diretorioDestino;
	private byte[] conteudo;
	
	//não serializaveis
	private transient String ipServer;
	private transient int portaServer;
	
	

}
