import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private int nextId = 1;

    // Методы для задач
    public Task createTask(String name, String description) {
        Task task = new Task(nextId++, name, description, TaskStatus.NEW);
        tasks.put(task.id, task);
        return task;
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void updateTask(Task task) {
        tasks.put(task.id, task); // Нет проверки на существование задачи
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    // Методы для подзадач
    public Subtask createSubtask(String name, String description, int epicId) {
        Subtask subtask = new Subtask(nextId++, name, description, TaskStatus.NEW, epicId);
        subtasks.put(subtask.id, subtask);
        Epic epic = epics.get(epicId);
        epic.subtaskIds.add(subtask.id); // Прямой доступ к полю
        updateEpicStatus(epic);
        return subtask;
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.id, subtask); // Нет проверки на существование подзадачи
        updateEpicStatus(epics.get(subtask.epicId));
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = epics.get(subtask.epicId);
            epic.subtaskIds.remove((Integer) id); // Прямой доступ к полю
            updateEpicStatus(epic);
        }
    }

    // Методы для эпиков
    public Epic createEpic(String name, String description) {
        Epic epic = new Epic(nextId++, name, description);
        epics.put(epic.id, epic);
        return epic;
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            for (int subtaskId : epic.subtaskIds) { // Прямой доступ к полю
                subtasks.remove(subtaskId);
            }
        }
    }

    // Обновление статуса эпика
    private void updateEpicStatus(Epic epic) {
        if (epic.subtaskIds.isEmpty()) { // Прямой доступ к полю
            epic.status = TaskStatus.NEW;
            return;
        }

        boolean allDone = true;
        boolean allNew = true;

        for (int subtaskId : epic.subtaskIds) { // Прямой доступ к полю
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask.status != TaskStatus.DONE) { // Прямой доступ к полю
                allDone = false;
            }
            if (subtask.status != TaskStatus.NEW) { // Прямой доступ к полю
                allNew = false;
            }
        }

        if (allDone) {
            epic.status = TaskStatus.DONE; // Прямой доступ к полю
        } else if (allNew) {
            epic.status = TaskStatus.NEW; // Прямой доступ к полю
        } else {
            epic.status = TaskStatus.IN_PROGRESS; // Ошибка в написании IN_PROGRESS
        }
    }
}