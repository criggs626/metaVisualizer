package digitalforensics1;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class DigitalForensics1 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Meta Visualizer");

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select the folder with the images");
        File directory = chooser.showDialog(primaryStage);

        File[] files = readFiles(directory.toString());
        image[] images = new image[files.length];
        for (int i = 0; i < files.length; i++) {
            images[i] = new image(files[i]);
            images[i].display();
        }

        MyBrowser myBrowser = new MyBrowser();
        StackPane root = new StackPane();
        root.getChildren().add(myBrowser);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    class MyBrowser extends Region {

        public MyBrowser() {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            final URL urlGoogleMaps = getClass().getResource("maps.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setJavaScriptEnabled(true);
            webEngine.executeScript("var marker = new google.maps.Marker({position: {lat: 28.04550827777778, lng: -81.95190458333333},map: map,title: 'Hello World!'});");

            getChildren().add(webView);

        }
    }

    public static File[] readFiles(String dir) {
        File imagesDir = new File(dir);
        if (imagesDir.isDirectory()) {
            File[] files = imagesDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg");
                }
            });
            return files;
        } else {
            File[] files = {};
            return files;
        }
    }
}
