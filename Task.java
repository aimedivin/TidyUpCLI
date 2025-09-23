import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
  public static enum Status {
    todo, in_progress, done
  };

  private final int id;
  private String description;
  private Status status;
  private String createdAt;
  private String updatedAt;

  public Task(int id, String description) {
    this.id = id;
    this.description = description;
    this.status = status.todo;
    this.createdAt = ZonedDateTime.now().toString();
    this.updatedAt = ZonedDateTime.now().toString();
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  // Convert to JSON format
  public String toJSON() {
    return "{"
        + "\"id\":" + id + ","
        + "\"description\":\"" + description.replace("\"", "\\\"") + "\","
        + "\"status\":\"" + status + "\","
        + "\"createdAt\":\"" + createdAt + "\","
        + "\"updatedAt\":\"" + updatedAt + "\""
        + "}";
  }

  // Convert from JSON
  public static Task fromJSON(String json) {
    int id = Integer.parseInt(json.replaceAll("(?s).*\"id\"\\s*:\\s*(\\d+).*", "$1"));
    String description = json.replaceAll("(?s).*\"description\"\\s*:\\s*\"([^\"]+)\".*", "$1");
    String status = json.replaceAll("(?s).*\"status\"\\s*:\\s*\"([^\"]+)\".*", "$1");
    String createdAt = json.replaceAll("(?s).*\"createdAt\"\\s*:\\s*\"([^\"]+)\".*", "$1");
    String updatedAt = json.replaceAll("(?s).*\"updatedAt\"\\s*:\\s*\"([^\"]+)\".*", "$1");

    Task task = new Task(id, description);
    task.status = Status.valueOf(status);
    task.createdAt = createdAt;
    task.updatedAt = updatedAt;
    return task;

  }
}
