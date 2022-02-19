package io.github.astrapi69.h2;

import org.h2.tools.Server;

import java.sql.SQLException;

public class H2Launcher {

    private static void startDB() throws SQLException {
        newServer().start();
    }

    public static Server newServer() throws SQLException {
        return Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists");
    }

}
