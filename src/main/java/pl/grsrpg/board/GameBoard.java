package pl.grsrpg.board;

import com.fasterxml.jackson.core.type.TypeReference;
import pl.grsrpg.Game;
import pl.grsrpg.card.Card;
import pl.grsrpg.card.GameCard;
import pl.grsrpg.config.Config;
import pl.grsrpg.field.BossField;
import pl.grsrpg.field.BossGameField;
import pl.grsrpg.field.Field;
import pl.grsrpg.field.GameField;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.GamePlayerMage;
import pl.grsrpg.player.GamePlayerScout;
import pl.grsrpg.player.GamePlayerWarrior;
import pl.grsrpg.player.Player;
import pl.grsrpg.utils.DiceRoll;
import pl.grsrpg.utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class GameBoard implements Board {
    private String name;
    private List<Field> level1GameFields = new ArrayList<>();
    private List<Field> level2GameFields = new ArrayList<>();
    private List<Field> level3GameFields = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();
    private Player player;

    public GameBoard() {
        loadLevel(level1GameFields, "data/level-1-field-common.yml", "level-1-field-common.yml", Game.getConfig().getLevel1Size());
        addFieldFromFile(level1GameFields, "data/level-1-field-boss.yml", "level-1-field-boss.yml");

        loadLevel(level2GameFields, "data/level-2-field-common.yml", "level-2-field-common.yml", Game.getConfig().getLevel2Size());
        addFieldFromFile(level2GameFields, "data/level-2-field-boss.yml", "level-2-field-boss.yml");

        loadLevel(level3GameFields, "data/level-3-field-common.yml", "level-3-field-common.yml", Game.getConfig().getLevel3Size());
        addFieldFromFile(level3GameFields, "data/level-3-field-boss.yml", "level-3-field-boss.yml");

        loadCards();
    }

    private void loadLevel(List<Field> levelList, String levelFileName, String resource, int size) {
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<GameField> gameFields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {
            });
            Collections.shuffle(gameFields);
            levelList.addAll(gameFields.subList(0, size - 1));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void addFieldFromFile(List<Field> levelList, String levelFileName, String resource) {
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<GameField> gameFields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {
            });
            Collections.shuffle(gameFields);
            System.out.println(gameFields);
            levelList.add(gameFields.get(0));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void loadCards() {
        File cardsFile = IOUtils.openFile("data/cards.yml", "cards.yml");
        try {
            List<GameCard> cards = IOUtils.getMapper().readValue(cardsFile, new TypeReference<>() {});
            this.cards.addAll(cards);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private int nextAction() {
        System.out.println("Possible actions: ");
        System.out.println("1. Display statistics.");
        System.out.println("2. Show your items.");
        System.out.println("3. Roll a dice and move to new field.");
        System.out.print("What is your next move?(default: 3) ");
        return IOUtils.getScanner().nextInt();
    }

    public void startGame() {
        System.out.println("Hello tired traveler in the land of " + Logger.BRIGHT_GREEN + name + Logger.RESET + "!");
        System.out.print("Choose name you want to be known in here: ");
        String name = IOUtils.getScanner().next();
        System.out.println("Professions available: ");
        System.out.println("1. Mage");
        System.out.println(GamePlayerMage.getStartDescription());
        System.out.println("2. Scout");
        System.out.println(GamePlayerScout.getStartDescription());
        System.out.println("3. Warrior");
        System.out.println(GamePlayerWarrior.getStartDescription());
        System.out.print("What is your choice:(default: 3) ");
        int classChoose = IOUtils.getScanner().nextInt();
        System.out.print("You will be " + Logger.YELLOW);
        switch (classChoose) {
            case 1:
                player = new GamePlayerMage(name, level1GameFields.get(0));
                System.out.print("Mage");
                break;
            case 2:
                player = new GamePlayerScout(name, level1GameFields.get(0));
                System.out.print("Scout");
                break;
            case 3:
            default:
                player = new GamePlayerWarrior(name, level1GameFields.get(0));
                System.out.print("Warrior");
                break;

        }
        System.out.println(Logger.RESET + " known as " + Logger.BRIGHT_GREEN + name + Logger.WHITE);
        while (true) {
            int choice = nextAction();
            switch (choice) {
                case 1:
                    System.out.println(player.getInfo());
                    break;
                case 2:
                    System.out.println(player.getItemsInfo());
                    break;
                default:
                case 3:
                    movePlayer();
            }
        }
    }

    private void movePlayer() {
        List<Field> availableFields = getNextFields();
        System.out.println("Available fields to move: ");
        for (int i = 1; i <= availableFields.size(); i++) {
            Field field = availableFields.get(i - 1);
            System.out.println(i + ". " + field.getName() + "\n " + field.getDescription());
        }
        System.out.println("Where you want to move?(default: 1) ");
        int choice = IOUtils.getScanner().nextInt() - 1;
        if (choice >= availableFields.size() || choice < 0) {
            choice = 0;
        }
        Field nextField = availableFields.get(choice);
        player.move(nextField);
    }

    private List<Field> getNextFields() {
        int fieldsToMove = DiceRoll.rollPublic(1, Game.getConfig().getMaxMove());
        List<Field> ret = new ArrayList<>();
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

    private void getLevel1Fields(List<Field> ret, int fieldsToMove) {
        int currentField = level1GameFields.indexOf(player.getCurrentField());
        ret.add(level1GameFields.get(wrap(level1GameFields.size(), currentField, fieldsToMove)));
        ret.add(level1GameFields.get(wrap(level1GameFields.size(), currentField, -fieldsToMove)));
        if (((BossField) level1GameFields.get(level1GameFields.size() - 1)).isDefeated()) {
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

    private void getLevel2Fields(List<Field> ret, int fieldsToMove) {
        int currentField = level2GameFields.indexOf(player.getCurrentField());
        ret.add(level2GameFields.get(wrap(level2GameFields.size(), currentField, fieldsToMove)));
        ret.add(level2GameFields.get(wrap(level2GameFields.size(), currentField, -fieldsToMove)));
        if (((BossField) level2GameFields.get(level2GameFields.size() - 1)).isDefeated()) {
            if (currentField + fieldsToMove % level2GameFields.size() == 0 || currentField - fieldsToMove % level2GameFields.size() == -2) {
                ret.add(level3GameFields.get(0));
            }
        }
        int nextLevelMove = 0;
        if ( currentField <= level2GameFields.size() / 2 && currentField + fieldsToMove > level2GameFields.size() / 2 ) {
            nextLevelMove = currentField + fieldsToMove - (level2GameFields.size()/2) - 1;
        } else if (currentField >= level2GameFields.size() / 2 && currentField - fieldsToMove < level2GameFields.size() / 2) {
            nextLevelMove = currentField - (level2GameFields.size()/2) - fieldsToMove;
        }
        if(nextLevelMove != 0){
            ret.add(level1GameFields.get(wrap(level1GameFields.size(), level1GameFields.size() - 1, nextLevelMove)));
            ret.add(level1GameFields.get(wrap(level1GameFields.size(), level1GameFields.size() - 1, -nextLevelMove)));
        }
    }

    private void getLevel3Fields(List<Field> ret) {
        int currentField = level3GameFields.indexOf(player.getCurrentField());
        if(currentField - 1 == -1){
            ret.add(level2GameFields.get(level2GameFields.size() - 1));
        } else {
            ret.add(level3GameFields.get(currentField - 1));
        }
        ret.add(level3GameFields.get(currentField + 1));
    }


    private int wrap(int listSize, int currentPosition, int nextPosition) {
        return (currentPosition + nextPosition < 0 ? (currentPosition + nextPosition % listSize) + listSize : currentPosition + nextPosition % listSize);
    }
}
