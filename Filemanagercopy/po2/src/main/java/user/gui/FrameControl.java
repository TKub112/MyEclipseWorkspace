package user.gui;

/**
 * Klasa reprezentuje interfejs do wyswietlanie informacji na ekran
 */
public class FrameControl implements Show
    {
    private UserFrame userGui;

    public void setUserGui(UserFrame userGui)
        {
        this.userGui = userGui;
        }

    public synchronized void addToFileList(String fileName)
        {
        waitIfUserGuiNull();
        userGui.writePrompt("dodawanie pliku " + fileName);
        userGui.showFile(fileName);
        }

    @Override
    public void addToUserList(String userName)
        {
        waitIfUserGuiNull();
        userGui.writePrompt("dodawanie usera " + userName);
        userGui.addToUserList(userName);
        }

    @Override
    public synchronized void deleteFromFileList(String fileName)
        {
        waitIfUserGuiNull();
        userGui.writePrompt("usuwanie pliku " + fileName);
        userGui.deleteFile(fileName);
        }

    @Override
    public void writePrompt(String text)
        {
        waitIfUserGuiNull();
        userGui.writePrompt(text);
        }

    public synchronized void cleanGui()
        {
        waitIfUserGuiNull();
        userGui.writePrompt(" pobieram liste userow " );
        userGui.cleanList();
        }

    @Override
    public int userCount()
        {
        return userGui.userCount();
        }

    private void waitIfUserGuiNull()
        {
        while (userGui == null)
            {
            try
                {
                /*czekamy az gui sie robi*/
                Thread.sleep(1000);
                }
            catch (InterruptedException e)
                {
                e.printStackTrace();
                }
            }
        }
    }
