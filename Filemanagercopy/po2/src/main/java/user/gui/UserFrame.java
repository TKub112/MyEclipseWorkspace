package user.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import user.IUser;


/**
 * reperezentuje wyswietlacz stanu usera
 */
public class UserFrame extends JFrame
    {

    private JList<String> fileList;
    private JList<String> userList;
    private DefaultListModel<String> fliesListData;
    private DefaultListModel<String> userListData;
    private IUser user;
    private JTextField action;
    private Lock lock = new ReentrantLock();


    public UserFrame(IUser user)
        {
        super(user.getName());
        this.user = user;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(new Dimension(270, 600));

        fliesListData = new DefaultListModel<>();

        fileList = new JList(fliesListData); //data has type Object[]
        fileList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fileList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        fileList.setVisibleRowCount(-1);
        fileList.setBounds(10, 10, 230, 220);
        fileList.setBackground(Color.CYAN);

        userListData = new DefaultListModel<>();

        userList = new JList(userListData);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL_WRAP);
        userList.setVisibleRowCount(-1);
        userList.setBounds(10, 230, 230, 220);
        userList.setBackground(Color.yellow);


        JButton share = new JButton("Udostepnij");
        share.setBounds(10, 450, 230, 50);
        share.addActionListener(new AbstractAction()
            {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                if (fileList.getSelectedValue() == null || userList.getSelectedValue() == null)
                    {
                    return;
                    }
                user.sendFileToServer(fileList.getSelectedValue(), userList.getSelectedValue());
                action.setText("wyslano " + fileList.getSelectedValue() + " do " + userList.getSelectedValue());
                }
            });

        action = new JTextField("pobieranie pikow z serwera");
        action.setBounds(10, 500, 230, 50);

        setLayout(null);
        add(fileList);
        add(share);
        add(userList);
        add(action);

        setVisible(true);
        }

    /**
     * doanie pliku do listy wyswietlanej
     *
     * @param fileName nawa pliku
     */
    public void showFile(String fileName)
        {
        lock.lock();
        fliesListData.addElement(fileName);
        action.setText("dodano plik " + fileName);
        lock.unlock();
        }

    /**
     * dodanie nazy usera do listy userow
     *
     * @param userName nazwa usera
     */
    public void addToUserList(String userName)
        {
        lock.lock();
        userListData.addElement(userName);
        action.setText("dodano uzytwownika " + userName);
        lock.unlock();
        }

    public void deleteFromUserList(String userName)
        {
        lock.lock();
        userListData.removeElement(userName);
        action.setText("usunieto uzytkownika " + userName);
        lock.unlock();
        }

    /**
     * usuwa plik z listy

     * @param fileName nazwa plku
     */
    public void deleteFile(String fileName)
        {
        lock.lock();
        fliesListData.removeElement(fileName);
        action.setText("usunieto plik " + fileName);
        lock.unlock();
        }

    public void cleanList()
        {
        lock.lock();
        userListData.clear();
        lock.unlock();
        }

    /**
     * @return licza wyswietlonych userow
     */
    public int userCount()
        {
        return userListData.size();
        }

    /**
     * pokazuje powiadomienie
     *
     * @param text text do wyswietlenia
     */
    public void writePrompt(String text)
        {
        lock.lock();
        action.setText(text);
        lock.unlock();
        }
    }
