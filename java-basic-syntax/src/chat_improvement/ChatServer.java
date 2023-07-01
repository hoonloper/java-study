package chat_improvement;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);

        List<ChatThread> list = Collections.synchronizedList(new ArrayList<>());
        while (true) {
            Socket socket = serverSocket.accept();

            System.out.println("접속 트리거 -> " + socket + list.toString());
            ChatThread chatThread = new ChatThread(socket, list);
            chatThread.start();
        }
    }
}