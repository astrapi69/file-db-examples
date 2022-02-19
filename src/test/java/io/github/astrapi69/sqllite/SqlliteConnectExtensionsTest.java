package io.github.astrapi69.sqllite;

import io.github.astrapi69.jdbc.ConnectionsExtensions;
import io.github.astrapi69.jdbc.CreationState;
import io.github.astrapi69.jdbc.SqlliteExtensions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlliteConnectExtensionsTest {

    @Test
    void getConnection() throws SQLException, ClassNotFoundException {

        // db parameters
        String path = "/home/astrapi69/dev/github/astrapi69/file-db-examples/src/main/resources/sqllite/";
        String databaseName = "test.db";
        Connection connection = SqlliteConnectExtensions.getConnection(path, databaseName);

        CreationState creationState = SqlliteExtensions.newDatabase(path, databaseName);
        assertEquals(creationState, CreationState.ALREADY_EXISTS);

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	capacity real\n"
                + ");";
        ConnectionsExtensions.executeSqlScript(connection, sql, true);
        deleteAllRows(SqlliteConnectExtensions.getConnection(path, databaseName), "warehouses");
        insert(SqlliteConnectExtensions.getConnection(path, databaseName), "Raw Materials", 3000);
        insert(SqlliteConnectExtensions.getConnection(path, databaseName), "Semifinished Goods", 4000);
        insert(SqlliteConnectExtensions.getConnection(path, databaseName), "Finished Goods", 5000);
        selectAll(SqlliteConnectExtensions.getConnection(path, databaseName));
        update(SqlliteConnectExtensions.getConnection(path, databaseName), 3, "Finished Products", 5500);
        selectAll(SqlliteConnectExtensions.getConnection(path, databaseName));
    }

    /**
     * Delete all rows from the given table
     */
    public void deleteAllRows(Connection connection, String tableName) throws SQLException {
        String sql = "DELETE from " +
                tableName +
                ";";

        ConnectionsExtensions.executeSqlScript(connection, sql, true);
    }

    /**
     * select all rows in the warehouses table
     */
    public void selectAll(Connection connection) {
        String sql = "SELECT id, name, capacity FROM warehouses";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get the warehouse whose capacity greater than a specified capacity
     *
     * @param capacity
     */
    public void getCapacityGreaterThan(Connection connection, double capacity) {
        String sql = "SELECT id, name, capacity "
                + "FROM warehouses WHERE capacity > ?";

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setDouble(1, capacity);
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insert(Connection connection, String name, double capacity) {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update data of a warehouse specified by the id
     *
     * @param id
     * @param name     name of the warehouse
     * @param capacity capacity of the warehouse
     */
    public void update(Connection connection, int id, String name, double capacity) {
        String sql = "UPDATE warehouses SET name = ? , "
                + "capacity = ? "
                + "WHERE id = ?";

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, capacity);
            pstmt.setInt(3, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete a warehouse specified by the id
     *
     * @param id
     */
    public void delete(Connection connection, int id) {
        String sql = "DELETE FROM warehouses WHERE id = ?";

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
