package digitalforensics1;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;
import java.util.Date;

public class image {

    File file;
    Metadata meta;
    Date date;
    GeoLocation location;
    String phone;

    public image(File in) {
        try {
            file = in;

            meta = ImageMetadataReader.readMetadata(file);

            ExifSubIFDDirectory exif = meta.getDirectory(ExifSubIFDDirectory.class);
            date = exif.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

            GpsDirectory gps = meta.getDirectory(GpsDirectory.class);
            location = gps.getGeoLocation();
            
            ExifIFD0Directory exif0= meta.getDirectory(ExifIFD0Directory.class);
            phone=exif0.getString(ExifIFD0Directory.TAG_MAKE)+" "+exif0.getString(ExifIFD0Directory.TAG_MODEL);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void display() {
        System.out.println(file.getName());
        System.out.println(date.toString());
        System.out.println(location.toString());
        System.out.println(phone + "\n---------\n");
    }
}
