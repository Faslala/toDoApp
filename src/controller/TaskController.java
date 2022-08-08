package controller;

import model.Task;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    Connection connection = null;
    PreparedStatement statement = null;

    public void save(Task task) {

        String sql = "INSERT INTO tasks (projectId, name, description, isCompleted, notes, deadline, createdAt, "
                + "updatedAT) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getProjectID());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date (task.getUpdatedAt().getTime()));
            statement.execute();

        } catch (Exception e) {

            throw new RuntimeException("A tarefa não pode ser salva!", e);

        } finally {

            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET projectId = ?, name = ?, description = ?, isCompleted = ?, notes = ?, " +
                "deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getProjectID());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();

        } catch (Exception e) {

            throw new RuntimeException("A tarefa não pode ser atualizada!", e);

        } finally {

            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int taskId) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();

        } catch (Exception e) {

            throw new RuntimeException("A tarefa não pode ser excluida!", e);

        } finally {

            ConnectionFactory.closeConnection(connection, statement);

        }
    }

    public List<Task> getAll(int projectId) {

        String sql = "SELECT * FROM tasks WHERE projectID = ?";

        ResultSet resultSet = null;

        List<Task> tasks = new ArrayList<>();

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setProjectID(resultSet.getInt("projectID"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setIsCompleted(resultSet.getBoolean("isCompleted"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));

                tasks.add(task);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar os dados na tabela", e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return tasks;
    }
}
