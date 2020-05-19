package pl.grsrpg.gameboard;

import com.fasterxml.jackson.core.type.TypeReference;
import pl.grsrpg.Game;
import pl.grsrpg.gamefield.GameField;
import pl.grsrpg.utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class GameBoard {
    private List<GameField> level1GameFields;
    private List<GameField> level2GameFields;
    private List<GameField> level3GameFields;

    public GameBoard(){
        loadLevel(level1GameFields, "levels/level-1-field-common.yml", "level-1-field-common.yml", Game.getConfig().getLevel1Size());
        addFieldFromFile(level1GameFields, "levels/level-1-field-boss.yml", "level-1-field-boss.yml");

        loadLevel(level2GameFields, "levels/level-2-field-common.yml", "level-2-field-common.yml", Game.getConfig().getLevel2Size());
        addFieldFromFile(level2GameFields, "levels/level-2-field-boss.yml", "level-2-field-boss.yml");

        loadLevel(level3GameFields, "levels/level-3-field-common.yml", "level-3-field-common.yml", Game.getConfig().getLevel3Size());
        addFieldFromFile(level3GameFields, "levels/level-3-field-boss.yml", "level-3-field-boss.yml");
    }

    private void loadLevel(List<GameField> levelList, String levelFileName, String resource, int size){
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<GameField> fields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {});
            Collections.shuffle(fields);
            levelList = new ArrayList<>(fields.subList(0, Game.getConfig().getLevel1Size()-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFieldFromFile(List<GameField> levelList, String levelFileName, String resource){
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<GameField> fields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {});
            Collections.shuffle(fields);
            levelList.add(fields.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
