module com.app.admin.animes.admon_app_animes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.dotenv;
    requires java.sql;


    opens com.app.admin.animes.admon_app_animes to javafx.fxml;
    exports com.app.admin.animes.admon_app_animes;
}