import bookstore.application.Model.Book;
import bookstore.application.Model.Database;
import static bookstore.application.Model.Database.getDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stu
 */
public class AppInputValidation {
    
    public AppInputValidation() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void LoginValidation() {
        String username = "laiba";
        String password = "laiba";
        //result values generated here
        Database database = getDatabase();
        boolean result = database.loginUser(username, password);
        
        assertEquals(true, result);
    }
}
