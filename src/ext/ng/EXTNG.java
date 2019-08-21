/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ext.ng;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import java.io.*;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author THE SKYLINE DIGITAL
 */
public class EXTNG extends Application {
    
    @Override
    public void start(Stage Stage) throws Exception {
        Stage.setHeight(670);Stage.setWidth(1030);
        Image icon=new Image(new FileInputStream("icon.png"));
        Stage.getIcons().add(icon); Stage.setOpacity(1);
        
        BackgroundFill bf1=new BackgroundFill(Color.BLUEVIOLET,new CornerRadii(2),new Insets(0,0,0,0)); Background bg1=new Background(bf1);
        BackgroundFill bf2=new BackgroundFill(Color.DARKSLATEBLUE,new CornerRadii(2),new Insets(0,0,0,0)); Background bg2=new Background(bf2);
        
        Label wlm=new Label("Welcome");wlm.setStyle("-fx-font:normal bold 57px 'Times new Roman'");wlm.setTextFill(Color.ALICEBLUE);
        Button cont=new Button("Continue");cont.setStyle("-fx-font:normal bold 23px 'serif';-fx-background-color:purple;-fx-text-fill:azure");
        cont.setPrefWidth(270);cont.setPrefHeight(50);
        
        Image im=new Image(new FileInputStream("logo.png"));
        ImageView logo=new ImageView(im);logo.setFitHeight(340);logo.setFitWidth(980);
        ImageView ic=new ImageView(icon);ic.setFitHeight(30);ic.setFitWidth(40);
        
        Slider sl=new Slider();sl.setMin(0);sl.setMax(1);sl.setStyle("-fx-background-color:olive");
        ProgressIndicator ind=new ProgressIndicator();ind.autosize();
        Label con =new Label("Getting Connection...");con.setStyle("-fx-font: normal 19px 'Constantia'"); con.setTextFill(Color.ALICEBLUE);
        Label con2 =new Label("Loading contents...");con2.setStyle("-fx-font: normal 19px 'serif'");con2.setTextFill(Color.AQUA);
        
        WebView web=new WebView(); WebEngine gt=web.getEngine();web.setPrefWidth(1700);web.setPrefHeight(1100);
        sl.valueProperty().bind(gt.getLoadWorker().progressProperty());
        
        Text ext=new Text("EXT-NG");ext.setStyle("-fx-font:Bold 37px 'Bell MT'");ext.setStroke(Color.PLUM);ext.setFill(Color.DARKORANGE);
        
        GridPane strt=new GridPane();strt.setPadding(new Insets(2,2,2,2));strt.setHgap(4);strt.setVgap(7);strt.setAlignment(Pos.CENTER);
        strt.setBackground(bg1); strt.add(logo,0,1); strt.add(wlm,0,0);GridPane pane=new GridPane(); pane.setHgap(5); pane.setVgap(5);
        pane.add(cont, 0, 0);strt.add(pane,0,7);
        
        HBox ex=new HBox();ex.setPadding(new Insets(2));ex.setAlignment(Pos.CENTER_LEFT);ex.getChildren().addAll(ic,ext);ex.setBackground(bg2);
        HBox da=new HBox();da.setPadding(new Insets(2));da.setAlignment(Pos.CENTER_LEFT);da.getChildren().addAll(sl);da.setBackground(bg2);
        
        BorderPane bp=new BorderPane();bp.setCenter(strt);
        
        Scene sn=new Scene(bp);Stage.setTitle("EXT-NG");Stage.setScene(sn);Stage.show();
        
        //application of web app
        gt.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State>ov,State oldS,State newS)->{
           if(newS.equals(State.RUNNING)){pane.add(con2, 0, 1);}
           if(newS.equals(State.SUCCEEDED)){
               bp.setBottom(da);strt.getChildren().clear();strt.add(web,0,0);
           }
           if(newS.equals(State.FAILED)){con2.setText("-> ? Please check your connection and try again.");}
        });
        
        cont.setOnAction((new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ev){
                pane.add(ind,1,0);pane.add(con,2,0);
                gt.load("http://etxng.documentone.xyz/portal/login.aspx#"); 
            }
        }));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
