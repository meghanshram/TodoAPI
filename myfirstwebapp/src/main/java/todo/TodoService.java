package todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static int todoCount=0;
	
	private static List<Todo> todos=new ArrayList<>();
	static
	{
		todos.add(new Todo(++todoCount,"meg","java",LocalDate.now().plusYears(1),false));
		todos.add(new Todo(++todoCount,"megh","java",LocalDate.now().plusYears(1),true));
		todos.add(new Todo(++todoCount,"mega","java",LocalDate.now().plusYears(1),false));
	}
	
	public List<Todo> findByUsername(String name)
	{
		Predicate<? super Todo> predicate=todo -> todo.getName().equalsIgnoreCase(name);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String Name,String desc,LocalDate targetDate,boolean done)
	{
		Todo todo=new Todo(++todoCount,Name,desc,targetDate,done);
		todos.add(todo);
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		Predicate<? super Todo> predicate=todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		// TODO Auto-generated method stub
		Predicate<? super Todo> predicate=todo -> todo.getId()==id;
		Todo todo=todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		// TODO Auto-generated method stub
		deleteById(todo.getId());
		todos.add(todo);
		
	}

}
