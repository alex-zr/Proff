package ua.kiev.prog.patterns.facade;

public class LoggerFacade {
    private ErrorLogger errorLogger = new ErrorLogger();
    private FileLogger fileLogger = new FileLogger();
    private ConsoleLogger consoleLogger = new ConsoleLogger();

    public void logError(String msg) {
        errorLogger.logMessage(msg);
    }

    public void logFile(String msg) {
        fileLogger.logMessage(msg);
    }

    public void logConsole(String msg) {
        consoleLogger.logMessage(msg);
    }
}
