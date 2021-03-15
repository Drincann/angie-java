package cool.gaolihai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {

    private String url;
    private String params;
    private String method;

    public Request(InputStream inputStream){
        try {
            String[] requestLine =  new BufferedReader(new InputStreamReader(inputStream)).readLine().split(" ");
            if (requestLine.length == 3 && requestLine[2].equals("HTTP/1.1")) {
                this.method = requestLine[0];
                String allUrl = requestLine[1];
                if (allUrl.contains("?")) {
                    this.url = allUrl.substring(0, allUrl.indexOf("?"));
                    this.params = allUrl.substring(allUrl.indexOf("?") + 1);
                } else {
                    this.url = allUrl;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getParams() {
        return params;
    }

    public String getMethod() {
        return method;
    }
}
