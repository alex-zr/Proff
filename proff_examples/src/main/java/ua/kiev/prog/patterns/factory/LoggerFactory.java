package ua.kiev.prog.patterns.factory;

public class LoggerFactory {
    public Logger getLogger(LoggerType type) {
        Logger res = null;
        switch (type) {
            case FILE:
                res = new FileLogger();
                break;
            case CONSOLE:
                res = new ConsoleLogger();
                break;
            case ERROR:
                res = new ErrorLogger();
                break;
        }
        return res;
    }
}
