package user;

import java.io.IOException;

public interface IUser
    {

    String getName();

    boolean refreshFileList() throws IOException;

    boolean refreshServerFileList() throws IOException, InterruptedException;

    boolean getUsersFromServer();

    boolean sendFileToServer(FileData myFile);

    boolean sendFileToServer(String name, String to);
    }
