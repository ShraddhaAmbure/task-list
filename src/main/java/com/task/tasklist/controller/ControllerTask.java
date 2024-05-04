package com.task.tasklist.controller;

import com.task.tasklist.domain.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Controller
public class ControllerTask {
    List<Task> taskList1 = new ArrayList<>();
    List<Task> taskList2= new ArrayList<>();
    {
        taskList1.add(new Task(1,"ABC"));
        taskList1.add(new Task(2,"PQR"));
        taskList1.add(new Task(3,"XYZ"));
    }
    @GetMapping("/task")
    public String getTask(Model model){
        model.addAttribute("tasks",taskList1);
        return "task";
    }
    @GetMapping("/addtask")
    public String addTask(Model model){
        Task task = new Task();
        model.addAttribute("taskobj",task);
        return "taskform";
    }
        @PostMapping("/taskform")
        public String getTask(Task task){
            taskList1.add(task);
            return "redirect:/task";
        }
    @GetMapping("/removetask/{id}")
    public String removeTask(@PathVariable(name = "id") int id){
        //fetch the specific task form task list based on id
        //add it to the completed task list
        //remove it from task list
        Iterator itr = taskList1.iterator();
        while (itr.hasNext()){
            Task arr=(Task) itr.next();
            if(arr.getTaskId()==id){
                taskList2.add(arr);
                taskList1.remove(arr);
                break;
            }
        }
        return "redirect:/task";
    }
    @GetMapping("/completedtask")
    public String completedTask(Model model){
        model.addAttribute("completetasklist",taskList2);
        return "completetask";
    }
}
