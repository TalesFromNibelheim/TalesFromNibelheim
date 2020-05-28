package pl.grsrpg.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Getter;
import pl.grsrpg.Game;
import pl.grsrpg.card.ICard;
import pl.grsrpg.card.Card;
import pl.grsrpg.field.BossField;
import pl.grsrpg.field.IBossIField;
import pl.grsrpg.field.IField;
import pl.grsrpg.field.Field;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.PlayerMage;
import pl.grsrpg.player.PlayerScout;
import pl.grsrpg.player.PlayerWarrior;
import pl.grsrpg.player.IPlayer;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Getter
public class Board implements IBoard {
    private String name;
    private final List<IField> level1GameFields = new ArrayList<>();
    private final List<IField> level2GameFields = new ArrayList<>();
    private final List<IField> level3GameFields = new ArrayList<>();
    private final List<ICard> cards = new ArrayList<>();
    private IPlayer player;

    public Board() {
        loadLevel(level1GameFields, "data/level-1-field-common.yml", "level-1-field-common.yml", Game.getConfig().getLevel1Size());
        addFieldFromFile(level1GameFields, "data/level-1-field-boss.yml", "level-1-field-boss.yml");

        loadLevel(level2GameFields, "data/level-2-field-common.yml", "level-2-field-common.yml", Game.getConfig().getLevel2Size());
        addFieldFromFile(level2GameFields, "data/level-2-field-boss.yml", "level-2-field-boss.yml");

        loadLevel(level3GameFields, "data/level-3-field-common.yml", "level-3-field-common.yml", Game.getConfig().getLevel3Size());
        addFieldFromFile(level3GameFields, "data/level-3-field-boss.yml", "level-3-field-boss.yml");

        loadCards();
    }

