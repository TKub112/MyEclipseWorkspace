package user;

import java.nio.file.Path;

import lombok.Data;

/**
 * klasa reprezentuje dane do wykonania dla tasku
 * @see Task
 */
@Data
public class TaskData
    {
    private Path filePath;
    private String userName;
    private String sendTo;
    private Client user;

    /**
     * @see TaskData#TaskData(Path, String, String, Client)
     */
    public TaskData(Path filePath, String userName, String sendTo)
        {
        this.filePath = filePath;
        this.userName = userName;
        this.sendTo = sendTo;
        }


    /**
     * @param filePath sciezka do pliku
     * @param userName nazwa usera
     * @param sendTo nazwa usera do ktorego wysylamy
     * @param user  kalsa reprezentujaca usera
     */
    public TaskData(Path filePath, String userName, String sendTo, Client user)
        {
        this.filePath = filePath;
        this.userName = userName;
        this.sendTo = sendTo;
        this.user=user;
        }
    }
