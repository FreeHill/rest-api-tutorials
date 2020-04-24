package com.restful.RestfulApi.service;

import com.restful.RestfulApi.model.Todo;
import com.restful.RestfulApi.error.InvalidTodoIdException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoStorage {
    /* - AtomicInteger class resolves synchronization
     *   problems in multi-threaded environments
     * - Map Todo model with integer keys by creating a "todos" hashmap
    */
    AtomicInteger incrementKey = new AtomicInteger(0);
    Map<Integer, Todo> todos = new HashMap<>(20);

    /*getTodo() is of the type Map and will resolve the return type
    * to an unmodifiable key value pair
    * */
    public Map<Integer, Todo> getTodos(){
        return Collections.unmodifiableMap(todos);
    }

    //Exception handling for Todo
    public Todo getTodo(Integer id) throws  InvalidTodoIdException {
        if(!todos.containsKey(id)){
            throw new InvalidTodoIdException();
        }
        return todos.get(id);
    }

    //Todo setter
    /* addTodo function is of Type "Todo" with parameter todo, of Type "Todo"
    * newTodo is an object of Todo, with constructor parameters of incrementKey and todo
    * */
    public Todo addTodo(Todo todo){
        Todo newTodo = new Todo(incrementKey.getAndAdd(1), todo.getMessage());
        todos.put(newTodo.getId(), newTodo);
        return newTodo;
    }

    /*this function updates(replaces) a todo item in the Todo Hasmap
     *An error is thrown anytime and id is not contained in the
     * Todo list
     *  update(replacement) is done by passing a todo  item which is of the
     *  same type as Todo, since todo is a type of Todo
     * */
    public void replace(Todo todo) throws InvalidTodoIdException{
        if(!todos.containsKey(todo.getId())){
            throw new InvalidTodoIdException(todo.getId());
        }
        todos.replace(todo.getId(), todo);
    }

    /*this function removes a todo item from the Todo Hashmap
     *An error is thrown anytime and id is not contained in the
     * Todo list
     *  Removal is done based on id(integer) passed to the removeTodo function
    * */
    public void removeTodo(Integer id) throws InvalidTodoIdException{
        if(!todos.containsKey(id)){
            throw new InvalidTodoIdException();
        }
        todos.remove(id);
    }
}