    private void loadLevel(List<IField> levelList, String levelFileName, String resource, int size) {
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<Field> gameFields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {
            });
            Collections.shuffle(gameFields);
            levelList.addAll(gameFields.subList(0, size - 1));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void addFieldFromFile(List<IField> levelList, String levelFileName, String resource) {
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<BossField> gameFields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {
            });
            Collections.shuffle(gameFields);
            levelList.add(gameFields.get(0));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void loadCards() {
        File cardsFile = IOUtils.openFile("data/cards.yml", "cards.yml");
        try {
            List<Card> cards = IOUtils.getMapper().readValue(cardsFile, new TypeReference<>() {
            });
            Collections.shuffle(cards);
            this.cards.addAll(cards);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void startGame() {
        System.out.println("Hello tired traveler in the land of " + Logger.BRIGHT_GREEN + name + Logger.RESET + "!");
        System.out.print("Choose name you want to be known in here: ");
        String name = IOUtils.getScanner().next();
        System.out.println("Professions available: ");
        System.out.println(Logger.YELLOW + "1. " + Logger.CYAN + "Mage" + Logger.RESET);
        System.out.println(PlayerMage.getStartDescription());
        System.out.println(Logger.YELLOW + "2. " + Logger.CYAN + "Scout" + Logger.RESET);
        System.out.println(PlayerScout.getStartDescription());
        System.out.println(Logger.YELLOW + "3. " + Logger.CYAN + "Warrior" + Logger.RESET);
        System.out.println(PlayerWarrior.getStartDescription());
        System.out.print("What is your choice:(default: 3) ");
        int classChoose = IOUtils.getScanner().nextInt();
        System.out.print("You will be " + Logger.YELLOW);
        switch (classChoose) {
            case 1:
                player = new PlayerMage(name);
                System.out.print("Mage");
                break;
            case 2:
                player = new PlayerScout(name);
                System.out.print("Scout");
                break;
            case 3:
            default:
                player = new PlayerWarrior(name);
                System.out.print("Warrior");
                break;

        }
        System.out.println(Logger.RESET + " known as " + Logger.BRIGHT_GREEN + name + Logger.RESET);
        player.recalculateAttributes();
    }

    public void gameLoop() {
        while (true) {
            int choice = nextAction();
            switch (choice) {
                case 1:
                    System.out.println(player.getInfo());
                    break;
                case 2:
                    System.out.println(player.getCardsInfo());
                    break;
                default:
                case 3:
                    movePlayer();
                    break;
                case 4:
                    saveAndQuit();
                    System.exit(0);
            }
        }
    }

    private int nextAction() {
        System.out.println();
        System.out.println("Possible actions: ");
        System.out.println(Logger.YELLOW + "1. " + Logger.RESET + "Display statistics.");
        System.out.println(Logger.YELLOW + "2. " + Logger.RESET + "Show your items.");
        System.out.println(Logger.YELLOW + "3. " + Logger.RESET + "Roll a dice and move to new field.");
        System.out.println(Logger.YELLOW + "4. " + Logger.RESET + "Save and quit.");
        System.out.print("What is your next move?(default: 3) ");
        int choice = IOUtils.getScanner().nextInt();
        System.out.println();
        return choice;
    }

    private void saveAndQuit() {
        System.out.println(Logger.CYAN + "See you later!" + Logger.RESET);
        try {
            IOUtils.getMapper().enable(SerializationFeature.INDENT_OUTPUT);
            IOUtils.getMapper().writeValue(new File(IOUtils.getDataPath() + "/data/save.yml"), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void movePlayer() {
        IField[] availableFields = getNextFields().toArray(new IField[0]);
        System.out.println("Available fields to move: ");
        for (int i = 1; i <= availableFields.length; i++) {
            IField field = availableFields[i - 1];
            System.out.print(Logger.YELLOW + i + ". ");
            if(field instanceof IBossIField)
                System.out.print(Logger.RED+"BOSS ");
            System.out.println(Logger.RESET + "Name: " + Logger.CYAN + field.getName() + Logger.RESET + "\n   Description: " + field.getDescription());
        }
        System.out.print("Where you want to move?(default: 1) ");
        int choice = IOUtils.getScanner().nextInt() - 1;
        if (choice > availableFields.length || choice < 0) {
            choice = 0;
        }
        IField nextField = availableFields[choice];
        int mapLevel = 1;
        int fieldNumber = level1GameFields.indexOf(nextField);
        if (fieldNumber == -1 && level2GameFields.contains(nextField)) {
            mapLevel = 2;
            fieldNumber = level2GameFields.indexOf(nextField);
        } else if(fieldNumber == -1){
            mapLevel = 3;
            fieldNumber = level3GameFields.indexOf(nextField);
        }
        player.move(mapLevel, fieldNumber, nextField);
    }

    private Set<IField> getNextFields() {
        int fieldsToMove = DiceRoll.rollPublic(1, Game.getConfig().getMaxMove(),player.getAddPoint());
        Set<IField> ret = new HashSet<>();
        switch (player.getCurrentMapLevel()) {
            case 1:
                getLevel1Fields(ret, fieldsToMove);
                break;
            case 2:
                getLevel2Fields(ret, fieldsToMove);
                break;
            case 3:
                getLevel3Fields(ret);
                break;
        }
        return ret;
    }

    private void getLevel1Fields(Set<IField> ret, int fieldsToMove) {
        int currentField = player.getCurrentField();
        ret.add(level1GameFields.get(wrap(level1GameFields.size(), currentField, fieldsToMove)));
        ret.add(level1GameFields.get(wrap(level1GameFields.size(), currentField, -fieldsToMove)));
        if (((IBossIField) level1GameFields.get(level1GameFields.size() - 1)).isDefeated()) {
            int nextLevelMove = 0;
            if (currentField + fieldsToMove % level1GameFields.size() >= level1GameFields.size()) {
                nextLevelMove = currentField + fieldsToMove % level1GameFields.size();
            } else if (currentField - fieldsToMove % level1GameFields.size() <= -2) {
                nextLevelMove = currentField - fieldsToMove % level1GameFields.size() + 1;
            }
            if (nextLevelMove != 0) {
                ret.add(level2GameFields.get(wrap(level2GameFields.size(), level2GameFields.size() / 2, nextLevelMove)));
                ret.add(level2GameFields.get(wrap(level2GameFields.size(), level2GameFields.size() / 2, -nextLevelMove)));
            }
        }
    }

    private void getLevel2Fields(Set<IField> ret, int fieldsToMove) {
        int currentField = player.getCurrentField();
        ret.add(level2GameFields.get(wrap(level2GameFields.size(), currentField, fieldsToMove)));
        ret.add(level2GameFields.get(wrap(level2GameFields.size(), currentField, -fieldsToMove)));
        if (((IBossIField) level2GameFields.get(level2GameFields.size() - 1)).isDefeated()) {
            if (currentField + fieldsToMove % level2GameFields.size() == 0 || currentField - fieldsToMove % level2GameFields.size() == -2) {
                ret.add(level3GameFields.get(0));
            }
        }
        int nextLevelMove = 0;
        if (currentField <= level2GameFields.size() / 2 && currentField + fieldsToMove > level2GameFields.size() / 2) {
            nextLevelMove = currentField + fieldsToMove - (level2GameFields.size() / 2) - 1;
        } else if (currentField >= level2GameFields.size() / 2 && currentField - fieldsToMove < level2GameFields.size() / 2) {
            nextLevelMove = currentField - (level2GameFields.size() / 2) - fieldsToMove;
        }
        if (nextLevelMove != 0) {
            ret.add(level1GameFields.get(wrap(level1GameFields.size(), level1GameFields.size() - 1, nextLevelMove)));
            ret.add(level1GameFields.get(wrap(level1GameFields.size(), level1GameFields.size() - 1, -nextLevelMove)));
        }
    }

    private void getLevel3Fields(Set<IField> ret) {
        int currentField = player.getCurrentField();
        if (currentField - 1 == -1) {
            ret.add(level2GameFields.get(level2GameFields.size() - 1));
        } else {
            ret.add(level3GameFields.get(currentField - 1));
        }
        ret.add(level3GameFields.get(currentField + 1));
    }


    private int wrap(int listSize, int currentPosition, int nextPosition) {
        return ( (currentPosition + nextPosition) % listSize  < 0 ? ( (currentPosition + nextPosition) % listSize) + listSize : (currentPosition + nextPosition) % listSize);
    }

    @JsonIgnore
    public IField getField(int level, int filedNumber){
        switch (level) {
            case 1:
                return level1GameFields.get(filedNumber);
            case 2:
                return level2GameFields.get(filedNumber);
            case 3:
                return level3GameFields.get(filedNumber);
        }
        return null;
    }

    @JsonIgnore
    public ICard drawCard(){
        if(cards.isEmpty()){
            loadCards();
        }
        return cards.remove(0);
    }
}
