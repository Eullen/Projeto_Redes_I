package Cliente;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("unused")
public class ClienteSocket{
    private Socket socket;
    private String ip;
    private int port;
    private int position;
    private byte[] arqSerializado;
    private int countSockets = 1;
    
    public ClienteSocket(Socket socket) {
        this.socket   = socket;
        this.position = 0;
    }
    
    public ClienteSocket(String ip, int port) throws UnknownHostException, IOException {
        this.socket   = new Socket(ip, port);
        this.ip       = ip;
        this.port     = port;
        this.position = 0;
    }
    
	public void enviarArquivo(byte info) throws UnknownHostException, IOException, InterruptedException {
        PrintStream out = new PrintStream(this.socket.getOutputStream());
        out.println(info);
		receberMensagem();
		return;
	}
	
	public void enviarMensagem(String text) throws UnknownHostException, IOException {
        PrintStream out = new PrintStream(this.socket.getOutputStream());
        out.println(text);
        System.out.println(text);
    }

	private byte[] serializarArquivo(Arquivo arq) {
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
    
    public void receberMensagem() throws UnknownHostException, IOException, InterruptedException {
        BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String line          = input.readLine();
        System.out.println(line);
        nextByte();
        return;
    }
    
    public void sendFile(Arquivo arquivo) throws UnknownHostException, IOException, InterruptedException{
         this.arqSerializado = serializarArquivo(arquivo);
         enviarArquivo(this.arqSerializado[this.position++]);
         while(position < this.arqSerializado.length){
             System.gc();
             Thread.sleep(1000);
             nextByte();
         }
         return;
    }
    
    public void nextByte() throws UnknownHostException, IOException, InterruptedException{
        if(this.position < this.arqSerializado.length){
            if(countSockets%200 == 0){
                countSockets = 1;
                return;
            }else{
                countSockets++;
                this.socket.close();
                this.socket = new Socket(this.ip, this.port);
                enviarArquivo(this.arqSerializado[this.position++]);
            }
        }else{
            enviarMensagem("fim");
        }
    }

}
