package pl.grsrpg.logger;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;

public class RPGLogger {
    private static final ColoredPrinter errorPrint = new ColoredPrinter.Builder(1, false)
            .foreground(Ansi.FColor.RED).build();

    public static void printError(String content){
        errorPrint.setForegroundColor(Ansi.FColor.WHITE);
        errorPrint.errorPrint("[");
        errorPrint.setForegroundColor(Ansi.FColor.RED);
        errorPrint.setAttribute(Attribute.BLINK);
        errorPrint.errorPrint("ERROR");
        errorPrint.setAttribute(Attribute.NONE);
        errorPrint.setForegroundColor(Ansi.FColor.WHITE);
        errorPrint.errorPrint("] ");
        errorPrint.setForegroundColor(Ansi.FColor.RED);
        errorPrint.errorPrint(content);
    }
}
