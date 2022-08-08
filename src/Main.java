import controller.ProjectController;
import controller.TaskController;
import model.Project;
import model.Task;
import util.ConnectionFactory;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Task task = new Task();
        TaskController taskController = new TaskController();

        task.setProjectID(1);
        task.setName("teste task");
        task.setIsCompleted(false);
        task.setDeadline(new Date());
        task.setUpdatedAt(new Date());
        taskController.save(task);



    }
}