import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSocket {
	
	private boolean enviarArquivo(Arquivo arq) throws UnknownHostException, IOException{
		
		Socket socket = new Socket(arq.getIpServer(),arq.getPortaServer());
		
		BufferedOutputStream bf = new BufferedOutputStream(socket.getOutputStream());
		byte[] arqSerializado = serializarArquivo(arq);
		//enviando arquivo
		bf.write(arqSerializado);
		bf.flush();
		bf.close();
		
		//TODO: fechar socker só depois de receber o retorno
		return false;
		
	}
	
    private byte[] serializarArquivo(Arquivo arq){
        try {
                 ByteArrayOutputStream bao = new ByteArrayOutputStream();
                 ObjectOutputStream ous;
                 ous = new ObjectOutputStream(bao);
                 ous.writeObject(arq);
                 return bao.toByteArray();
        } catch (IOException e) {
                 e.printStackTrace();
        }
        return null;
}
	
}
