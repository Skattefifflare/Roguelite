public class Main {
    public static void main(String[] args){
        try {
            Game game = new Game();
            game.run();
        }
        catch (InterruptedException | java.lang.reflect.InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
