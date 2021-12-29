package ua.edu.sumdu.j2se.pavlenko.tasks.utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.pavlenko.tasks.model.Task;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out) {
        try(ObjectOutputStream oos = new ObjectOutputStream(out))
        {
            oos.writeInt(tasks.size());
            for (Task task : tasks) {
                oos.writeInt(task.getTitle().length());
                oos.writeUTF(task.getTitle());
                oos.writeBoolean(task.isActive());
                oos.writeInt(task.getRepeatInterval());
                if(task.isRepeated()){
                    oos.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    oos.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                } else {
                    oos.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws EOFException {
        try(ObjectInputStream ois = new ObjectInputStream(in))
        {
            int size = ois.readInt();
            for (int i = 0; i < size; i++) {
                int titleSize = ois.readInt();
                String title = ois.readUTF();
                boolean isActive = ois.readBoolean();
                int intervalTime = ois.readInt();
                if(intervalTime > 0){
                    LocalDateTime startTime = LocalDateTime.ofEpochSecond(ois.readLong(), 0, ZoneOffset.UTC);
                    LocalDateTime endTime = LocalDateTime.ofEpochSecond(ois.readLong(), 0, ZoneOffset.UTC);
                    Task task = new Task(title, startTime, endTime, intervalTime);
                    task.setActive(isActive);
                    tasks.add(task);
                } else {
                    LocalDateTime time = LocalDateTime.ofEpochSecond(ois.readLong(), 0, ZoneOffset.UTC);
                    Task task = new Task(title, time);
                    task.setActive(isActive);
                    tasks.add(task);
                }

            }
        }
        catch(EOFException e){
            throw new EOFException("Empty file");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static  void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        TaskIO.write(tasks, new FileOutputStream(file));
    }

    public static  void readBinary(AbstractTaskList tasks, File file) throws IOException {
        TaskIO.read(tasks, new FileInputStream(file));
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Task[] taskList = new Task[tasks.size()];
        int i = 0;
        for (Task task : tasks) {
            taskList[i++] = task;
        }
        try(BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(gson.toJson(taskList));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static  void read(AbstractTaskList tasks, Reader in) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(in)) {
            String line;
            StringBuilder string = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                string.append(line).append(" ");
            }
            Task[] taskList = gson.fromJson(string.toString(), new TypeToken<Task[]>() {}.getType());
            for (Task task : taskList) {
                tasks.add(task);
            }
        }
        catch(IOException e){
            e.printStackTrace();
       }
    }

    public static  void writeText(AbstractTaskList tasks, File file) throws IOException {
        TaskIO.write(tasks, new FileWriter(file));
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        TaskIO.read(tasks, new FileReader(file));
    }
}
