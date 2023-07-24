# Todo List - Spring Boot 3 Web App

The repository contains MySQL database script, and Spring Boot 3 source code

# MySQL Script
- Create Database: TodoList
- Create Table: todo_items

```
DROP SCHEMA IF EXISTS `TodoList` ;
CREATE SCHEMA IF NOT EXISTS `TodoList` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `TodoList` ;

-- -----------------------------------------------------
-- Table `TodoList`.`todo_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TodoList`.`todo_items` ;

CREATE  TABLE IF NOT EXISTS `TodoList`.`todo_items` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `task` VARCHAR(100) NOT NULL ,
  `is_completed` TINYINT(1) NOT NULL, 
  PRIMARY KEY (`id`))  
ENGINE = InnoDB;
```

# Spring Boot Code

### Entity 

```
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

    getter/setter
    ...
    toString()
    ...
    
}

```

### Data Access Object (DAO)

#### Interface: TodoItemDao

```
public interface TodoItemDao {

    List<TodoItem> GetAll();
    TodoItem Get(long id);
    void Save(TodoItem todoItem);
    void Update(long id, TodoItem todoItem);
    void Delete(long id);
}
```
#### Implementation: TodoItemDaoImpl

```
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
```


### Service
#### interface: TodoItemService
```
public interface TodoItemService {
    List<TodoItem> GetAll();
    TodoItem Get(long id);
    void Save(TodoItem todoItem);
    void Update(long id, TodoItem todoItem);
    void Delete(long id);
}
```

#### implementation: TodoItemServiceImpl
```
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
```

### RestController : TodoItemController

```
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

```
