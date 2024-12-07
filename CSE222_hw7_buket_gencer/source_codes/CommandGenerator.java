import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CommandGenerator {

    private static final String[] SYMBOLS = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "FBSK", "BRKA", "BABA", "JNJ", "WMT","VBC", "PNG", "JPM", "UNH", "MA", "HD", "DIS", "PYPL", "BAC", "INTC"};
    private static final int NUM_COMMANDS = 2000;
    private static final String FILE_PATH = "commands.txt";

    public static void generateCommands() {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < NUM_COMMANDS; i++) {
                int commandType = random.nextInt(4); // 0: ADD, 1: REMOVE, 2: SEARCH, 3: UPDATE
                String symbol = SYMBOLS[random.nextInt(SYMBOLS.length)];
                double price = 100 + (5000 - 100) * random.nextDouble();
                long volume = 1000 + random.nextInt(1000000);
                long marketCap = 1000000 + random.nextInt(1000000000);

                switch (commandType) {
                    case 0: // ADD
                        writer.write(String.format("ADD %s %.2f %d %d%n", symbol, price, volume, marketCap));
                        break;
                    case 1: // REMOVE
                        writer.write(String.format("REMOVE %s%n", symbol));
                        break;
                    case 2: // SEARCH
                        writer.write(String.format("SEARCH %s%n", symbol));
                        break;
                    case 3: // UPDATE
                        String newSymbol = SYMBOLS[random.nextInt(SYMBOLS.length)];
                        double newPrice = 100 + (5000 - 100) * random.nextDouble();
                        long newVolume = 1000 + random.nextInt(1000000);
                        long newMarketCap = 1000000 + random.nextInt(1000000000);
                        writer.write(String.format("UPDATE %s %s %.2f %d %d%n", symbol, newSymbol, newPrice, newVolume, newMarketCap));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
