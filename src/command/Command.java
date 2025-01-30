package command;

public interface Command {
    void execute(String[] args); // Выполнить команду
    String info(); // Получить информацию о команде
}