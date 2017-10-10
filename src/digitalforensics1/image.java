/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalforensics1;

import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import java.io.File;
import java.util.Date;

/**
 *
 * @author Caleb
 */
public class image {

    File file;
    Metadata meta;
    Date date;
    GeoLocation location;

    public image(File in) {
        try {
            file = in;

            meta = ImageMetadataReader.readMetadata(file);

            ExifSubIFDDirectory exif = meta.getDirectory(ExifSubIFDDirectory.class);
            date = exif.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);

            GpsDirectory gps = meta.getDirectory(GpsDirectory.class);
            location = gps.getGeoLocation();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void display(){
        System.out.println(file.getName());
        System.out.println(date.toString());
        System.out.println(location.toString()+"\n---------\n");
    }
}
