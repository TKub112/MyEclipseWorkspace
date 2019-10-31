package server.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import javax.swing.*;

import server.loadBalancer.ServerSaveData;

/**
 * klasa wyswietla stan serwera na ekran
 *
 */
public class ServerFrame extends JFrame
    {


    private JList<String> fileList1;
    private JList<String> fileList2;
    private JList<String> fileList3;
    private JList<String> fileList4;
    private JList<String> fileList5;

    private DefaultListModel<String> fliesListData1;
    private DefaultListModel<String> fliesListData2;
    private DefaultListModel<String> fliesListData3;
    private DefaultListModel<String> fliesListData4;
    private DefaultListModel<String> fliesListData5;
    private Path serverPath;

    /**
     * @param text umozliwia ustawienie komunikatu o aktualnej akcji
     */
    public static synchronized void setField(String text)
        {
        ServerFrame.field.setText(text);
        }

    private static JTextField field = new JTextField("serwer gotowy");

    public ServerFrame() throws IOException
        {
        super("ServerWaitForClient UserFrame");
        field.setEditable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(new Dimension(440, 850));


        serverPath = Paths.get(System.getProperty("user.home"));

        fliesListData1 = new DefaultListModel<>();

        fileList1 = new JList(fliesListData1); //data has type Object[]
        fileList1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fileList1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        fileList1.setVisibleRowCount(-1);
        fileList1.setBounds(0, 0, 440, 150);
        fileList1.setBackground(Color.green);

        listFiles(serverPath, 0, fliesListData1);


        fliesListData2 = new DefaultListModel<>();
        fileList2 = new JList(fliesListData2); //data has type Object[]
        fileList2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fileList2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        fileList2.setVisibleRowCount(-1);
        fileList2.setBounds(0, 150, 440, 150);
        listFiles(serverPath, 1, fliesListData2);

        fliesListData3 = new DefaultListModel<>();
        fileList3 = new JList(fliesListData3); //data has type Object[]
        fileList3.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fileList3.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        fileList3.setVisibleRowCount(-1);
        fileList3.setBounds(0, 300, 440, 150);
        fileList3.setBackground(Color.green);
        listFiles(serverPath, 2, fliesListData3);

        fliesListData4 = new DefaultListModel<>();
        fileList4 = new JList(fliesListData4); //data has type Object[]
        fileList4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fileList4.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        fileList4.setVisibleRowCount(-1);
        fileList4.setBounds(0, 450, 440, 150);
        listFiles(serverPath, 3, fliesListData4);

        fliesListData5 = new DefaultListModel<>();
        fileList5 = new JList(fliesListData5); //data has type Object[]
        fileList5.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fileList5.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        fileList5.setVisibleRowCount(-1);
        fileList5.setBounds(0, 600, 440, 150);
        fileList5.setBackground(Color.green);
        listFiles(serverPath, 4, fliesListData5);

        field.setBounds(0, 750, 440, 30);


        setLayout(null);
        add(fileList1);
        add(fileList2);
        add(fileList3);
        add(fileList4);
        add(fileList5);
        add(field);

        setVisible(true);
        setBackground(Color.CYAN);
        }

    /**
     * aktualizuje graficzna zawartosc serwera
     *
     * @throws IOException
     */
    public void refresh() throws IOException
        {
        listFiles(serverPath, 0, fliesListData1);
        listFiles(serverPath, 1, fliesListData2);
        listFiles(serverPath, 2, fliesListData3);
        listFiles(serverPath, 3, fliesListData4);
        listFiles(serverPath, 4, fliesListData5);
        }

    private void listFiles(Path serverPath, int server, DefaultListModel<String> list) throws IOException
        {
        ServerSaveData.lockForLogs.lock();
        list.clear();
        Files.list(serverPath.resolve("server" + server)).flatMap((e) ->
        {
        try
            {
            return Files.list(e);
            }
        catch (IOException ex)
            {
            }
        return null;
        }).map(e -> e.getFileName().toString()).filter(Objects::nonNull).forEach(e -> list.addElement(e));
        ServerSaveData.lockForLogs.unlock();
        }
    }
