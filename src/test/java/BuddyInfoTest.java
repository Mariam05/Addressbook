import AddressBook.BuddyInfo;
import org.junit.Test;

public class BuddyInfoTest {

    private BuddyInfo buddy;
    private BuddyInfo secondBuddy;
    private BuddyInfo copyBuddy;

//    @Before
//    public void setUp() {
//        buddy = new AddressBook.BuddyInfo("Harry Potter", "4 Privet Drive", "12345");
//        secondBuddy = new AddressBook.BuddyInfo("Harry Potter", "4 Privet Drive", "12345");
//        copyBuddy = new AddressBook.BuddyInfo(buddy);
//    }
//
//    @Test
//    public void testBuddyInfoCopyConstructor() {
//        assertTrue(buddy.isEquals(copyBuddy));
//    }

//    @Test
//    public void testIsEquals() {
//        assertTrue(buddy.isEquals(secondBuddy));
//    }
//
//    @Test
//    public void testGreeting() {
//        assertEquals("Hey there bud!",buddy.greetBuddy());
//    }
//
//    @Test
//    public void testSetAge() {
//        buddy.setAge(20);
//        assertEquals(20, buddy.getAge());
//    }

    @Test
    public void testJPA(){
//        AddressBook.BuddyInfo buddy1 = new AddressBook.BuddyInfo();
//        buddy1.setName("ArthurReid");
//        buddy1.setPhoneNumber("1234567890");
//
//        AddressBook.BuddyInfo buddy2 = new AddressBook.BuddyInfo();
//        buddy2.setName("Francine Frensky");
//        buddy2.setPhoneNumber("1234567890");
//
//        // Connecting to the database through EntityManagerFactory
//        // connection details loaded from persistence.xml
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
//        EntityManager em = emf.createEntityManager();
//
//        // Creating a new transaction
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        // Persisting the product entity objects
//        em.persist(buddy1);
//        em.persist(buddy2);
//
//        tx.commit();
//
//        // Querying the contents of the database using JPQL query
//        Query q = em.createQuery("SELECT b FROM AddressBook.BuddyInfo b");
//
//        List<AddressBook.BuddyInfo> buddies = q.getResultList();
//
//        System.out.println("List of Buddies\n----------------");
//
//        for (AddressBook.BuddyInfo b : buddies) {
//            System.out.println(b.getId() + " " +  b.getName() + " " + b.getPhoneNumber());
//        }
//
//        // Closing connection
//        em.close();
//
//        emf.close();
    }
}