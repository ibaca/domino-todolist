package com.progressoft.domino.tutorial.layout.client.ui.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.layout.client.presenters.LayoutPresenter;
import com.progressoft.brix.domino.sample.layout.client.views.LayoutView;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

@UiView(presentable = LayoutPresenter.class)
public class DesktopLayoutView extends Application implements LayoutView {
    private HBox hbox;
    private static VBox menu;
    private static VBox center;
    private static ShowingHandler showingHandler;
    private static LayoutContext.CreateItemHandler createItemHandler;

    @Override
    public void show(ShowingHandler showingHandler) {
        DesktopLayoutView.showingHandler = showingHandler;
        Thread thread = new Thread(Application::launch);
        thread.start();
    }

    @Override
    public void addMenuItem(LayoutContext.LayoutMenuItem layoutMenuItem) {
        Image image = new Image(getClass().getResourceAsStream("/" + layoutMenuItem.icon() + ".png"));
        Button item = new Button("", new ImageView(image));
        item.setTooltip(new Tooltip(layoutMenuItem.text()));
        item.setCursor(Cursor.HAND);
        item.setBackground(Background.EMPTY);
        item.setMinWidth(menu.getMinWidth());
        item.setOnAction(event -> layoutMenuItem.selectHandler().onSelect());
        menu.getChildren().add(item);
    }

    @Override
    public void setContent(LayoutContext.LayoutContent content) {
        center.getChildren().add((Node) content.get());
    }

    @Override
    public void setCreateHandler(LayoutContext.CreateItemHandler createItemHandler) {
        DesktopLayoutView.createItemHandler = createItemHandler;
    }

    @Override
    public void closeMenu() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnShowing(event -> showingHandler.onShow());
        BorderPane border = new BorderPane();
        hbox = new HBox();
        border.setTop(hbox);
        hbox.setMinHeight(60);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setBackground(new Background(new BackgroundFill(Color.web("#2196f3"), CornerRadii.EMPTY, Insets.EMPTY)));
        hbox.setPadding(new Insets(10));

        menu = new VBox();
        menu.setPadding(new Insets(20, 0, 0, 0));
        BorderStroke borderStroke = new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,1,0,0));

        menu.setBorder(new Border(borderStroke));
        menu.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        menu.setMinWidth(50);
        menu.setSpacing(20);
        border.setLeft(menu);


        primaryStage.setTitle("Todo list");

        Text text = new Text("Todo List");
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        center = new VBox();
        center.setPadding(new Insets(20));
        center.setSpacing(10);
        Image image = new Image(getClass().getResourceAsStream("/add.png"));
        Button add = new Button("", new ImageView(image));
        add.setCursor(Cursor.HAND);
        add.setBackground(Background.EMPTY);
        add.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        add.setOnAction(event -> {
            createItemHandler.onCreate();
        });

        border.setCenter(center);

        hbox.setPadding(new Insets(10, 10, 10, 10));

        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        hbox.getChildren().addAll(text, spacer, add);

        primaryStage.setScene(new Scene(border, 500, 500));
        primaryStage.show();
    }
}
