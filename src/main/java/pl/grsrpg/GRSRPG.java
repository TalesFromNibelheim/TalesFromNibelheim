package pl.grsrpg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import pl.grsrpg.config.Config;
import pl.grsrpg.logger.RPGLogger;
import pl.grsrpg.utils.Utils;

import java.io.File;
import java.io.IOException;

@Getter
public class GRSRPG {
    private static final String DATA_PATH = System.getProperty("user.dir") + "/";
    private static Config config;

    public static String getDataPath() {
        return DATA_PATH;
    }

    public static void main(String[] args){
        loadConfig();
    }

    private static void loadConfig(){
        File config = new File(GRSRPG.getDataPath() +"config/config.yml");
        if(!config.exists()){
            if(!Utils.saveResource("pl/grsrpg/utils/config.yml", config)){
                RPGLogger.printError("There was a problem with opening config file! Exiting...");
                System.exit(1);
            }
        }
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            GRSRPG.config = mapper.readValue(config, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(GRSRPG.config.toString());
    }
}
