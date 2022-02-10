import AddressBook.AddressBook;
import AddressBook.BuddyInfo;
import org.junit.Test;

public class AddressBookTest {


    private AddressBook addressBook;
    private BuddyInfo buddy1;
    private BuddyInfo buddy2;

//    @Before
//    public void setUp() throws Exception {
//        addressBook = new AddressBook.AddressBook();
//        buddy1 = new AddressBook.BuddyInfo("Harry Potter", "4 Privet Drive", "12345");
//        buddy2 = new AddressBook.BuddyInfo("Sherlock Holmes", "221b Baker St", "56790");
//        addressBook.addBuddy(buddy1);
//        addressBook.addBuddy(buddy2);
//    }
//
//    @org.junit.Test
//    public void size() {
//        assertEquals(2, addressBook.size());
//    }
//
//    @org.junit.Test
//    public void clear() {
//        addressBook.clear();
//        assertEquals(0, addressBook.size());
//    }

    @Test
    public void testAddressBookJPA(){
//        AddressBook.AddressBook addressBook = new AddressBook.AddressBook();
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
//        addressBook.addBuddy(buddy1);
//        addressBook.addBuddy(buddy2);
//        em.persist(addressBook);
//
//        tx.commit();
//
//        // Querying the contents of the database using JPQL query
//        Query q = em.createQuery("SELECT b FROM AddressBook.BuddyInfo b");
//
//        List<AddressBook.BuddyInfo> results = q.getResultList();
//
//        System.out.println("\nBuddies\n----------------");
//        for (AddressBook.BuddyInfo b : results) {
//            System.out.println(b.getId() + " " + b.getName() + " " + b.getPhoneNumber());
//        }
//
//        // Querying the contents of the database using JPQL query
//        Query q1 = em.createQuery("SELECT ab FROM AddressBook.AddressBook ab");
//
//        @SuppressWarnings("unchecked")
//        List<AddressBook.AddressBook> results1 = q1.getResultList();
//
//        System.out.println("\nAddressBooks\n----------------");
//
//        for (AddressBook.AddressBook ab : results1) {
//            System.out.println("address book id: " + ab.getId());
//            for (AddressBook.BuddyInfo b : ab.getBuddyInfoList()){
//                System.out.println(b.getId() + " " + b.getName() + " " + b.getPhoneNumber());
//            }
//
//        }
//        // Closing connection
//        em.close();
//        emf.close();
    }
}