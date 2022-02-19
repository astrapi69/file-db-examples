package io.github.astrapi69.sqllite;

import io.github.astrapi69.jdbc.CreationState;
import io.github.astrapi69.jdbc.SqlliteExtensions;
import lombok.NonNull;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlliteConnectExtensions {

    public static CreationState newDatabase(String directoryPath, String dbFileName)
            throws SQLException, ClassNotFoundException
    {
        File file = new File(directoryPath, dbFileName);
        String url = SqlliteExtensions.URL_PREFIX + directoryPath + dbFileName;
        if (file.exists())
        {
            return CreationState.ALREADY_EXISTS;
        }
        getConnection(directoryPath, dbFileName);
        return CreationState.CREATED;
    }
    /**
     * Gets the sqllite connection.
     *
     * @param path
     *            the path
     * @param databaseName
     *            the database name
     * @return the sqllite connection
     * @throws SQLException
     *             is thrown if a database access error occurs or this method is called on a closed
     *             connection
     */
    public static Connection getConnection(final @NonNull String path,
                                           final @NonNull String databaseName) throws SQLException, ClassNotFoundException {
        return SqlliteExtensions.getConnection(path, databaseName);
    }

}
