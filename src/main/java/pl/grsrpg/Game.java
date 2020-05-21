package pl.grsrpg;

import lombok.Getter;
import pl.grsrpg.config.Config;
import pl.grsrpg.board.Board;
import pl.grsrpg.board.GameBoard;
import pl.grsrpg.utils.IOUtils;

import java.io.File;
import java.io.IOException;

@Getter
public class Game {
    private static Config config;
    public static Config getConfig() {
        return config;
    }

    private final Board board;

    public Game() {
        this.board = new GameBoard();
    }

    public static void main(String[] args){
        loadConfig();
        //Game game = new Game();
    }

    private static void loadConfig(){
        File config = IOUtils.openFile("config/config.yml", "config.yml");
        try{
            Game.config = IOUtils.getMapper().readValue(config, Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
