import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    // Declaring properties of Text Editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    // File menu items
    JMenuItem newFile,openFile,saveFile;
    // Edit menu items
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;

    TextEditor(){
        // Initialize a frame
        frame = new JFrame();

        // Initialize menu bar
        menuBar = new JMenuBar();
        // Initialize text area
        textArea = new JTextArea();

        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initialize file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        // Create Content Pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // Add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        // Create Scroll Pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Add scroll pane to panel
        panel.add(scrollPane);
        // Add panel to frame
        frame.add(panel);

        // Set dimensions of frame
        frame.setBounds(100,100,600,600);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            // Perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            // Perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            // Perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            // Perform select all operation
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            // Perform close editor operation
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            // Perform open file operation
            // Open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // If we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Getting selected file
                File file = fileChooser.getSelectedFile();
                // Get the path of the selected file
                String filePath = file.getPath();
                try{
                    // Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    // Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "";
                    String output = "";
                    // Read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }
                    // Set the output String to text area
                    textArea.setText(output);
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            // Perform save file operation
            // Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // Check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // Create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    // Initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // Initialize Buffered Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // Write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            // Perform open new window operation
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}