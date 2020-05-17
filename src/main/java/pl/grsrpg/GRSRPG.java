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

    public static String getDataPath() {
        return DATA_PATH;
    }

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static ObjectMapper getMapper() {
        return mapper;
    }

    private static Config config;

    public static Config getConfig() {
        return config;
    }

    public static void main(String[] args){
        loadConfig();
    }

    private static void loadConfig(){
        File config = new File(GRSRPG.getDataPath() +"config/config.yml");
        if(!config.exists()){
            if(!Utils.saveResource("config.yml", config)){
                RPGLogger.printError("There was a problem with opening config file! Exiting...");
                System.exit(1);
            }
        }
        try {
            GRSRPG.config = mapper.readValue(config, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(GRSRPG.config.toString());
    }
}
