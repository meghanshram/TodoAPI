package todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	private TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}



	@RequestMapping(value="todo",method=RequestMethod.GET)
	public String printAll(ModelMap model)
	{
		String username=getLoggedinUsername(model);
		List<Todo> todos=todoService.findByUsername(username);
		model.addAttribute("todos", todos);
		return "todo";
	}



	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String addTodo(ModelMap model)
	{
		String username=getLoggedinUsername(model);
		Todo todo=new Todo(0,username,"",LocalDate.now().plusYears(1),false);
		model.addAttribute("todo", todo);
		return "addtodo";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String redirectTodo(ModelMap model,@Valid Todo todo,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addtodo";
		}
		String username=getLoggedinUsername(model);
		todoService.addTodo(username, todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:todo";
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id)
	{
		todoService.deleteById(id);
		return "redirect:todo";
	}
	
	@RequestMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model)
	{
		Todo todo=todoService.findById(id);
		model.addAttribute("todo",todo);
		return "addtodo";
	}
	
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "addtodo";
		}
		String username=getLoggedinUsername(model);
		todo.setName(username);
		todoService.updateTodo(todo);
		return "redirect:todo";
	}
	

	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	

}
