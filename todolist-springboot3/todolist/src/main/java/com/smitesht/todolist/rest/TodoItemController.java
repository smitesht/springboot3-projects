package com.smitesht.todolist.rest;

import com.smitesht.todolist.entity.ResponseEntity;
import com.smitesht.todolist.entity.TodoItem;
import com.smitesht.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoItemController {

    private TodoItemService todoItemService;

    @Autowired
    public TodoItemController(TodoItemService _todoItemService){
        todoItemService = _todoItemService;
    }

    @CrossOrigin
    @GetMapping("/")
    ResponseEntity GetAll(){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatusCode(200);
        responseEntity.setData(todoItemService.GetAll());
        return responseEntity;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    ResponseEntity GetById(@PathVariable("id") long id){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatusCode(200);
        responseEntity.setData(todoItemService.Get(id));
        return responseEntity;
    }

    @CrossOrigin
    @PostMapping("/")
    ResponseEntity Save(@RequestBody TodoItem todoItem){

        System.out.println(todoItem.toString());

        TodoItem newItem = new TodoItem(todoItem.getTask(),todoItem.getCompleted());
        todoItemService.Save(newItem);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatusCode(200);
        return responseEntity;
    }

    @CrossOrigin
    @PutMapping("/{id}")
    ResponseEntity Update(@PathVariable long id,@RequestBody TodoItem todoItem){
        todoItemService.Update(id,todoItem);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatusCode(200);
        return responseEntity;
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    ResponseEntity Delete(@PathVariable long id){
        todoItemService.Delete(id);
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setStatusCode(200);
        return responseEntity;
    }

}
