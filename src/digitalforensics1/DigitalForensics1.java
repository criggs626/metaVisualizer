package digitalforensics1;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class DigitalForensics1 extends Application {

    WebEngine webEngine;

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
        BorderPane root = new BorderPane();
        root.setCenter(myBrowser);

        HBox frame = new HBox();
        frame.setSpacing(10);
        for (image file : images) {
            ImageView iv = new ImageView();
            Image im = new Image("file:" + file.getDir(), 200, 0, false, false);
            iv.setFitWidth(100);
            iv.setFitHeight(100);
            iv.setImage(im);
            frame.getChildren().add(iv);
            VBox metaInfo = new VBox();
            Text temp = new Text(file.getInfo());
            frame.getChildren().add(temp);
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(frame);
        root.setBottom(sp);

        Button addPins = new Button("Add Pins");
        root.setTop(addPins);
        addPins.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (int i = 0; i < images.length; i++) {
                    root.getChildren().remove(addPins);
                    if (!images[i].getLocation().equals("34.683, -41.051")) {
                        webEngine.executeScript("addPin(" + images[i].getLocation() + ",'" + images[i].getPath() + "','" + i + "')");
                    }
                }
            }
        });

        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
    }

    class MyBrowser extends Region {

        public MyBrowser() {
            WebView webView = new WebView();
            webEngine = webView.getEngine();
            final URL urlGoogleMaps = getClass().getResource("maps.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setJavaScriptEnabled(true);
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
