import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    public List<Integer> subtaskIds; // Публичное поле вместо приватного

    public Epic(int id, String name, String description) {
        super(id, name, description, TaskStatus.NEW);
        this.subtaskIds = new ArrayList<>();
    }

    // Нет геттеров и сеттеров, поля публичные
    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtaskIds=" + subtaskIds +
                '}';
    }
}
