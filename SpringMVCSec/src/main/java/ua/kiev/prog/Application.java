package ua.kiev.prog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.kiev.prog.security.CustomUser;
import ua.kiev.prog.security.UserRole;
import ua.kiev.prog.security.UserService;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final ContactService contactService, final UserService userService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                userService.addUser(new CustomUser("admin", "d033e22ae348aeb5660fc2140aec35850c4da997", UserRole.ADMIN));
                userService.addUser(new CustomUser("user", "12dea96fec20593566ab75692c9949596833adc9", UserRole.USER));

                Group group = new Group("Test");
                Contact contact;

                contactService.addGroup(group);

                for (int i = 0; i < 13; i++) {
                    contact = new Contact(null, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@test.com");
                    contactService.addContact(contact);
                }
                for (int i = 0; i < 10; i++) {
                    contact = new Contact(group, "Other" + i, "OtherSurname" + i, "7654321" + i, "user" + i + "@other.com");
                    contactService.addContact(contact);
                }
            }
        };
    }
}