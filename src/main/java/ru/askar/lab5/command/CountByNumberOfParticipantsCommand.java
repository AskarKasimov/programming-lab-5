package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.MusicBand;

public class CountByNumberOfParticipantsCommand extends Command {
    public CountByNumberOfParticipantsCommand() {
        super("count_by_number_of_participants");
    }

    @Override
    public void execute(String[] args) {
        int numberOfParticipants = Integer.parseInt(args[0]);
        int count = 0;
        for (MusicBand item : CollectionStorage.getInstance().getCollection()) {
            if (item.getNumberOfParticipants() == numberOfParticipants) {
                count++;
            }
        }
        outputWriter.writeOnSuccess(String.valueOf(count));
    }

    @Override
    public String getInfo() {
        return "count_by_number_of_participants numberOfParticipants - вывести количество элементов, значение поля numberOfParticipants которых равно заданному";
    }
}
