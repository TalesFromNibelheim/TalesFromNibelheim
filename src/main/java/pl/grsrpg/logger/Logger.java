package pl.grsrpg.logger;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;

public class Logger {
    private static final ColoredPrinter printer = new ColoredPrinter.Builder(1, false)
            .foreground(Ansi.FColor.WHITE).build();

    public static ColoredPrinter getPrinter() {
        return printer;
    }

    public static void printError(String content){
        printer.setForegroundColor(Ansi.FColor.WHITE);
        printer.errorPrint("[");
        printer.setForegroundColor(Ansi.FColor.RED);
        printer.setAttribute(Attribute.BLINK);
        printer.errorPrint("ERROR");
        printer.setAttribute(Attribute.NONE);
        printer.setForegroundColor(Ansi.FColor.WHITE);
        printer.errorPrint("] ");
        printer.errorPrint(content);
    }

    public static void printWarning(String content){
        printer.setForegroundColor(Ansi.FColor.WHITE);
        printer.errorPrint("[");
        printer.setForegroundColor(Ansi.FColor.YELLOW);
        printer.errorPrint("WARNING");
        printer.setAttribute(Attribute.NONE);
        printer.setForegroundColor(Ansi.FColor.WHITE);
        printer.errorPrint("] ");
        printer.errorPrint(content);
    }

    public static void printInfo(String content){
        printer.setForegroundColor(Ansi.FColor.WHITE);
        printer.errorPrint("[");
        printer.setForegroundColor(Ansi.FColor.CYAN);
        printer.errorPrint("INFO");
        printer.setAttribute(Attribute.NONE);
        printer.setForegroundColor(Ansi.FColor.WHITE);
        printer.errorPrint("] ");
        printer.errorPrint(content);
    }
}
