/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.hotel;

import java.util.Collection;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

/**
 *
 * @author wintermute
 */
public class GuestManagerImplTest {

    static ApplicationContext ctx;
    private GuestManager guestManager;

    public GuestManagerImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        ctx = new AnnotationConfigApplicationContext(AppTest.SpringConfig.class);
    }

    @AfterClass
    public static void tearDownClass() {
        JdbcTemplate jdbc = new JdbcTemplate(ctx.getBean("dataSource", DataSource.class));
        JdbcTestUtils.executeSqlScript(jdbc, new ClassPathResource("deleteTables.sql"), true);
    }

    @Before
    public void setUp() {
        JdbcTemplate jdbc = new JdbcTemplate(ctx.getBean("dataSource", DataSource.class));
        JdbcTestUtils.executeSqlScript(jdbc, new ClassPathResource("createTables.sql"), true);
        
        guestManager = ctx.getBean("guestManager", GuestManager.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateGuest() {
        Guest guest = new Guest(null, "root", "01234567", false);
        guestManager.createGuest(guest);
        Guest result = guestManager.findGuestById(guest.getId());
        assertEquals(guest.getId(), result.getId());
    }

    @Test
    public void testCreateGuestWithNull() throws Exception {
        try {
            guestManager.createGuest(null);
            fail("Did not threw NullPointerException");
        } catch (NullPointerException ex) {
        }
    }

    @Test
    public void testUpdateGuest() {
        Guest guest = new Guest(null, "root", "01234567", false);
        guestManager.createGuest(guest);
        guest.setName("toor");
        guestManager.updateGuest(guest);
        Guest result = guestManager.findGuestById(guest.getId());
        assertEquals(guest.getName(), result.getName());
    }

    @Test
    public void testDeleteGuest() {
        Guest guest = new Guest(null, "root", "01234567", false);
        guestManager.createGuest(guest);
        guestManager.deleteGuest(guest.getId());
        Guest result = guestManager.findGuestById(guest.getId());
        assertNull(result);
    }

    @Test
    public void testListAllGuests() {
        Collection<Guest> guestsBefore = guestManager.listAllGuests();
        Guest guest = new Guest(null, "root", "01234567", false);
        guestManager.createGuest(guest);
        Collection<Guest> guestsAfter = guestManager.listAllGuests();
        assertEquals(guestsAfter.size(), guestsBefore.size() + 1);
    }

    @Test
    public void testFindGuestById() {
        guestManager.createGuest(new Guest(null, "root", "01234567", false));
        Guest guest = guestManager.findGuestById(1);
        Guest result = guestManager.findGuestById(guest.getId());
        assertEquals(guest, result);
    }

    @Test
    public void testFindGuestsByName() {
        guestManager.createGuest(new Guest(null, "root", "01234567", false));
        Guest guest = guestManager.findGuestById(1);
        Collection<Guest> result = guestManager.findGuestsByName("root");
        assertTrue(result.contains(guest));
    }
}
