package user;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


import user.gui.FrameControl;
import user.gui.UserFrame;


/**
 * Klasa uruchomieniowa dla klienta
 */
public class Main
    {
    public static void main(String[] args) throws IOException, InterruptedException
        {
        String userName = "ddkowalski";
        String userPath = "ddkowalski";

        if (args.length == 2)
            {
            userName = args[0];
            userPath = args[1];
            }


        FrameControl screen = new FrameControl();
        Client user = new Client(userName, userPath, screen);
        Executor executor = Executors.newCachedThreadPool();


        //watek gui

        executor.execute(() ->
        {
        UserFrame userGui1 = new UserFrame(user);
        screen.setUserGui(userGui1);
        });

        //dodawanie pliku.
        executor.execute(() ->
        {
        try
            {
            Thread.sleep(5000);

            while (true)
                {
                user.refreshFileList();
                Thread.sleep(1000);
                user.getUsersFromServer();
                Thread.sleep(1000);
                }
            }
        catch (IOException | InterruptedException e)
            {
            e.printStackTrace();
            }

        });

        executor.execute(() ->
        {
        try
            {
            while (true)
                {
                user.cleanFileList();
                Thread.sleep(3000);
                }
            }
        catch (IOException | InterruptedException e)
            {
            e.printStackTrace();
            }

        });
        executor.execute(() ->
        {
        try
            {
            while (true)
                {
                user.refreshServerFileList();
                Thread.sleep(5000);
                }
            }
        catch (InterruptedException e)
            {
            e.printStackTrace();
            }

        });


        }


    }

