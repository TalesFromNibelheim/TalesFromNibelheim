package pl.grsrpg.utils;

import pl.grsrpg.GRSRPG;
import pl.grsrpg.logger.RPGLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static boolean saveResource(String input, File output){
        ClassLoader classLoader = GRSRPG.class.getClassLoader();

        InputStream stream = classLoader.getResourceAsStream(input);
        if(stream == null){
            RPGLogger.printError(input+" file not found! Exiting...");
            System.exit(1);
            return false;
        }
        File outFolder = output.getParentFile();
        if (!outFolder.exists()) {
            outFolder.mkdirs();
        }
        try {
            output.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream outPutStream = new FileOutputStream(output);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                outPutStream.write(buffer, 0, length);
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
