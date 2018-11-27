package ua.kiev.prog.patterns.factory;

public class Main {
    public static void main(String[] args) {
        LoggerFactory factory = new LoggerFactory();

        Logger logger = factory.getLogger(LoggerType.CONSOLE);
        logger.logMessage("test");

        logger = factory.getLogger(LoggerType.FILE);
        logger.logMessage("test");
    }
}
