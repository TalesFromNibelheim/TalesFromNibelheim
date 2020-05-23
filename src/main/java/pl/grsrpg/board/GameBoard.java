package pl.grsrpg.board;

import com.fasterxml.jackson.core.type.TypeReference;
import pl.grsrpg.Game;
import pl.grsrpg.card.Card;
import pl.grsrpg.field.Field;
import pl.grsrpg.field.GameField;
import pl.grsrpg.logger.Logger;
import pl.grsrpg.player.GamePlayerMage;
import pl.grsrpg.player.GamePlayerScout;
import pl.grsrpg.player.GamePlayerWarrior;
import pl.grsrpg.player.Player;
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

    public GameBoard(){
        loadLevel(level1GameFields, "data/level-1-field-common.yml", "level-1-field-common.yml", Game.getConfig().getLevel1Size());
        addFieldFromFile(level1GameFields, "data/level-1-field-boss.yml", "level-1-field-boss.yml");

        loadLevel(level2GameFields, "data/level-2-field-common.yml", "level-2-field-common.yml", Game.getConfig().getLevel2Size());
        addFieldFromFile(level2GameFields, "data/level-2-field-boss.yml", "level-2-field-boss.yml");

        loadLevel(level3GameFields, "data/level-3-field-common.yml", "level-3-field-common.yml", Game.getConfig().getLevel3Size());
        addFieldFromFile(level3GameFields, "data/level-3-field-boss.yml", "level-3-field-boss.yml");

        loadCards();
    }

    private void loadLevel(List<Field> levelList, String levelFileName, String resource, int size){
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<GameField> gameFields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {});
            Collections.shuffle(gameFields);
            levelList.addAll(gameFields.subList(0, size-1));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void addFieldFromFile(List<Field> levelList, String levelFileName, String resource){
        File levelFile = IOUtils.openFile(levelFileName, resource);
        try {
            List<GameField> gameFields = IOUtils.getMapper().readValue(levelFile, new TypeReference<>() {});
            Collections.shuffle(gameFields);
            System.out.println(gameFields);
            levelList.add(gameFields.get(0));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void loadCards(){
        File cardsFile = IOUtils.openFile("data/cards.yml", "cards.yml");
        try {
            this.cards.addAll(IOUtils.getMapper().readValue(cardsFile, new TypeReference<>() {}));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void startGame(){
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
        System.out.print("What is your choice: ");
        int classChoose  = IOUtils.getScanner().nextInt();
        System.out.print("You will be "+Logger.YELLOW);
        switch (classChoose){
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
        System.out.println(Logger.RESET+" known as "+Logger.BRIGHT_GREEN+name+Logger.WHITE);
        while (true){
            int choice = nextAction();
            switch (choice){
                case 1:
                    
            }
        }
    }

    public int nextAction(){
        System.out.println("Possible actions: ");
        System.out.println("1. Display statistics.");
        System.out.println("2. Show your items.");
        System.out.println("3. Roll a dice and move to new field.");
        System.out.print("What is your next move? ");
        return IOUtils.getScanner().nextInt();
    }
}
