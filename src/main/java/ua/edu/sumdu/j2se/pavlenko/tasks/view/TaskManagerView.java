package ua.edu.sumdu.j2se.pavlenko.tasks.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskManagerView {

    private Scanner input = new Scanner(System.in);

    private static final String[] startPageContent = new String[]{"Create new task", "Change task",
            "Remove task", "View information about available tasks", "View calendar of scheduled tasks", "Quite"};
    private static final String[] createTaskPageContent = new String[]{"Back", "Create new not repeated task",
            "Create new repeated task"};
    private static final String[] changeTaskPageContent = new String[]{"Back", "Change title", "Change activation",
            "Change start time", "Change end time", "Change interval"};

    public int showStartPage() {
        System.out.println("Enter required option: ");
        writePageContent(startPageContent);
        return menuValidationInput(startPageContent.length);
    }

    public int showCreateTaskPage() {
        System.out.println("Enter required option: ");
        writeSubPageContent(createTaskPageContent);
        return subMenuValidationInput(createTaskPageContent.length - 1);
    }

    public int showChangeTaskPage() {
        System.out.println("Enter required option: ");
        writeSubPageContent(changeTaskPageContent);
        return subMenuValidationInput(changeTaskPageContent.length - 1);
    }

    public int showActualTaskListPage(String[] list) {
        System.out.println("Enter 0 - return to menu");
        if(list.length == 0) {
            System.out.println("Your task list is empty");
        } else {
            System.out.println("Your task list:");
            writePageContent(list);
        }
        return subMenuValidationInput(0);
    }

    public int showActualTaskListPageToChange(String[] list) {
        System.out.println("Enter number of task to change/remove");
        System.out.println("Enter 0 - return to menu");
        if(list.length == 0) {
            System.out.println("Your task list is empty");
        } else {
            System.out.println("Your task list:");
            writePageContent(list);
        }
        return subMenuValidationInput(list.length);
    }

    public int showCalendarPage(String[] list) {
        System.out.println("Enter 0 - return to menu");
        if(list.length == 0) {
            System.out.println("Your calendar is empty");
        } else {
            System.out.println("Your calendar:");
            writePageContent(list);
        }
        return subMenuValidationInput(0);
    }

    public void showNotification(String notification) {
        System.out.println("Time to do: " + notification);
    }

    public String readUserInput() {
        return input.nextLine();
    }

    public String getTitle() {
        System.out.println("Enter title");
        String title = "";
        boolean notValid = true;
        while (notValid) {
            title = readUserInput();
            try {
                if(Integer.parseInt(title) >= 0 || Integer.parseInt(title) <= 0) {
                    System.out.println("Wrong title!\nIt must begin from letter\nTry again");
                }
            } catch (NumberFormatException e) {
                if(title.equals("")) {
                    System.out.println("Title must be filled\nFor example: \"Go to the gym\"");
                } else {
                    notValid = false;
                }
            }
        }
        return title;
    }

    public LocalDateTime getStartTime() {
        System.out.println("Enter start date follow next format: \"dd.MM.yyyy\":");
        LocalDate date = getDate();
        System.out.println("Enter start time follow next format: \"HH.mm\":");
        LocalTime time = getTime(date);
        return LocalDateTime.of(date, time);
    }

    public LocalDateTime getEndTime() {
        System.out.println("Enter end date follow next format: \"dd.MM.yyyy\":");
        LocalDate date = getDate();
        System.out.println("Enter end time follow next format: \"HH.mm\":");
        LocalTime time = getTime(date);
        return LocalDateTime.of(date, time);
    }

    public int getInterval() {
        System.out.println("Enter interval in second");
        int interval = -1;
        boolean notValid = true;
        while (notValid) {
            String userInput = readUserInput();
            try {
                interval = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Wrong value!\nIt must be a number\nPlease, enter correct symbols");
                continue;
            }
            if(interval <= 0) {
                System.out.println("Value must be grater then 0\nPlease, enter correct interval!");
            } else {
                notValid = false;
            }
        }
        return interval;
    }

    public boolean getActivation() {
        System.out.println("Set activation:\n0 - not active\n1 - active");
        int activation = -1;
        boolean notValid = true;
        while (notValid) {
            String userInput = readUserInput();
            try {
                activation = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Value must be a number\nPlease, enter correct symbols");
                continue;
            }
            if(activation < 0 || activation > 1) {
                System.out.println("Value must be in range form 0 to 1\nPlease, enter correct option!");
            } else {
                notValid = false;
            }
        }
        return activation == 1;
    }

    public LocalDate getDate() {
        LocalDate date = LocalDate.of(2000, 1, 1);
        boolean notValid = true;
        while (notValid) {
            String userInput = readUserInput();
            try {
                date = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            } catch (DateTimeParseException exception) {
                System.out.println("Wrong format!\nPlease enter date follow next format: dd.MM.yyyy");
                continue;
            }
            if(date.isBefore(LocalDate.now())) {
                System.out.println("Wrong date!\nIt must not be before now\nTry again");
            } else {
                notValid = false;
            }
        }
        return date;
    }

    public LocalTime getTime(LocalDate date) {
        LocalTime time = LocalTime.of(0, 0, 0);
        boolean notValid = true;
        while (notValid) {
            String userInput = readUserInput();
            try {
                time = LocalTime.parse(userInput, DateTimeFormatter.ofPattern("HH.mm"));
            } catch (DateTimeParseException exception) {
                System.out.println("Wrong format!\nPlease enter time follow next format: HH.mm");
                continue;
            }
            if(date.isEqual(LocalDate.now())) {
                if (time.isBefore(LocalTime.now())) {
                    System.out.println("Wrong time!\nIt must not be before now\nTry again");
                } else {
                    notValid = false;
                }
            } else {
                notValid = false;
            }
        }
        return time;
    }

    private void writePageContent(String[] content) {
        for(int i = 0; i < content.length; i++) {
            System.out.println((i + 1) + " - " + content[i]);
        }
    }

    private void writeSubPageContent(String[] content) {
        for(int i = 0; i < content.length; i++) {
            System.out.println(i + " - " + content[i]);
        }
    }

    private int menuValidationInput(int range) {
        int userInput = -1;
        boolean notValid = true;
        while (notValid) {
            try {
                userInput = Integer.parseInt(readUserInput());
            } catch (NumberFormatException exception) {
                System.out.println("Wrong value!\nIt must be a number. Please, enter correct symbols");
                continue;
            }
            if(userInput < 1 || userInput > range) {
                System.out.println("Value must be in range form 1 to " + range + "\nPlease, chose correct option!");
            } else {
                notValid = false;
            }
        }
        return userInput;
    }

    private int subMenuValidationInput(int range) {
        int userInput = -1;

        boolean notValid = true;
        while (notValid) {
            try {
                userInput = Integer.parseInt(readUserInput());
            } catch (NumberFormatException exception) {
                System.out.println("Wrong value!\nIt must be a number. Please, enter correct symbols");
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
