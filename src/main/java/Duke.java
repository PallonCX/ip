import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        // Ui
        Ui ui = new Ui();

        // Storage
        Storage storage = new Storage();

        // Input reader
        Scanner inputReader = new Scanner(System.in);

        // Greet
        ui.greet();

        // Perform task
        while (true) {
            String task = inputReader.nextLine();
            String[] splitedTask = task.split(" ");
            String taskType = "";

            // Empty command handler
            try {
                taskType = splitedTask[0];
                if (taskType.isBlank()) {
                    throw new ArrayIndexOutOfBoundsException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.print("Sorry, we are not sync enough to communicate through empty command.");
                continue;
            }

            boolean isEnd = false;

            try {
                switch (taskType) {
                    case "bye": {
                        // Incorrect command syntax handler
                        if (splitedTask.length > 1) {
                            throw new DukeException("bye");
                        }

                        isEnd = true;
                        break;
                    }
                    case "list": {
                        // Incorrect command syntax handler
                        if (splitedTask.length > 1) {
                            throw new DukeException("list");
                        }

                        ui.list(storage);
                        break;
                    }
                    case "mark": {
                        // Incorrect command syntax handler
                        if (splitedTask.length != 2) {
                            throw new DukeException("mark");
                        }
                        int index = 0;
                        try {
                            index = Integer.parseInt(splitedTask[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("mark");
                        }

                        storage.markDone(index);
                        ui.mark(storage.getItem(index));
                        break;
                    }
                    case "unmark": {
                        // Incorrect command syntax handler
                        if (splitedTask.length != 2) {
                            throw new DukeException("unmark");
                        }
                        int index = 0;
                        try {
                            index = Integer.parseInt(splitedTask[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("unmark");
                        }

                        storage.unmarkDone(index);
                        ui.mark(storage.getItem(index));
                        break;
                    }
                    case "todo": {
                        // Incorrect command syntax handler
                        if (splitedTask.length == 1) {
                            throw new DukeException("todo");
                        }

                        Task newTask = new Todo(task);
                        storage.add(newTask);
                        ui.add(newTask, storage);
                        break;
                    }
                    case "deadline": {
                        // Incorrect command syntax handler
                        if (splitedTask.length == 1) {
                            throw new DukeException("deadline");
                        }

                        // Split the string with /by
                        String[] splitedBy = task.split(" /by ");

                        // Incorrect command syntax handler
                        if (splitedBy.length != 2 || splitedBy[0].length() <= 9
                                || splitedBy[0].substring(9).isBlank()
                                || splitedBy[1].isBlank()) {
                            throw new DukeException("deadline");
                        }

                        // Create deadline task
                        Task newTask = new Deadline(splitedBy[0].substring(9), splitedBy[1]);
                        storage.add(newTask);
                        ui.add(newTask, storage);
                        break;
                    }
                    case "event": {
                        // Incorrect command syntax handler
                        if (splitedTask.length == 1) {
                            throw new DukeException("event");
                        }

                        // Split the string with /from
                        String[] splitedFrom = task.split(" /from ");

                        // Incorrect command syntax handler
                        if (splitedFrom.length != 2 || splitedFrom[0].length() <= 6
                                || splitedFrom[0].substring(6).isBlank()
                                || splitedFrom[1].isBlank()) {
                            throw new DukeException("deadline");
                        }

                        // Split the string with /to
                        String[] splitedTo = splitedFrom[1].split(" /to ");

                        // Incorrect command syntax handler
                        if (splitedTo.length != 2 || splitedTo[0].isBlank() || splitedTo[1].isBlank()) {
                            throw new DukeException("deadline");
                        }

                        // Create event task
                        Task newTask = new Event(splitedFrom[0].substring(6), splitedTo[0], splitedTo[1]);
                        storage.add(newTask);
                        ui.add(newTask, storage);
                        break;
                    }
                    default: {
                        ui.print("Syntax error, unknown command.");
                    }
                }
            } catch (DukeException e) {
                ui.print(e.handle());
            }

            // Loop breaker check
            if (isEnd) {
                break;
            }
        }

        // Exit
        ui.exit();
    }
}
