
package App;

import java.util.*;

public class ToDoList {
    static List<Task> tasks = new ArrayList<>();
    
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
            case SHOW_LIST -> showList();
            case ADD_TASK -> {
                addTask();
                showList();
                System.out.println("");
            }
            case REMOVE_TASK -> {
                removeTask();
                showList();
                System.out.println("");
            }
            case MARK_AS_DONE -> {
                markAsDone();
                showList();
                System.out.println("");
            }
            case EDIT_TASK -> {
                editTask();
                showList();
                System.out.println("");
            }
            case ORDER_BY_PRIORITY -> {
                orderByPriority();
                showList();
                System.out.println("");
            }
            case EXIT -> {
                System.out.println("");
                exit();
            }
            default -> {
            }
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
            case Task.LOW_IMPORTANCE -> s = "Importancia baja";
            case Task.MEDIUM_IMPORTANCE -> s = "Importancia media";
            case Task.HIGH_IMPORTANCE -> s = "Importancia alta";
            default -> { 
                System.out.println("error en priorityString");
                s = "Este numero no esta en el rango de prioridades";
            }
        }
        return s;
    }
    
    //MUESTRA TODAS LAS TAREAS
    private void showList(){
        if (!(tasks.size()>0)) {
            System.out.println("No hay tareas en la lista");
        }
        else{
            int i = 1;
            System.out.println("\n-------- LISTA DE TAREAS --------");
            for(Task t: tasks){
                System.out.println("" + i + " - " + t.getName() + "\t\t" + priorityString(t) + "\t" + isTaskDone(t));
                i++;
            }
            System.out.println("\n");
        }
    }
    
    //PREGUNTA LA EQTIQUETA QUE VA EN LA TAREA Y LA DEVUELVE
    private String askTaskName(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("\nIngrese la etiqueta para la tarea:");
        String taskName = in.nextLine();
        
        return taskName;
    }
    
    //PREGUNTA LA PRIORIDAD DE LA TAREA Y LA DEVUELVE
    private int askTaskPr(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("\nIngrese la priodad de la tarea:\n1 - Importancia Baja\n2 - Importancia Media\n3 - Importancia Alta");
        String pr = in.next();
        
        while(!pr.equals("1") && !pr.equals("2") && !pr.equals("3")){
            System.out.println("\nIngrese un número entero entre 1 y 3...");
            pr = in.next();
        }
        
        return Integer.parseInt(pr)-1;
    }
    
    //PREGUNTA Y DEVUELVE UN TRUE OR FALSE DEPENDE LA ENTRADA (S O N)
    private boolean askSN(char ans){
        Scanner in = new Scanner(System.in);
        
        while(ans!='s' && ans!='S' && ans!='n' && ans!='N'){
            System.out.println("\nIngrese S o s para Sí, o N o n para No.");
            
            char a = in.next().charAt(0);
            ans = a;
        }
        return ans == 's' || ans == 'S';
    }
    
    //PREGUNTA EL ESTADO DE LA TAREA Y LO DEVUELVE
    private boolean askTaskState(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("\n¿Marcar como completada?/tS/N");
        char ans = in.next().charAt(0);
        boolean state = askSN(ans);
        
        return state;
    }
    
    //COMPRUEBA SI LA UN STRING SON NUMEROS, TRUE SI ES ASI
    private boolean isStringInt(String s){
        return s.matches("-?\\d+"); //La que habia pensado yo: [0-9]+
    }
    
    //COMPRUEBA QUE LA OPCIÓN EXISTA
    private int checkOut(){
        Scanner in = new Scanner(System.in);
        
        System.out.println("\nIngrese el numero de la tarea en la que desee realizar la acción. Para salir de esta opción ingrese: 0.");
        String index = in.next();
        boolean checkInput = isStringInt(index);
        
        while(true){
            if (checkInput) {
                while(index.equals("0") || (Integer.parseInt(index) > tasks.size())){
                    System.out.println("\nEsa tarea no existe, por favor ingrese un numero del 1 al " + tasks.size() + " o 0 para salir.");
                    index = in.next();
                    checkInput = isStringInt(index);
                    if (!checkInput) {
                        break;
                    }
                }
                if (checkInput) {
                    break;
                }
            }
            if (!checkInput) {
                while(!checkInput){
                    System.out.println("\nEsa tarea no existe, por favor ingrese un numero del 1 al " + tasks.size());
                    index = in.next();
                    checkInput = isStringInt(index);
                }
            }
        }
        return Integer.parseInt(index)-1;
    }
    
    //AGREGA UNA TAREA A LA LISTA
    private void addTask(){
        Scanner in = new Scanner(System.in);
        System.out.println("\nPara volver al menu presione cualquier tecla. Si quiere agregar una tarea presione: 1.");
        String ans = in.next();
        
        while (ans.equals("1")) {
            String taskName = askTaskName();
            int pr = askTaskPr();

            Task t = new Task(taskName,pr);

            tasks.add(t);
            
            System.out.println("¿Quiere agregar otra tarea?\tS/N");
            boolean add = askSN(in.next().charAt(0));
            if (!add) {
                break;
            }
        }
    }
    
    //ELIMINA UNA TAREA DE LA LISTA
    private void removeTask(){
        if (tasks.size() > 0) {
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
            showList();

            int index = checkOut();
            
            if (index != -1) {
                tasks.get(index).setIsDone(true);
            }
        }
        else{System.out.println("No tienes tareas para marcar como termanadas.");}
    }
    
    //VUELVE A PREGUNTAR NOMBRE, PRIORIDAD Y ESTADO DE LA TAREA Y LO CAMBIA
    private void editTask(){
        if (tasks.size() > 0) {
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
        else{System.out.println("No tienes tareas para editar");}
    }
    
    //INTERCAMBIA DOS TAREAS DENTRO DE UNA LISTA (PARA ORDENAR)
    private void changeTask(Task a, Task b){
        Task aux = new Task(a.getName(),a.getPriority());
        //Intercambio de variables
        a.setName(b.getName());
        a.setPriority(b.getPriority());
        b.setName(aux.getName());
        b.setPriority(aux.getPriority());
    }
    
    //ORDENA POR PRIORIDAD DE MAS IMPORTANTE A MENOS IMPORTANTE
    private void orderByPriority(){
        for (int i = 0; i < tasks.size()-1; i++) { 
            int j = i;
            while (j >= 0 && (tasks.get(j).getPriority() < tasks.get(j+1).getPriority())) 
            {
                changeTask(tasks.get(j),tasks.get(j + 1));
                j--;
            }
        }
    }
    
    //CAMBIA LA FLAG PARA QUE SE DEJE DE EJECUTAR EL PROGRAMA;
    public void exit(){
        running = false;
    }
}