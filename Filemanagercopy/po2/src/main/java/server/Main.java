package server;

import server.gui.ServerFrame;

/**
 * Klasa uruchomieniowa dla serwera
 */
public class Main
    {
    public static void main(String[] args)
        {
        try
            {
            new Thread(new ServerWaitForClient()).start();
            ServerFrame gui = new ServerFrame();

            new Thread(() ->
            {
            try
                {
                while (true)
                    {
                    gui.refresh();
                    gui.setField("odswiezam liste");
                    Thread.sleep(4000);
                    }
                }
            catch (Exception e)
                {
                e.printStackTrace();
                }
            }).start();

            }
        catch (Exception e)
            {
            e.printStackTrace();
            }
        }
    }
