package pl.grsrpg.gameboard;

import com.fasterxml.jackson.core.type.TypeReference;
import pl.grsrpg.GRSRPG;
import pl.grsrpg.gamefield.GameField;
import pl.grsrpg.logger.RPGLogger;
import pl.grsrpg.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameBoard {
    private List<GameField> level1GameFields;
    private List<GameField> level2GameFields;
    private List<GameField> level3GameFields;

    public GameBoard(){
        File level1CommonFile = new File(GRSRPG.getDataPath() +"levels/level-1-field-common.yml");
        loadLevel(level1GameFields, level1CommonFile, "level-1-field-common.yml", GRSRPG.getConfig().getLevel1Size());
        File level1BossFile = new File(GRSRPG.getDataPath() +"levels/level-1-field-boss.yml");
        addFieldFromFile(level1GameFields, level1BossFile, "level-1-field-boss.yml");

        File level2CommonFile = new File(GRSRPG.getDataPath() +"levels/level-2-field-common.yml");
        loadLevel(level2GameFields, level2CommonFile, "level-2-field-common.yml", GRSRPG.getConfig().getLevel2Size());
        File level2BossFile = new File(GRSRPG.getDataPath() +"levels/level-2-field-boss.yml");
        addFieldFromFile(level2GameFields, level2BossFile, "level-2-field-boss.yml");

        File level3CommonFile = new File(GRSRPG.getDataPath() +"levels/level-3-field-common.yml");
        loadLevel(level3GameFields, level3CommonFile, "level-3-field-common.yml", GRSRPG.getConfig().getLevel3Size());
        File level3BossFile = new File(GRSRPG.getDataPath() +"levels/level-3-field-boss.yml");
        addFieldFromFile(level3GameFields, level3BossFile, "level-3-field-boss.yml");
    }

    private void loadLevel(List<GameField> levelList, File levelFile, String resource, int size){
        if(!levelFile.exists()){
            if(!Utils.saveResource(resource, levelFile)){
                RPGLogger.printError("There was a problem with opening "+resource+" file! Exiting...");
                System.exit(1);
            }
        }
        try {
            List<GameField> fields = GRSRPG.getMapper().readValue(levelFile, new TypeReference<>() {});
            Collections.shuffle(fields);
            levelList = new ArrayList<>(fields.subList(0, GRSRPG.getConfig().getLevel1Size()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFieldFromFile(List<GameField> levelList, File levelFile, String resource){
        if(!levelFile.exists()){
            if(!Utils.saveResource(resource, levelFile)){
                RPGLogger.printError("There was a problem with opening "+resource+" file! Exiting...");
                System.exit(1);
            }
        }
        try {
            List<GameField> fields = GRSRPG.getMapper().readValue(levelFile, new TypeReference<>() {});
            Collections.shuffle(fields);
            levelList.add(fields.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
