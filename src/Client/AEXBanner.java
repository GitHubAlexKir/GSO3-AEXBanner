package Client;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.security.x509.IPAddressName;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class AEXBanner extends Application {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 100;
    public static final int NANO_TICKS = 15000000;
    public final double textSpeed = 12;
    // FRAME_RATE = 1000000000/NANO_TICKS = 50;


    private Text text;
    private double textLength;
    private double textPosition;
    private BannerController controller;
    private AnimationTimer animationTimer;

    private static String IP;

    @Override
    public void start(Stage primaryStage) throws RemoteException, NotBoundException {
        System.out.println("IP: " + IP);
        controller = new BannerController(this,IP,1099);

        Font font = new Font("Arial", HEIGHT);
        text = new Text();
        text.setFont(font);
        text.setFill(Color.WHITE);

        Pane root = new Pane();
        root.getChildren().add(text);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("AEX banner");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();


        // Start animation: text moves from right to left
        animationTimer = new AnimationTimer() {
            private long prevUpdate;

            @Override
            public void handle(long now) {
                long lag = now - prevUpdate;
                if (lag >= NANO_TICKS) {
                    textPosition -= textSpeed;
                }
                if (textPosition + textLength <0) {
                    textPosition = 1000;
                }
                text.relocate(textPosition,0);
                prevUpdate = now;

            }
            @Override
            public void start() {
                prevUpdate = System.nanoTime();
                textPosition = WIDTH;
                text.relocate(textPosition, 0);
                super.start();
            }
        };
        animationTimer.start();
    }

    public void setKoersen(String koersen) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
        text.setText(koersen);
        textLength = text.getLayoutBounds().getWidth();

    }});}

    @Override
    public void stop() throws RemoteException {
        animationTimer.stop();
        controller.stop();
    }
    public static void main(String[] args) {
        if (args.length != 0){
            IP = args[0];
        }
        else
        {
            IP = "127.0.0.1";
        }

        Application.launch(args);
    }
}
