import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class TaskCLI {
    public static void updateStatus(List<Task> tasks, String[] args, Task.Status status) {
        if (args.length < 2) {
            System.out.println("Usage: [status] <id>");
            return;
        } else {
            int id = Integer.parseInt(args[1]);

            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setStatus(status);
                    task.setUpdatedAt(ZonedDateTime.now().toString());
                    TaskManager.saveTasks(tasks);
                    System.out.println("Task with id:[" + id + "] marked  as " + status);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java TaskCLI <command> [arguments]");
            return;
        }

        String command = args[0];

        List<Task> tasks = TaskManager.loadTasks();

        switch (command) {
            case "add" -> {
                if (args.length < 2) {
                    System.out.println("Please provide description for the task");
                    return;
                }

                String description = String.join("", Arrays.copyOfRange(args, 1, args.length));
                int id = tasks.isEmpty() ? 1 : tasks.get(tasks.size() - 1).getId() + 1;
                Task newTask = new Task(id, description);
                tasks.add(newTask);
                TaskManager.saveTasks(tasks);
                System.out.println("Task added successfully (ID: " + id + ")");
                break;
            }
            case "update" -> {
                if (args.length < 3) {
                    System.out.println("Usage: update <id> <description>");
                } else {
                    int id = Integer.parseInt(args[1]);
                    for (Task task : tasks) {
                        if (task.getId() == id) {
                            task.setDescription(args[2]);
                            task.setUpdatedAt(ZonedDateTime.now().toString());
                            TaskManager.saveTasks(tasks);
                            System.out.println("Task with id:[" + id + "] updated successfully.");
                        }
                    }

                }
                break;
            }
            case "delete" -> {
                if (args.length < 2) {
                    System.out.println("Usage: delete <id>");

                } else {
                    int id = Integer.parseInt(args[1]);
                    tasks.removeIf(t -> t.getId() == id);
                    TaskManager.saveTasks(tasks);
                    System.out.println("Task was successfully deleted");
                }
                break;
            }
            case "mark-in-progress" -> {
                updateStatus(tasks, args, Task.Status.in_progress);
                break;
            }
            case "mark-done" -> {
                updateStatus(tasks, args, Task.Status.done);
                break;
            }
            case "list" -> {
                if (args.length == 1) {
                    System.out.printf("\n\nAll Tasks:\n\n");
                    for (Task task : tasks) {
                        TaskManager.printTask(task);
                        System.out.println("\n");
                    }
                } else {
                    Task.Status status = Task.Status.valueOf(args[1]);
                    for (Task task : tasks) {
                        if (task.getStatus().equals(status)) {
                            TaskManager.printTask(task);
                            System.out.println("\n");
                        }
                    }
                }
                break;
            }
            default -> System.out.println("Unknown Command");
        }
    }
}
