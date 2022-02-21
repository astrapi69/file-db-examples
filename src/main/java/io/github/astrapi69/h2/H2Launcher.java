package io.github.astrapi69.h2;

import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Launcher
{

    public static void start(Server server) throws SQLException
    {
        server.start();
    }


    public static void stop(Server server) throws SQLException
    {
        server.stop();
    }

    public static Server newServer() throws SQLException
    {
        return newTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists");
    }

    public static Server newTcpServer(String... args) throws SQLException
    {
        return Server.createTcpServer(args);
    }

}
