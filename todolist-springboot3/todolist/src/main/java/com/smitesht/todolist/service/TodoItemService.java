package com.smitesht.todolist.service;

import com.smitesht.todolist.entity.TodoItem;

import java.util.List;

public interface TodoItemService {
    List<TodoItem> GetAll();
    TodoItem Get(long id);
    void Save(TodoItem todoItem);
    void Update(long id, TodoItem todoItem);
    void Delete(long id);
}
