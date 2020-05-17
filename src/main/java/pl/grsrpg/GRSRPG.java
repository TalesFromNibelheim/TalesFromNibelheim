package pl.grsrpg;

public class GRSRPG {

    public static void main(String[] args){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Hello world!");
    }
}
