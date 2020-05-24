package pl.grsrpg;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import pl.grsrpg.card.GameCardItem;
import pl.grsrpg.config.Config;
import pl.grsrpg.board.Board;
import pl.grsrpg.board.GameBoard;
import pl.grsrpg.player.GamePlayerWarrior;
import pl.grsrpg.utils.IOUtils;

import java.io.File;
import java.io.IOException;

@Getter
public class Game {
    private static Config config;
    public static Config getConfig() {
        return config;
    }

    private Board board;

    public Game() {
        if(!loadGame()){
            this.board = new GameBoard();
            this.board.startGame();
        }
        this.board.gameLoop();
    }

    public static void main(String[] args){
        loadConfig();
        Game game = new Game();
    }

    private static void loadConfig(){
        File config = IOUtils.openFile("config/config.yml", "config.yml");
        try{
            Game.config = IOUtils.getMapper().readValue(config, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadGame(){
        File save = new File(IOUtils.getDataPath()+"/data/save.yml");
        if(save.exists()){
            System.out.print("Found previous game save, load it?[Y/n] ");
            String choice = IOUtils.getScanner().next();
            if(choice.equals("n"))
                return false;
            try{
                this.board = IOUtils.getMapper().readValue(save, GameBoard.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
