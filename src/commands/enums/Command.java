package commands.enums;

public enum Command {

    ADD_NEW("1", "Add new question"),
    EXAM("2", "Take exam"),
    FILL("3", "Fill data(first time use)"),

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
