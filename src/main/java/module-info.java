module com.msreem.dpcoinsgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.msreem.dpcoinsgame to javafx.fxml;
    exports com.msreem.dpcoinsgame;
}