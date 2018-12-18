package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextArea textArea;

      File savedFileName = null;   //  saved file
     private int changed = 0;       // variable for text value changed
     boolean saved = false;    // checking saved state of a file


    public void onNew()
    {
        TextArea t = textArea;
        if(changed == 1)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("popUpForSave.fxml"));
//            popUpForSave p =
            try
            {
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("popUpForSave.fxml"));
                Parent root1 = fxmlLoader.load();
                popUpForSave p = fxmlLoader.getController();
                Stage stage = new Stage();
                p.setTextArea(t);
                stage.setTitle("Text Editor");
                stage.setScene(new Scene(root1));
                stage.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
//                System.out.println("Cant load new window");
            }
        }

    }
    @FXML
    public void onOpen()
    {
        saved = true;
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\tanujs\\Desktop"));
        File selectedFile = fc.showOpenDialog(null);
        savedFileName = selectedFile;
        String para = null,pr=null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile.getAbsoluteFile()));
                para = bufferedReader.readLine() + "\n";
                pr=para;
                do
                {
                    pr = bufferedReader.readLine();
                    if(pr!=null)
                        para += (pr+"\n");
                } while(pr!=null);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            para.replaceAll("\n",System.getProperty("line.separator"));
        textArea.setText(para);
//        textarea.getText().replaceAll("\n",System.getProperty("line.separator"));
    }

    @FXML
    public void onSave()
    {
        File saveFile ;
        if(!saved) {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("C:\\Users\\tanujs\\Desktop"));
            saveFile = fc.showSaveDialog(null);
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*", ".txt"));
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("(TXT fies(*.txt))",".txt"));
        }
        else
        {
            saveFile = savedFileName ;
        }


        try
        {
//            System.out.println(saveFile.getName());
            BufferedWriter br = new BufferedWriter(new FileWriter(saveFile));

            ObservableList<CharSequence> paragraph = textArea.getParagraphs();
            Iterator<CharSequence> iter  = paragraph.iterator();
            while(iter.hasNext())
            {
                CharSequence seq = iter.next();
                br.append(seq);
                br.newLine();
            }
//            br.write(s);
            br.flush();
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSaveAs()
    {
        File saveFile = null;

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\tanujs\\Desktop"));
        saveFile = fc.showSaveDialog(null);

        if(saved)
        {
             savedFileName = saveFile;
            System.out.println(savedFileName.equals(saveFile));
        }
        /*if(!saved) {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("C:\\Users\\tanujs\\Desktop"));
             saveFile = fc.showSaveDialog(null);
        }*/

//        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*",".txt"));
        else
        {
            try
            {
//            System.out.println(saveFile.getName());
                BufferedWriter br = new BufferedWriter(new FileWriter(saveFile));

                ObservableList<CharSequence> paragraph = textArea.getParagraphs();
                Iterator<CharSequence> iter  = paragraph.iterator();
                while(iter.hasNext())
                {
                    CharSequence seq = iter.next();
                    br.append(seq);
                    br.newLine();
                }
//            br.write(s);
                br.flush();
                br.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /*@FXML
    public void onDelete()
    {

    }*/

    @FXML
    public void onAbout()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("about.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Text Editor");
            stage.setScene(new Scene(root2));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onExit()
    {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!oldValue.equals(newValue) || !newValue.equals(oldValue))
                    changed = 1;
                    }
        });
    }
}
