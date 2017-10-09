package Server;

public class Main_s {
    public static void main(String[] args) {
        try{
            System.out.println("server up!");
            Server.init(4433);
        }catch(Exception e){
            System.err.println(e.getMessage());
        } 
    }
}
