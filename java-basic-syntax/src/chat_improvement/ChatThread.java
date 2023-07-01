package chat_improvement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatThread extends Thread {
    private final String name;
    private final BufferedReader br;
    private final PrintWriter pw;
    private final Socket socket;
    List<ChatThread> list;

    public ChatThread(Socket socket, List<ChatThread> list) throws Exception {
        this.socket = socket;
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.name = br.readLine();
        this.list = list;
        this.list.add(this);
    }

    public void sendMessage(String msg) {
        pw.println(msg);
        pw.flush();
    }

    @Override
    public void run() {
        // ChatThread는 사용자가 보낸 메시지를 읽어들여서 접속된 모든 클라이언트에게 전송 -> 브로드캐스트
        // 나를 제외한 모든 사용자에게 "OOO님이 연결되었습니다." 전송, 현재 스레드를 제외
        try {
            broadcast(name + "님이 연결되었습니다.", false);

            String line = null;
            while ((line = br.readLine()) != null) {
                if("/quit".equals(line)) {
                    throw new RuntimeException("접속 종료");
                }
                broadcast(name + " : " + line, true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            broadcast(name + "님이 연결이 끊어졌습니다.", false);
            this.list.remove(this);
            try {
                br.close();
            } catch (Exception ignored) {}
            try {
                pw.close();
            } catch (Exception ignored) {}
            try {
                socket.close();
            } catch (Exception ignored) {}
        }
    }

    private void broadcast(String msg, boolean includeMe) {
        List<ChatThread> chatThreads = new ArrayList<>(this.list);

        try {
            for (ChatThread ct : chatThreads) {
                if (!includeMe && (ct == this)) continue;
                ct.sendMessage(msg);
            }
        } catch (Exception ex) {
            System.out.println("///");
        }
    }
}
