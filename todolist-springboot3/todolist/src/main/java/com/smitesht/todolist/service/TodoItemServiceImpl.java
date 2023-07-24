package com.smitesht.todolist.service;

import com.smitesht.todolist.dao.TodoItemDao;
import com.smitesht.todolist.entity.TodoItem;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    private TodoItemDao todoItemDao;

    @Autowired
    public TodoItemServiceImpl(TodoItemDao _todoItemDao){
        todoItemDao = _todoItemDao;
    }

    @Override
    public List<TodoItem> GetAll() {
        return todoItemDao.GetAll();
    }

    @Override
    public TodoItem Get(long id) {
        return todoItemDao.Get(id);
    }

    @Override
    @Transactional
    public void Save(TodoItem todoItem) {
        todoItemDao.Save(todoItem);
    }

    @Override
    @Transactional
    public void Update(long id, TodoItem todoItem) {
        todoItemDao.Update(id,todoItem);
    }

    @Override
    @Transactional
    public void Delete(long id) {
        todoItemDao.Delete(id);
    }
}
