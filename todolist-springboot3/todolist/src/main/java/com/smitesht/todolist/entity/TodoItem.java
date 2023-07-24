package com.smitesht.todolist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "todo_items")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "task")
    private String task;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    public TodoItem(){

    }
    public TodoItem(String task, Boolean isCompleted) {
        this.task = task;
        this.isCompleted = isCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
