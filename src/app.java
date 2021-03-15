import cool.gaolihai.Angie;

public class app {
    public static void main(String[] args) {
        Angie app = new Angie();

        app.use("/test", (req, res) -> {
            res.setStatus(200)
                    .setHeaders("Content-Type", "text/html")
                    .send("<h1> Hello, web framework! </h1>");
        });

        app.listen(80);
    }
}
