package pl.grsrpg.board;

import pl.grsrpg.card.ICard;
import pl.grsrpg.field.IField;

public interface IBoard {
    void startGame();

    void gameLoop();

    IField getField(int level, int filedNumber);

    ICard drawCard();
}
