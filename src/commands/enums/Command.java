package commands.enums;

public enum Command {

    ADD_NEW("1", "Add new question"),
    EXAM("2", "Take exam"),
    FILL("3", "Fill data(first time use)"),
    UPDATE("4", "Update question"),
    STAT1("5", "Calculate number of times exam taken"),

    STAT2("6", "Calculate avg exam score "),
    STAT3("7", "Calculate each answer select times"),


    EXIT("0", "Exit");

    public final String key;
    public final String title;

    Command(String key, String title) {
        this.key = key;
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s", this.key, this.title);
    }
}
