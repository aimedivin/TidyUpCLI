import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaskManager {
  public static String FILE_NAME = "tasks.json";
  private static File tasksFile;

  public static List<Task> loadTasks() {
    List<Task> tasks = new ArrayList<>();

    try {
      tasksFile = new File(FILE_NAME);
      if (!Files.exists(Paths.get(FILE_NAME))) {
        System.out.println("inn");
        Files.write(Paths.get(FILE_NAME), "[]".getBytes());
      }
      String content = (new String(Files.readAllBytes(Paths.get(FILE_NAME)))).trim();

      if (content.equals("[]"))
        return tasks;

      String[] items = content.substring(1, content.length() - 1).split("},");
      for (int i = 0; i < items.length; i++) {
        String json = items[i].trim();

        if (!json.endsWith("}"))
          json += "}";
        tasks.add(Task.fromJSON(json));
      }

    } catch (Exception e) {
      System.out.println("LOADING-TASKS: Error Processing File...");
    }
    return tasks;
  }

  // Saving new tasks
  public static void saveTasks(List<Task> tasks) {
    try {
      try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
        out.print("[");

        for (int i = 0; i < tasks.size(); i++) {
          out.print(tasks.get(i).toJSON());
          if (i < tasks.size() - 1)
            out.print(",");

        }
        out.print("]");
      }
    } catch (Exception e) {
      System.out.println("SAVING-TASKS: Error Processing File...");
    }
  }

  // Printing out task
  public static void printTask(Task task) {
    System.out.printf("\t[%d] %s %n%n\t\t status: (%s) %n\t\t posted: %s %n\t\t updated: %s %n ", task.getId(),
        task.getDescription(),
        task.getStatus(),
        ZonedDateTime.parse(task.getCreatedAt()).format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm")),
        ZonedDateTime.parse(task.getUpdatedAt()).format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm")));
  }

}
