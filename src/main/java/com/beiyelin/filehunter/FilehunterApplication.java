package com.beiyelin.filehunter;

import com.beiyelin.filehunter.view.CustomSplash;
import com.beiyelin.filehunter.view.IndexView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilehunterApplication extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(FilehunterApplication.class,IndexView.class, new CustomSplash(),args);
//        SpringApplication.run(FilehunterApplication.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }
}

