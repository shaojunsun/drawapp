package drawapp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by oaev on 23.10.16.
 */
public class FileManager {

    public static void imageToFile(BufferedImage image, String fileName) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        byte[] byteArr = baos.toByteArray();
        Files.write(new File(fileName).toPath(), byteArr);
    }

    public static BufferedImage fileToImage(File file) throws IOException {

        return ImageIO.read(file);
    }
}
