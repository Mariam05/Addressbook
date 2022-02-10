package AddressBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements AddressBookListener {

    private AddressBook addressBook;
    private DefaultListModel<BuddyInfo> buddyListModel;
    private JList<BuddyInfo> buddyJList;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu menu, submenu;
    private JMenuItem menuItem;
    private AddressBookController controller;

    public MainFrame(AddressBookController controller) {

        this.controller = controller;
        this.addressBook = controller.getAddressBook();
        controller.subscribeToModel(this);

        this.setSize(new Dimension(300, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        ArrayList<BuddyInfo> buddies = addressBook.getBuddyInfoList();
        buddyJList = new JList<>(buddyListModel = new DefaultListModel<>());
        for (BuddyInfo bud : buddies){
            buddyListModel.addElement(bud);
        }
        setup();
        this.add(new JScrollPane(buddyJList));
        setVisible(true);

    }

    private void setup(){
        //Set up the menu
        menuBar = new JMenuBar();
        menu = new JMenu("AddressBook");
        menuBar.add(menu);
        menuItem = new JMenuItem("Create Address Book");
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null, "A new address book will be created. All buddies will be removed.");
                controller.clearBook();
            }

        });

        menuItem = new JMenuItem("Add Buddy");
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JPanel optionPanel = new JPanel();

                JTextField name = new JTextField(10);
                JTextField number = new JTextField(10);

                optionPanel.add(new JLabel("Name: "));
                optionPanel.add(name);
                optionPanel.add(new JLabel("PhoneNumber: "));
                optionPanel.add(number);

                int result = JOptionPane.showConfirmDialog(null, optionPanel, "New Buddy", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    BuddyInfo newBuddy = new BuddyInfo(name.getText(), number.getText());
                    controller.addBud(newBuddy);

                }
            }

        });


        menuItem = new JMenuItem("Remove Buddy");
        menu.add(menuItem);
        //This can also be implemented by adding an action listener on the list that will remove the one that is selected
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String response = JOptionPane.showInputDialog("Which buddy would you like to remove? ", JOptionPane.OK_CANCEL_OPTION);
                int index = Integer.parseInt(response);
                controller.removeBud(index);
            }

        });

        add(menuBar, BorderLayout.NORTH);
        add(buddyJList);
        setVisible(true);
    }

    @Override
    public void handleAddressBookEvent(AddressBookEvent e) {
        ArrayList<BuddyInfo> newBuds = e.getUpdatedList();
        buddyListModel.clear();
        for (BuddyInfo bud : newBuds){
            buddyListModel.addElement(bud);
        }
        System.out.println("handling address book event");
    }


    public static void main(String[] args){
//        AddressBook.AddressBook addressBook = new AddressBook.AddressBook();
//        AddressBook.MainFrame view = new AddressBook.MainFrame(new AddressBook.AddressBookController(addressBook));

        ApplicationLauncher apl = new ApplicationLauncher();
        apl.launch();
    }


//    private void init() {
//        System.out.println("Mainframe init");
//        AddressBook.AddressBook addressBook = new AddressBook.AddressBook();
//        AddressBook.MainFrame view = new AddressBook.MainFrame(new AddressBook.AddressBookController(addressBook));
//    }
}
