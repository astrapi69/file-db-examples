package io.github.astrapi69.h2;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class H2ExampleTest {

    @Test
    void testFileH2DB() throws SQLException, ClassNotFoundException {
        H2Example.testFileH2DB();
    }
}
