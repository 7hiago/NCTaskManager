package ua.edu.sumdu.j2se.pavlenko.tasks.view;

import java.util.Scanner;

public class TaskManagerView {

    private Scanner input = new Scanner(System.in);

    private static final String[] startPageContent = new String[]{"Create new task", "Change task",
            "Remove task", "View information about available tasks", "View calendar of scheduled tasks", "Quite"};
    private static final String[] createTaskPageContent = new String[]{"Create new not repeated task",
            "Create new repeated task", "Back"};
    private static final String[] changeTaskPageContent = new String[]{"Change title", "Change activation",
            "Change start time", "Change end time", "Change interval", "Back"};

    public int showStartPage() {
        System.out.println("Select required option: ");
        writePageContent(startPageContent);
        return validationInput(startPageContent.length);
    }

    public int showCreateTaskPage() {
        System.out.println("Select required option: ");
        writePageContent(createTaskPageContent);
        return validationInput(createTaskPageContent.length);
    }

    public int showChangeTaskPage() {
        System.out.println("Select required option: ");
        writePageContent(changeTaskPageContent);
        return validationInput(changeTaskPageContent.length);
    }

    public int showActualTaskListPage(String[] list) {
        System.out.println("Enter 0 - return to menu");
        System.out.println("Your task list:");
        if(list.length == 0) {
            System.out.println("Empty");
        } else {
            writePageContent(list);
        }
        return validationInput(list.length);
    }

    public int showCalendarPage(String[] list) {
        System.out.println("Enter 0 - return to menu");
        System.out.println("Your calendar:");
        if(list.length == 0) {
            System.out.println("Empty");
        } else {
            writePageContent(list);
        }
        return validationInput(list.length);
    }

    public void showNotification(String notification) {
        System.out.println(notification);
    }

    public String readUserInput() {
        return input.nextLine();
    }

    public String getTitle() {
        System.out.println("Write title");
        return readUserInput();
    }

    public String getStartTime() {
        System.out.println("Write start time follow next format: yyyy-MM-ddTHH:mm:ss");
        return readUserInput();
    }

    public String getEndTime() {
        System.out.println("Write end time follow next format: yyyy-MM-ddTHH:mm:ss");
        return readUserInput();
    }

    public String getInterval() {
        System.out.println("Write interval in second");
        return readUserInput();
    }

    public String getActivation() {
        System.out.println("Set activation (0 - not active, 1 - active)");
        return readUserInput();
    }

    private void writePageContent(String[] content) {
        for(int i = 0; i < content.length; i++) {
            System.out.println((i + 1) + " - " + content[i]);
        }
    }

    private int validationInput(int range) {
        int userInput = -1;
        boolean notValid = true;
        while (notValid) {
            try {
                userInput = Integer.parseInt(readUserInput());
            } catch (NumberFormatException e) {
                System.out.println("Value must be a number\nPlease, enter correct symbols");
                continue;
            }
            if(userInput < 0 || userInput > range) {
                System.out.println("Value must be in range form 0 to " + range + "\nPlease, chose correct option!");
            } else {
                notValid = false;
            }
        }
        return userInput;
    }
}
