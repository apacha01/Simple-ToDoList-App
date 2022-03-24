
package App;

import java.util.*;

public class ToDoList {
    List<Task> tasks = new ArrayList<Task>();
    
    //VARIABLES
    public static final int SHOW_LIST = 0;
    public static final int ADD_TASK = 1;
    public static final int REMOVE_TASK = 2;
    public static final int MARK_AS_DONE = 3;
    public static final int EDIT_TASK = 4;
    public static final int ORDER_BY_PRIORITY = 5;
    public static final int EXIT = 6;
    private static boolean running = true;
    
    //INICIALIZACION DE LA APP
    public void run(){
        while(running){
            printMenu();
            int choice = readOption();
            runOption(choice);
        }
    }
    
    //MENU
    private void printMenu(){
        System.out.println("-----------------");
        System.out.println("To Do List App");
        System.out.println("-----------------");
        System.out.println("1. Mostrar lista");
        System.out.println("2. Agregar tarea a la lista");
        System.out.println("3. Eliminar tarea de la lista");
        System.out.println("4. Marcar como terminado");
        System.out.println("5. Editar tarea");
        System.out.println("6. Ordenar por prioridad");
        System.out.println("7. Salir");
        System.out.println("-----------------");
        System.out.println("");
    }
    
    //LECTURA DE LA OPCION
    private int readOption(){
        int choice;
        Scanner in = new Scanner(System.in);
        
        System.out.println("Seleccione la tarea que desea realizar:");
        choice = in.nextInt()-1;
        
        while(choice >= 8 && choice <= 0){   
            System.out.println("Ingrese un número entre 1 y 7...");   
            choice = in.nextInt()-1;
        }
        
        return choice;
    }
    
    //EJECUTA LA OPCIÓN ELEGIDA.
    private void runOption(int choice){
        switch (choice){
            case SHOW_LIST:
                showList();
                break;
            case ADD_TASK:
                addTask();
                showList();
                System.out.println("");
                break;
            case REMOVE_TASK:
                removeTask();
                showList();
                System.out.println("");
                break;
            case MARK_AS_DONE:
                markAsDone();
                showList();
                System.out.println("");
                break;
            case EDIT_TASK:
                editTask();
                showList();
                System.out.println("");
                break;
            case ORDER_BY_PRIORITY:
                orderByPriority();
                showList();
                System.out.println("");
                break;
            case EXIT:
                System.out.println("");
                exit();
                break;
            default: break;
        }
    }
    
    //STRING PARA MOSTRAR SI ESTA TERMINADA O NO LA TAREA
    private String isTaskDone(Task t){
        String s;
        
        if (t.getIsDone()) {
            s = "Completada";
        }
        else{s = "En Progreso";}
        
        return s;
    }
    
    //STRING PARA MOSTRAR LA PRIORIDAD DE LA TAREA
    private String priorityString(Task t){
        String s;
        switch(t.getPriority()){
            case Task.LOW_IMPORTANCE:
                s = "Importancia baja";
                break;
            case Task.MEDIUM_IMPORTANCE:
                s = "Importancia media";
                break;
            case Task.HIGH_IMPORTANCE:
                s = "Importancia alta";
                break;
            default: 
                System.out.println("error");
                s = "Este numero no esta en el rango de prioridades";
        }
        return s;
    }
    
    //MUESTRA TODAS LAS TAREAS
    private void showList(){
        for(Task t: tasks){
            int i = 1;
            System.out.println( i + " - " + t.getName() + "\t" + priorityString(t) + "\t" + isTaskDone(t));
            i++;
        }
        System.out.println("");
    }
    
    //PREGUNTA LA EQTIQUETA QUE VA EN LA TAREA Y LA DEVUELVE
    private String askTaskName(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("Ingrese la etiqueta para la tarea:");
        String taskName = in.nextLine();
        
        return taskName;
    }
    
    //PREGUNTA LA PRIORIDAD DE LA TAREA Y LA DEVUELVE
    private int askTaskPr(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("Ingrese la priodad de la tarea:\n1 - Importancia Baja\n2 - Importancia Media\n3 - Importancia Alta");
        int pr = in.nextInt() - 1;
        
        while(pr != 0 || pr != 1 || pr != 2){
            System.out.println("Ingrese un número entre 1 y 3...");
            pr = in.nextInt() - 1;
        }
        return pr;
    }
    
