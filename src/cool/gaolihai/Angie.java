package cool.gaolihai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Angie {
    private final HashMap<String, Processor> routeMap = new HashMap();

    public void use(String route, Processor processor) {
        routeMap.put(route, processor);
    }

    public void listen(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(() -> {
                    try {
                        Request request = new Request(client.getInputStream());
                        Response response = new Response(client.getOutputStream());
                        if (routeMap.containsKey(request.getUrl())) {
                            routeMap.get(request.getUrl()).callback(request, response);
                        } else {
                            response.setStatus(404).send(request.getUrl() + " not found");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
