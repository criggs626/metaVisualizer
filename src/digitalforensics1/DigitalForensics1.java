package digitalforensics1;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;
import java.util.Scanner;

public class DigitalForensics1 {

    public static void main(String[] args) {
        try {
            File[] files = readFiles("D:/Phone/Auto Classic/");
            image[] images=new image[files.length];
            for (int i=0;i<files.length;i++) {
                images[i]=new image(files[i]);
                images[i].display();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
