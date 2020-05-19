package pl.grsrpg.logger;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;

public class Logger {
    private static final ColoredPrinter print = new ColoredPrinter.Builder(1, false)
            .foreground(Ansi.FColor.WHITE).build();

    public static void printError(String content){
        print.setForegroundColor(Ansi.FColor.WHITE);
        print.errorPrint("[");
        print.setForegroundColor(Ansi.FColor.RED);
        print.setAttribute(Attribute.BLINK);
        print.errorPrint("ERROR");
        print.setAttribute(Attribute.NONE);
        print.setForegroundColor(Ansi.FColor.WHITE);
        print.errorPrint("] ");
        print.errorPrint(content);
    }
    public static void printWarning(String content){
        print.setForegroundColor(Ansi.FColor.WHITE);
        print.errorPrint("[");
        print.setForegroundColor(Ansi.FColor.YELLOW);
        print.errorPrint("WARNING");
        print.setAttribute(Attribute.NONE);
        print.setForegroundColor(Ansi.FColor.WHITE);
        print.errorPrint("] ");
        print.errorPrint(content);
    }

    public static void printInfo(String content){
        print.setForegroundColor(Ansi.FColor.WHITE);
        print.errorPrint("[");
        print.setForegroundColor(Ansi.FColor.CYAN);
        print.errorPrint("INFO");
        print.setAttribute(Attribute.NONE);
        print.setForegroundColor(Ansi.FColor.WHITE);
        print.errorPrint("] ");
        print.errorPrint(content);
    }
}
