public class Task {
    public int id; // Публичное поле вместо приватного
    public String name;
    public String description;
    public TaskStatus status;

    public Task(int id, String name, String description, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // Нет геттеров и сеттеров, поля публичные
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}