    //PREGUNTA EL ESTADO DE LA TAREA Y LO DEVUELVE
    private boolean askTaskState(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("¿Marcar como completada? /t S/N");
        char ans = in.next().charAt(0);
        
        while(ans!='s' || ans!='S' || ans!='n' || ans!='N'){
            System.out.println("Ingrese S o s para Sí o N o n para No");
            System.out.println("¿Marcar como completada? /t S/N");
            ans = in.next().charAt(0);
        }
        
        if (ans == 's' || ans == 'S') {
            return true;
        }
        if (ans == 'n' || ans == 'N') {
            return false;
        }
        else return false;
    }
    
    //COMPRUEBA QUE NO HAYA PUESTO POR ERROR UNA OPCIÓN
    private int checkOut(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("Ingrese la tarea en la que desee realizar la acción. Para salir de esta opción ingrese: 0.");
        int index = in.nextInt() - 1;

        while(index < -1 || index > tasks.size()){
            System.out.println("Esa tarea no existe, por favor ingrese un numero del 1 al " + tasks.size());
            index = in.nextInt() - 1;
        }
        
        return index;
    }
    
    //AGREGA UNA TAREA A LA LISTA
    private void addTask(){
        Scanner in = new Scanner(System.in);
        System.out.println("Si desea agregar una tarea ingrese: 1, si quiere salir de esta opción presione cualquier otra tecla.");
        String ans = in.nextLine();
        
        if (ans == "1") {
            String taskName = askTaskName();
            int pr = askTaskPr();

            Task t = new Task(taskName,pr);

            tasks.add(t);
        }
    }
    
    //ELIMINA UNA TAREA DE LA LISTA
    private void removeTask(){
        if (tasks.size() > 0) {
            Scanner in = new Scanner(System.in);
            showList();
            
            int index = checkOut();
            
            if (index != -1) {
                tasks.remove(index);
            }
        }
        else{System.out.println("No tienes tareas para remover.");}
    }
    
    //CAMBIA EL ESTADO DE LA TAREA A TRUE (COMPLETADA)
    private void markAsDone(){
        if (tasks.size() > 0){
            Scanner in = new Scanner(System.in);
            showList();

            int index = checkOut();
            
            if (index != -1) {
                tasks.get(index).setIsDone(true);
            }
        }
        else{System.out.println("No tienes tareas para marcar como termanadas.");}
    }
    
    //VUELVE A PREGUNTAR NOMBRE, PRIORIDAD Y ESTADO DE LA TAREA Y LO CAMBIA POR LA ELEGIDA
    private void editTask(){
        if (tasks.size() > 0) {
            Scanner in = new Scanner(System.in);
            showList();

            int index = checkOut();
            
            if (index != -1) {
                String newTaskName = askTaskName();
                int pr = askTaskPr();

                tasks.get(index).setName(newTaskName);
                tasks.get(index).setPriority(pr);
                tasks.get(index).setIsDone(askTaskState());
            }
        }
        else{System.out.println("No tienes tareas para remover");}
    }
    
    //INTERCAMBIA DOS TAREAS DENTRO DE UNA LISTA (PARA ORDENAR)
    private void changeTask(Task a, Task b){
        Task aux = new Task(a.getName(),a.getPriority());
        a = b;
        b = aux;
    }
    
    //ORDENA POR PRIORIDAD DE MAS IMPORTANTE A MENOS IMPORTANTE
    private void orderByPriority(){
        for (int i = 0; i < tasks.size()-1; i++) {
            if (tasks.get(i).getPriority() < tasks.get(i+1).getPriority()) {
                //tasks.sort(null); //Investigar el COMPARATOR 
                int j = i;
                while (j >= 0 && (tasks.get(i+1).getPriority() > tasks.get(i).getPriority())) 
                {
                    changeTask(tasks.get(j),tasks.get(j + 1));
                    j--;
                }
            }
        }
    }
    
    //CAMBIA LA FLAG PARA QUE SE DEJE DE EJECUTAR EL PROGRAMA;
    public void exit(){
        running = false;
    }
}