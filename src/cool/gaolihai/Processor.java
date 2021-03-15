package cool.gaolihai;

@FunctionalInterface
public interface Processor {
    void callback(Request request, Response response);
}
