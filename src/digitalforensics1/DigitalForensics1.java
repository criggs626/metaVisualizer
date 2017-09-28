package digitalforensics1;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class DigitalForensics1 {

    public static void main(String[] args) {
        try {
            File imagesDir = new File("C:/Users/madsc/Desktop/images/");

            if (imagesDir.isDirectory()) {
                File[] files = imagesDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg");
                    }
                });
                for (File image : files) {
                    System.out.println("\n********** NEW FILE " + image.getName() + "**********\n");
                    Metadata metadata = ImageMetadataReader.readMetadata(image);
                    for (Directory directory : metadata.getDirectories()) {
                        for (Tag tag : directory.getTags()) {
                            System.out.println(tag);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
