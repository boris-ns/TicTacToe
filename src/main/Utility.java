package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class Utility {

    private Utility() {
        throw new AssertionError();
    }

    public static Image loadImageFromResource(String path) {
        try {
            return ImageIO.read(new FileInputStream(new File(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
