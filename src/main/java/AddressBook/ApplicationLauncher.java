package AddressBook;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationLauncher {
    public void launch() {
        String[] contextPaths = new String[] {"appcontext.xml"};
        new ClassPathXmlApplicationContext(contextPaths);
    }
}
