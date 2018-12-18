package sample;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.Iterator;

public class popUpForSave {

//    private TextArea textArea = null;

    @FXML
    TextArea textArea = null;

    Controller ct = new Controller();
//    TextArea textArea ;
//    ObservableList<CharSequence> para = textArea1.getParagraphs();


    /*public popUpForSave(TextArea textArea)
    {
        this.textArea = textArea;
    }*/
    public void setTextArea(TextArea textArea)
    {
        this.textArea = textArea;
    }

    @FXML
    public void onSavePopUp(ActionEvent event) {

        ct.saved = true;

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\tanujs\\Desktop"));
        File saveFile = fc.showSaveDialog(null);
//        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*",".txt"));
//        ct.savedFileName = saveFile;
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
//        System.out.println(para);
//        ct.fun();

//        System.out.println(textArea.getParagraphs());

        textArea.setText(null);
       Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
       stage.close();
    }

    @FXML
    public void onDontSavePopUp(ActionEvent event) {

        ct.saved = false;
        textArea.setText(null);
//        System.out.println(textArea1.getText());
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCancel(ActionEvent event) {

        ct.saved = false;
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
