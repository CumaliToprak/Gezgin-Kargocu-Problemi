package prolab.pkg2.pkg1;

import javafx.scene.control.TextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private String baslangicSehri;
    private static ArrayList<ArrayList<Integer>> koordinatlarX = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<ArrayList<Integer>> koordinatlarY = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<String> hesaplananMesafeDegerleriArrayList = new ArrayList<>();
    public static Group group = new Group();
    private static final double arrowLength = 7;
    private static final double arrowWidth = 7;
    private static float FullHDWitdh = 1499;
    private static float FullHDHeight = 797;
    private static final float HDWitdh = 1140;
    private static final float HDHeight = 607;
    private static int cozunurlukGenislik;
    private static int cozunurlukYukseklik;
    private static float oranX;
    private static float oranY;

    private static Text statusAnlikText;

    @Override
    public void start(Stage stage) {
        AllPairShortestPath.floydWarshall(); // tum sehirler arasi uzakliklar hesaplanip islem gorulecek.
        resimCozunurlukAta();

        statusAnlikText = new Text("-");
        GridPane gridPane = new GridPane();
        group = new Group();
        TextField textField1 = new TextField(); // sehirleri almak icin textfield
        Text informationText = new Text("(Default olarak kocaeli başlangıç ve bitiş "
                + "şehiri seçildi) Türkçe karakter KULLANILMAMALIDIR"
                + "\nSEHİRLER VİRGUL KARAKTERİYLE AYRILMALIDIR"
                + "\nKOCAELİ HARİÇ ŞEHİRELERİ GİRİNİZ:");
        Text hesaplananGuzergahlarText = new Text("HesaplananGuzergahlar:");
        Text statusText = new Text("Durum:");
        Text gelistiricilerText = new Text("Bu proje Berkay Efe ÖZCAN ve Cumali TOPRAK "
                + "tarafından geliştirlmiştir.");
        Text mesafeText = new Text("Mesafe:");
        Text hesaplananMesafeText = new Text("---km");
        mesafeText.setFont(Font.font(15));
        hesaplananMesafeText.setFont(Font.font(23));
        statusText.setFont(Font.font(15));
        statusAnlikText.setFont(Font.font(23));
        Button islemButton = new Button("Hesapla");
        Button haritaCizdirButton    = new Button("Haritayi çizdir.");
        informationText.setFont(Font.font(16));

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream("harita.png");
        } catch (FileNotFoundException ex) {
            System.out.println("harita.png acilamiyor lutfen dosyayi kontrol ediniz.");
        }
        Image image = new Image(fileInput);
        textField1.setMinWidth(600);
        textField1.setMinHeight(20);

        final ToggleGroup radioButtonGroup = new ToggleGroup();// radio buttonlarının sadece birinin secilmesi icin tanimlandi.

        RadioButton enKisaYolRadioButton = new RadioButton("EnKisaYol");

        enKisaYolRadioButton.setToggleGroup(radioButtonGroup);

        RadioButton alternetif1RadioButton = new RadioButton(":Alternetif en kisa"
                + "yol 1");
        alternetif1RadioButton.setToggleGroup(radioButtonGroup);

        RadioButton alternetif2RadioButton = new RadioButton(":Alternetif en kisa"
                + "yol 2");
        alternetif2RadioButton.setToggleGroup(radioButtonGroup);
        RadioButton alternetif3RadioButton = new RadioButton(":Alternetif en kisa"
                + "yol 3");
        alternetif3RadioButton.setToggleGroup(radioButtonGroup);
        RadioButton alternetif4RadioButton = new RadioButton(":Alternetif en kisa"
                + "yol 4");
        alternetif4RadioButton.setToggleGroup(radioButtonGroup);

        FlowPane toplamYolFlowPane = new FlowPane(mesafeText, hesaplananMesafeText);
        toplamYolFlowPane.setHgap(8);
        GridPane inputveStatus = new GridPane(); // input ve durum bilgisini hizalamak icin.
        FlowPane statusFlowPane = new FlowPane();
        statusFlowPane.getChildren().add(statusText);
        statusFlowPane.getChildren().add(statusAnlikText);
        statusFlowPane.getChildren().add(toplamYolFlowPane);

        statusFlowPane.setHgap(8);

        FlowPane sehilerInputFlowPane = new FlowPane();
        sehilerInputFlowPane.getChildren().add(informationText);
        sehilerInputFlowPane.getChildren().add(textField1);
        sehilerInputFlowPane.getChildren().add(islemButton);

        inputveStatus.add(sehilerInputFlowPane, 0, 0);
        inputveStatus.add(statusFlowPane, 1, 0);        
        inputveStatus.setHgap(50);

        FlowPane radioButtonFlowPane = new FlowPane();
        RadioButton[] radioButtonArray = {enKisaYolRadioButton, alternetif1RadioButton,
            alternetif2RadioButton, alternetif3RadioButton, alternetif4RadioButton};
        radioButtonFlowPane.setHgap(40); // checkbox arasindaki mesafe 
        //radioButtonFlowPane.setPrefWrapLength(250);

        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(cozunurlukYukseklik);
        imageView.setFitWidth(cozunurlukGenislik);
        imageView.setPreserveRatio(true);

        group.getChildren().add(imageView);

        //gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);
        
        gridPane.add(group, 0, 0);
        gridPane.add(inputveStatus, 0, 1);
        gridPane.add(radioButtonFlowPane, 0, 2);
        gridPane.add(gelistiricilerText, 0, 3);
        gridPane.setVgap(23);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);
        
        GridPane mainGridPane = new GridPane(); // ScrollPane yi merkeze hizalamak için.
        mainGridPane.add(scrollPane, 0, 0);
        mainGridPane.setAlignment(Pos.CENTER);
       
        Scene scene = new Scene(mainGridPane, 1280, 720);// fullHD olarak açılacak.
        stage.getIcons().add(new Image("file:icon.png"));// uygulamımız için ikon 
        stage.setTitle("GEZGİN KARGO PROBLEMİ");
        stage.setScene(scene);
        stage.show();

        islemButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                statusAnlikText.setText("hesaplanıyor..");
                group.getChildren().clear();
                imageView.setFitHeight(cozunurlukYukseklik);
                imageView.setFitWidth(cozunurlukGenislik);
                group.getChildren().add(imageView); // harita sifirlaniyor.
                hesaplananMesafeText.setText("---km");//textler sifilaniyor.
                radioButtonFlowPane.getChildren().clear();//radio butonlar temizleniyor.
                hesaplananMesafeDegerleriArrayList.clear();// hesaplanan mesafeler sifirlaniyor 
                koordinatlarX.clear();
                koordinatlarY.clear();
                baslangicSehri = "kocaeli";
                String ayrilmamisString = baslangicSehri + "," + textField1.getText();
                ayrilmamisString = ayrilmamisString.trim();// inputtaki bosluklar atiliyor.         
                String[] gezilecekSehirler = ayrilmamisString.split(",");

                /*
                baslangicSehri = gezilecekSehirler[0]; // girilen sehirlerden ilki baslangic sehri olarak ataniyor. 
                 */
                try {
                    AllPairShortestPath.findShortestPaths(gezilecekSehirler, koordinatlarX, koordinatlarY,
                            hesaplananMesafeDegerleriArrayList); // en kisa yollar hesaplandiriliyor.
                    int koordinatSize = koordinatlarX.size();
                    radioButtonFlowPane.getChildren().clear();// radio butonlarin oldugu satir silindi.  
                    radioButtonFlowPane.getChildren().add(hesaplananGuzergahlarText);
                    for (int i = 0; i < koordinatSize; i++) {
                        radioButtonFlowPane.getChildren().add(radioButtonArray[i]);
                    }
                    radioButtonArray[0].setSelected(true);
                    radioButtonFlowPane.getChildren().add(haritaCizdirButton);// haritayi cizmemizi saglayan buton eklendi.
                    statusAnlikText.setText("hesaplandı");
                } catch (Exception ex) {
                    statusAnlikText.setText("yanlis bir giris yaptiniz.");
                    System.out.println("yanlis bir giris yaptiniz.");
                    radioButtonFlowPane.getChildren().clear();// eger hata alinirsa radio butonlar sifirlanacak.
                }

            }
        });
        haritaCizdirButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // bu fonkisyon secilen en kisa yola gore guzergahi cizecek.
                ImageView imageView = new ImageView(image);
                imageView.setX(0);
                imageView.setY(0);
                imageView.setFitHeight(cozunurlukYukseklik);
                imageView.setFitWidth(cozunurlukGenislik);
                group.getChildren().clear();
                group.getChildren().add(imageView);

                for (int i = 0; i < radioButtonArray.length; i++) {
                    if (radioButtonArray[i].isSelected()) {
                        enKisaYollariCiz(i);
                        hesaplananMesafeText.setText(
                                hesaplananMesafeDegerleriArrayList.get(i).trim()
                                + " km");// hesaplanan deger yazdiriliyor.
                    }
                }

            }
        });

    }

    public static void okCiz(int baslangicX, int baslangicY, int sonX, int sonY) {
        // bu kodun algoritmasi internetten alinmistir.
        //https://stackoverflow.com/questions/41353685/how-to-draw-arrow-javafx-pane
        Line line = new Line();
        line.setStartX(baslangicX);
        line.setStartY(baslangicY);
        line.setEndX(sonX);
        line.setEndY(sonY);
        Line arrow1 = new Line();
        Line arrow2 = new Line();
        double ex = line.getEndX();
        double ey = line.getEndY();
        double sx = line.getStartX();
        double sy = line.getStartY();

        arrow1.setEndX(ex);
        arrow1.setEndY(ey);
        arrow2.setEndX(ex);
        arrow2.setEndY(ey);

        if (ex == sx && ey == sy) {
            // arrow parts of length 0
            arrow1.setStartX(ex);
            arrow1.setStartY(ey);
            arrow2.setStartX(ex);
            arrow2.setStartY(ey);
        } else {
            double factor = arrowLength / Math.hypot(sx - ex, sy - ey);
            double factorO = arrowWidth / Math.hypot(sx - ex, sy - ey);

            // part in direction of main line
            double dx = (sx - ex) * factor;
            double dy = (sy - ey) * factor;

            // part ortogonal to main line
            double ox = (sx - ex) * factorO;
            double oy = (sy - ey) * factorO;

            arrow1.setStartX(ex + dx - oy);
            arrow1.setStartY(ey + dy + ox);
            arrow2.setStartX(ex + dx + oy);
            arrow2.setStartY(ey + dy - ox);
        }
        group.getChildren().add(line);
        group.getChildren().add(arrow1);
        group.getChildren().add(arrow2);

    }

    public static void enKisaYollariCiz(int ninciYol) {
        ArrayList<Integer> X = koordinatlarX.get(ninciYol);
        ArrayList<Integer> Y = koordinatlarY.get(ninciYol);
        ArrayList<Integer> otelenecekKoordinat = new ArrayList<>();
        ArrayList<Integer> otelenecekKoordinatIndex = new ArrayList<>();
        int baslangic;
        int son;
        // baslangic sehrini kırmızı daire ile boyuyoruz.
        Circle circle = new Circle();
        circle.setCenterX(koordinatlarX.get(ninciYol).get(0) * oranX);// baslangic sehrinin koordinatlari veriliyor.
        circle.setCenterY(koordinatlarY.get(ninciYol).get(0) * oranY);
        circle.setRadius(9);// çap
        circle.setFill(Color.RED);// renk
        //
        group.getChildren().add(circle);
        for (int i = 2; i < Y.size() - 1; i++) {
            baslangic = Y.get(i);
            son = Y.get(i + 1);
            for (int y = 0; y < i - 1; y++) {
                if (Objects.equals(son, Y.get(y + 1))) {
                    otelenecekKoordinat.add(son);
                    otelenecekKoordinatIndex.add(i + 1);
                } else if (Objects.equals(baslangic, Y.get(y + 1)) && Objects.equals(son, Y.get(y))) // yani iki kere ayni yerden gecilecekse
                {

                    otelenecekKoordinat.add(baslangic);
                    otelenecekKoordinat.add(son);
                    otelenecekKoordinatIndex.add(i);
                    otelenecekKoordinatIndex.add(i + 1);
                }
            }
        }
        for (int i = 0; i < otelenecekKoordinatIndex.size(); i++) {
            Y.set(otelenecekKoordinatIndex.get(i), otelenecekKoordinat.get(i) - 15);

        }

        for (int i = 0; i < X.size() - 1; i++) {

            okCiz(Math.round(X.get(i) * oranX),
                    Math.round(Y.get(i) * oranY),
                    Math.round(X.get(i + 1) * oranX),
                    Math.round(Y.get(i + 1) * oranY)); // n.yoldaki yollar cizdiriliyor.

        }
    }

    public static void main(String[] args) {
        launch(args);

    }

    public static void setTextStatusAnlikText(String s) {
        statusAnlikText.setText(s);
    }

    private static void resimCozunurlukAta() {
        int result = FileUtility.dosyadanCozunurluguOku();
        // result 0 ise hd 1 ise full hd 
        if (result == 1) { // FULLHD İSE 
            cozunurlukGenislik = (int) FullHDWitdh;
            cozunurlukYukseklik = (int) FullHDHeight;
            oranX = 1;
            oranY = 1;
        } else {
            cozunurlukGenislik = (int) HDWitdh;
            cozunurlukYukseklik = (int) HDHeight;
            oranX = HDWitdh / FullHDWitdh;
            oranY = HDHeight / FullHDHeight;

        }
    }

}
