package pl.grsrpg.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import pl.grsrpg.Game;
import pl.grsrpg.logger.RPGLogger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static ObjectMapper getMapper() {
        return mapper;
    }

    private static final String DATA_PATH = System.getProperty("user.dir") + "/";

    public static String getDataPath() {
        return DATA_PATH;
    }

    public static boolean saveResource(String input, File output){
        ClassLoader classLoader = Game.class.getClassLoader();

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

    public static File openFile(String path, String resource){
        File file = new File(getDataPath() +path);
        if(!file.exists()){
            if(!IOUtils.saveResource(resource, file)){
                RPGLogger.printError("There was a problem with opening"+resource+"file! Exiting...");
                System.exit(1);
            }
        }
        return file;
    }

}
