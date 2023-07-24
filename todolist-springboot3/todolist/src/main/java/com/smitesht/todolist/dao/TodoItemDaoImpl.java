package com.smitesht.todolist.dao;

import com.smitesht.todolist.entity.TodoItem;
import com.smitesht.todolist.exception.TodoItemNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoItemDaoImpl implements TodoItemDao{

    private EntityManager entityManager;

    @Autowired
    public TodoItemDaoImpl(EntityManager _entityManager){
        entityManager = _entityManager;
    }

    @Override
    public List<TodoItem> GetAll() {

        TypedQuery<TodoItem> todoitemsQry = entityManager.createQuery("from TodoItem", TodoItem.class);

        List<TodoItem> todoItems = todoitemsQry.getResultList();

        return todoItems;
    }

    @Override
    public TodoItem Get(long id) {

        TodoItem todoItem = entityManager.find(TodoItem.class,id);

        if(todoItem == null){
            throw new TodoItemNotFoundException("Todo Item Not Found");
        }

        return todoItem;
    }

    @Override
    public void Save(TodoItem todoItem) {

        try {
            entityManager.persist(todoItem);
        }catch (Exception e){
            throw new TodoItemNotFoundException("Error While Saving Data");
        }

    }

    @Override
    public void Update(long id, TodoItem todoItem) {

        try
        {
            TodoItem _todoItem = Get(id);
            _todoItem.setTask(todoItem.getTask());
            _todoItem.setCompleted(todoItem.getCompleted());
            entityManager.merge(_todoItem);
        }catch (Exception e){
            throw new TodoItemNotFoundException("Error While Updating Data");
        }


    }

    @Override
    public void Delete(long id) {

        try
        {
        TodoItem _todoItem = Get(id);
        entityManager.remove(_todoItem);
        }catch (Exception e){
            throw new TodoItemNotFoundException("Error While Deleting Data");
        }
    }
}
