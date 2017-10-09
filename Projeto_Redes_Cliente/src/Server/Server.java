package Server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import Cliente.Arquivo;

public class Server extends Thread{
    private Socket socket;
    private ArrayList<Byte> arquivo;
    
    public Server(final Socket socket) throws UnknownHostException, IOException, ClassNotFoundException{
        this.socket  = socket;
        this.arquivo = new ArrayList<>();
    }
    
    public Server(final String ip, final int port) throws UnknownHostException, IOException, ClassNotFoundException{
        this.arquivo = new ArrayList<>();
        this.socket  = new Socket(ip, port);
        receberMensagem();
    }
    
    public void receberMensagem() throws UnknownHostException, IOException, ClassNotFoundException {
        BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String line          = input.readLine();
        if(line.equals("fim")){
            saveFile();
            System.out.println("salvou!!");
        }else{
            System.out.println(line);
            arquivo.add(Byte.valueOf(line));
            enviarMensagem("next");
        }
    }
    
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is    = new ObjectInputStream(in);
        return is.readObject();
    }
    
    public void enviarMensagem(String text) throws UnknownHostException, IOException {
        PrintStream out = new PrintStream(this.socket.getOutputStream());
        out.println(text);
    }

    @SuppressWarnings("unused")
    private Object getObjectFromByte(final byte[] objAsByte) throws Exception{
        ByteArrayInputStream bis = new ByteArrayInputStream(objAsByte);
        ObjectInputStream ois    = new ObjectInputStream(bis);
        Object obj               = ois.readObject();
        bis.close();
        ois.close();
        return obj;
    }
    
    @SuppressWarnings("resource")
    public static void init(int port) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        Server server = new Server(socket);
        server.receberMensagem();
        while (true) {
            server.setSocket(serverSocket.accept());
            server.receberMensagem();
        }
    }
    
    private void setSocket(Socket socket2) {
        this.socket = socket2;
    }
    
    public static byte[] toByteArray(List<Byte> in) {
        final int n = in.size();
        byte ret[] = new byte[n];
        for (int i = 0; i < n; i++) {
            ret[i] = in.get(i);
        }
        return ret;
    }
    
    private void saveFile() throws ClassNotFoundException, IOException{
        Arquivo arq          = (Arquivo) deserialize(toByteArray(arquivo));
        FileOutputStream fos = new FileOutputStream(arq.getNomeArquivo());
        fos.write(arq.getConteudo());
        fos.close();
    }

}
