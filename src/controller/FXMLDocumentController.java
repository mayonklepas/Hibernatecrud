package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.Bukutelpon;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.BukutelponIM;

/**
 *
 * @author Minami
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField tnama, tnohp, tfind;
    @FXML
    private Button bsave, bupdate, bdelete;
    @FXML
    TableView<Bukutelpon> tv;
    @FXML
    TableColumn<Bukutelpon, String> id, nama, nohp;

    BukutelponIM m = new BukutelponIM();
    ObservableList ols = FXCollections.observableArrayList();
    String key = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //panggil semua method yang sudah dibuat dibawah disini kecuali method clear
        read();
        find();
        save();
        update();
        delete();
        selectrow();
        
        
        
    }

    public void read() {
        tv.getItems().clear();
        List<Bukutelpon> ls = m.read();
        for (Bukutelpon l : ls) {
            ols.add(l);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        nohp.setCellValueFactory(new PropertyValueFactory<>("nohp"));
        tv.setItems(ols);
    }

    public void find() {
        tfind.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                tv.getItems().clear();
                List<Bukutelpon> ls = m.find(tfind.getText());
                for (Bukutelpon l : ls) {
                    ols.add(l);
                }

                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
                nohp.setCellValueFactory(new PropertyValueFactory<>("nohp"));
                tv.setItems(ols);
            }
        });

    }

    public void selectrow() {
        tv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                int i = tv.getSelectionModel().getSelectedIndex();
                key = String.valueOf(id.getCellData(i));
                tnama.setText(nama.getCellData(i));
                tnohp.setText(nohp.getCellData(i));
            }
        });
    }

    public void clear() {
        key = null;
        tnama.clear();
        tnohp.clear();
    }

    public void save() {
        bsave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Bukutelpon b = new Bukutelpon();
                b.setNama(tnama.getText());
                b.setNohp(tnohp.getText());
                m.save(b);
                clear();
                read();
            }
        });
    }

    public void update() {
        bupdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Bukutelpon b = new Bukutelpon();
                b.setId(Integer.parseInt(key));
                b.setNama(tnama.getText());
                b.setNohp(tnohp.getText());
                m.update(b);
                clear();
                read();
            }
        });
    }

    public void delete() {
        bdelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                Bukutelpon b = new Bukutelpon();
                b.setId(Integer.parseInt(key));
                m.delete(b);
                clear();
                read();
            }
        });
    }

}
