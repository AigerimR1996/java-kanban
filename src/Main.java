public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаем задачи
        Task task1 = manager.createTask("Task 1", "Description 1");
        Task task2 = manager.createTask("Task 2", "Description 2");

        // Создаем эпик с подзадачами
        Epic epic1 = manager.createEpic("Epic 1", "Description Epic 1");
        Subtask subtask1 = manager.createSubtask("Subtask 1", "Description Subtask 1", epic1.id);
        Subtask subtask2 = manager.createSubtask("Subtask 2", "Description Subtask 2", epic1.id);

        // Выводим задачи
        System.out.println("Tasks: " + manager.getAllTasks());
        System.out.println("Epics: " + manager.getAllEpics());
        System.out.println("Subtasks: " + manager.getAllSubtasks());

        // Меняем статусы
        subtask1.status = TaskStatus.DONE; // Прямой доступ к полю
        manager.updateSubtask(subtask1);

        // Выводим обновленные данные
        System.out.println("Updated Epic: " + manager.getEpic(epic1.id));
    }
}