package no.monorepo.app1;

import no.monorepo.lib1.Lib1;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App1
{
    public static void main( String[] args ) throws IOException
    {
        // Call library function to test monorepo setup
        String message = Lib1.scream("monorepo success");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", (exchange) -> {
            exchange.sendResponseHeaders(200, message.length());
            OutputStream os = exchange.getResponseBody();
            os.write(message.getBytes());
            os.close();
        });

        server.start();
    }
}
