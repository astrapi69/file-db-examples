package io.github.astrapi69.h2;

import io.github.astrapi69.file.delete.DeleteFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.jdbc.h2.H2ConnectionsExtensions;
import org.h2.tools.Server;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class H2ExampleTest {

    @Test
    void testFileH2DB() throws SQLException, ClassNotFoundException, IOException {

        String path;
        String databaseName;
        File databaseDirectory;
        File srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
        databaseDirectory = PathFinder.getRelativePath(srcTestResourcesDir, "h2");
        path = databaseDirectory.getAbsolutePath();
        databaseName = "testh2";

        Server server = H2Launcher.newServer().start();
        Connection connection = H2ConnectionsExtensions.getConnection(
                path,
                databaseName);
        assertNotNull(connection);
        server.stop();
        DeleteFileExtensions.delete(databaseDirectory);
    }
}
