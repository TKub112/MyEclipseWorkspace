package user.gui;

public interface Show
    {
    void addToFileList(String fileName);

    void addToUserList(String userName);

    void deleteFromFileList(String fileName);

    void writePrompt(String text);

    void cleanGui();

    int userCount();

    }
