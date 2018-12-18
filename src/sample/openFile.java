package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class openFile {

    @FXML
    private Button btnSingle;

    @FXML
    private Button btnMulti;

    @FXML
    private ListView listView;

    public void ButtonSingle(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("E:\\digitals\\lightroom pics"));
        List<File> selectedFile = fc.showOpenMultipleDialog(null);

        if(selectedFile != null) {
            for (int i = 0; i < selectedFile.size(); i++) {
                listView.getItems().add(selectedFile.get(i).getName());
            }
        }
        else
        {
            System.out.println("Invalid File");
        }
    }

    public void ButtonMulti(ActionEvent event)
    {

    }
}
