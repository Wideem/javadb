package commands.enums;

public enum Command {

    ADD_NEW("1", "Add new question"),
    SHOW("2", "Show data"),
    DELETE("3", "Fill data(first time use)"),
    UPDATE("4", "Take exam"),

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
