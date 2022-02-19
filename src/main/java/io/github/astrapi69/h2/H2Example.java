package io.github.astrapi69.h2;

import io.github.astrapi69.jdbc.H2ConnectionsExtensions;

import java.sql.Connection;
import java.sql.SQLException;

public class H2Example {

    public static void testFileH2DB() throws SQLException, ClassNotFoundException {
        H2Launcher.newServer().start();
        Connection connection = H2ConnectionsExtensions.getConnection(
                "~/dev/github/astrapi69/file-db-examples/src/main/resources/h2/",
                "testh2", "sa", "");
        System.out.println("Connection Established: "
                + connection.getMetaData().getDatabaseProductName() + "/" + connection.getCatalog());
    }
}
