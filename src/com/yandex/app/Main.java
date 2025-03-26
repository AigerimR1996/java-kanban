package com.yandex.app;

import com.yandex.app.model.*;
import com.yandex.app.service.TaskManager;

import static com.yandex.app.model.TaskStatus.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("Task 1", "Description 1", NEW);
        manager.createTask(task1);

        Task task2 = new Task("Task 2", "Description 2", NEW);
        manager.createTask(task2);

        // Создаем эпик
        Epic epic1 = new Epic("Epic 1", "Description Epic 1");
        manager.createEpic(epic1);

        // Создаем подзадачи
        Subtask subtask1 = new Subtask("Subtask 1", "Description Subtask 1", NEW, epic1.getId());
        manager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Subtask 2", "Description Subtask 2", NEW, epic1.getId());
        manager.createSubtask(subtask2);

        // Выводим задачи
        System.out.println("Tasks: " + manager.getAllTasks());
        System.out.println("Epics: " + manager.getAllEpics());
        System.out.println("Subtasks: " + manager.getAllSubtasks());

        // Меняем статус подзадачи
        subtask1.setStatus(DONE);
        manager.updateSubtask(subtask1);

        // Выводим обновленные данные
        System.out.println("Updated Epic: " + manager.getEpic(epic1.getId()));

        // Методы удаления
        System.out.println("\nDeleting all subtasks...");
        manager.deleteAllSubtasks();
        System.out.println("Subtasks after deletion: " + manager.getAllSubtasks());

        System.out.println("\nDeleting all epics...");
        manager.deleteEpics();
        System.out.println("Epics after deletion: " + manager.getAllEpics());

        System.out.println("\nDeleting all tasks...");
        manager.deleteAllTasks();
        System.out.println("Tasks after deletion: " + manager.getAllTasks());
    }
}