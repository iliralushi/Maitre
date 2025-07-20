package view;

import javax.swing.*;

public class MyFrame extends JFrame
{
    private JMenuBar menuBar;

    private JMenu addMenu;
    private JMenuItem addButton;

    private JMenu editMenu;
    private JMenuItem editButton;

    private JMenu deleteMenu;
    private JMenuItem deleteButton;

    private JMenu printMenu;
    private JMenuItem printButton;

    private JMenu loadMenu;
    private JMenuItem loadButton;

    private JMenu saveMenu;
    private JMenuItem saveButton;

    public MyFrame(String title)
    {
        super(title);

        menuBar = new JMenuBar();

        addMenu = new JMenu("Add");
        addButton = new JMenuItem("Add");
        addMenu.add(addButton);
        menuBar.add(addMenu);

        editMenu = new JMenu("Edit");
        editButton = new JMenuItem("Edit");
        editMenu.add(editButton);
        menuBar.add(editMenu);

        deleteMenu = new JMenu("Delete");
        deleteButton = new JMenuItem("Delete");
        deleteMenu.add(deleteButton);
        menuBar.add(deleteMenu);

        printMenu = new JMenu("Print");
        printButton = new JMenuItem("Print");
        printMenu.add(printButton);
        menuBar.add(printMenu);

        loadMenu = new JMenu("Load");
        loadButton = new JMenuItem("Load");
        loadMenu.add(loadButton);
        menuBar.add(loadMenu);

        saveMenu = new JMenu("Save");
        saveButton = new JMenuItem("Save");
        saveMenu.add(saveButton);
        menuBar.add(saveMenu);

        this.setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(275, 150, 1366, 768); 
        setVisible(true);
    }
}