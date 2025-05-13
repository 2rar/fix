package IT1_2215.fix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import IT1_2215.fix.service.UserService;


import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class FixApplication  {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(FixApplication.class, args);
		openBrowser("http://localhost:8080");
	}

	private static void openBrowser(String url) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
