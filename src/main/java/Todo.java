public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getDescriptionStatus() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + this.description;
    }

}
