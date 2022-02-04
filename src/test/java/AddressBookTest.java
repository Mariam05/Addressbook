import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class AddressBookTest {


    private AddressBook addressBook;
    private BuddyInfo buddy1;
    private BuddyInfo buddy2;

//    @Before
//    public void setUp() throws Exception {
//        addressBook = new AddressBook();
//        buddy1 = new BuddyInfo("Harry Potter", "4 Privet Drive", "12345");
//        buddy2 = new BuddyInfo("Sherlock Holmes", "221b Baker St", "56790");
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
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo();
        buddy1.setName("ArthurReid");
        buddy1.setPhoneNumber("1234567890");

        BuddyInfo buddy2 = new BuddyInfo();
        buddy2.setName("Francine Frensky");
        buddy2.setPhoneNumber("1234567890");

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Persisting the product entity objects
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        em.persist(addressBook);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT b FROM BuddyInfo b");

        List<BuddyInfo> results = q.getResultList();

        System.out.println("\nBuddies\n----------------");
        for (BuddyInfo b : results) {
            System.out.println(b.getId() + " " + b.getName() + " " + b.getPhoneNumber());
        }

        // Querying the contents of the database using JPQL query
        Query q1 = em.createQuery("SELECT ab FROM AddressBook ab");

        @SuppressWarnings("unchecked")
        List<AddressBook> results1 = q1.getResultList();

        System.out.println("\nAddressBooks\n----------------");

        for (AddressBook ab : results1) {
            System.out.println("address book id: " + ab.getId());
            for (BuddyInfo b : ab.getBuddyInfoList()){
                System.out.println(b.getId() + " " + b.getName() + " " + b.getPhoneNumber());
            }

        }
        // Closing connection
        em.close();
        emf.close();
    }
}