import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class View2 extends JFrame {

    private AddressBook					addressBook;
    private JMenuItem					createAddressBookButton;
    private JMenuItem					addBuddyButton;
    private JMenuItem					remBuddyButton;

    private JMenuBar					mainMenuBar;

    private JMenu						addressBookMenu;
    private JMenu						buddyInfoMenu;
    private DefaultListModel<BuddyInfo>	listModel;
    private JList<BuddyInfo>			buddyJList;

    public View2() {
        super("Address Book");
        addressBook = new AddressBook();

        this.setResizable(false);
        this.setSize(new Dimension(275, 60));
        this.setLocationRelativeTo(null);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());

        initializeComponents();
    }

    private DefaultListModel<BuddyInfo> getListModel(){
        DefaultListModel<BuddyInfo> model = new DefaultListModel<>();
        for( BuddyInfo val : this.addressBook.getBuddyInfoList())
            model.addElement(val);

        return model;
    }
    public void initializeComponents() {
        mainMenuBar = new JMenuBar();

        addressBookMenu = new JMenu("Address Book");
        buddyInfoMenu = new JMenu("Buddy Info");
        this.listModel = this.getListModel();


        buddyJList = new JList<>(listModel);
        // Handles when an item is selected
        buddyJList.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (((JList<?>) e.getSource()).getSelectedIndex() < 0)
                {
                    remBuddyButton.setEnabled(true);
                    return;
                }
                remBuddyButton.setEnabled(true);

            }
        });

        buddyJList.setPreferredSize(new Dimension(300, 300));

        createAddressBookButton = new JMenuItem("Create");
        createAddressBookButton.setSize(10, 10);
        createAddressBookButton.addActionListener(ae -> createButton_Clicked());

        addressBookMenu.add(createAddressBookButton);


        addBuddyButton = new JMenuItem("Add Buddy");
        addBuddyButton.setSize(10, 10);

        // Set up the action listener for the add buddy button
        addBuddyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String buddyName = (String) JOptionPane.showInputDialog("Please Enter the name of the buddy:");
                String phoneNumber = (String) JOptionPane.showInputDialog("Please Enter the Phone Number of the buddy:");
                addBuddy(new BuddyInfo(buddyName, phoneNumber));

            }
        });

        addBuddyButton.setEnabled(false);
        buddyInfoMenu.add(addBuddyButton);


        remBuddyButton = new JMenuItem("Remove Buddy");
        remBuddyButton.setSize(10, 10);

        // Set up the action listener for the add buddy button
        remBuddyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(buddyJList.getSelectedIndex() < 0)
                    return;
                addressBook.removeBuddy(buddyJList.getSelectedIndex());

            }
        });

        remBuddyButton.setEnabled(false);
        buddyInfoMenu.add(remBuddyButton);

        // Add components to menu bar
        mainMenuBar.add(addressBookMenu);
        mainMenuBar.add(buddyInfoMenu);

        // Add menu bar and list to frame
        this.add(mainMenuBar);
        this.add(buddyJList);
        buddyJList.setVisible(true);
        // Set visible
        this.setVisible(true);
    }

    private void addBuddy(BuddyInfo bI)
    {
        this.addressBook.addBuddy(bI);
    }
    private void createButton_Clicked()
    {
        createAddressBook();

        this.setSize(new Dimension(320, 365));
        this.add(buddyJList);
        buddyJList.setVisible(true);

    }
    private boolean createAddressBook()
    {
        if(buddyJList.getModel().getSize() > 0)
        {
            if(JOptionPane.showConfirmDialog(this, "You have pending buddies. Would you like to discard them?", "Warning!", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
            {
                return false;
            }
        }


        listModel.clear();

        addBuddyButton.setEnabled(true);
        createAddressBookButton.setEnabled(false);


        return true;

    }

    public static void main(String[] args) {
        View2 addressBookView2 = new View2();
    }

}