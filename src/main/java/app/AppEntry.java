package app;


import app.dao.OrdersDAO;
import app.entities.Order;
import app.utils.DatabaseUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Date;


@SpringBootApplication
public class AppEntry {
    public static void main(final String[] args) {

        SpringApplication.run(AppEntry.class, args);

        try {
            new PrintWriter("spy.log").close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String url = "http://0.0.0.0:8080/";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

