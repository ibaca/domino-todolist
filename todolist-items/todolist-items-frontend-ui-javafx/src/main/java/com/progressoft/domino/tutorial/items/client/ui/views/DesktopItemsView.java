package com.progressoft.domino.tutorial.items.client.ui.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.client.views.ItemsView;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.Optional;

@UiView(presentable = ItemsPresenter.class)
public class DesktopItemsView implements ItemsView {


    private final VBox vBox = new VBox();
    private ItemsUiHandlers uiHandlers;

    @Override
    public void setUiHandlers(ItemsUiHandlers uiHandlers) {
        this.uiHandlers=uiHandlers;
    }

    @Override
    public void showAddDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add item");

        ButtonType loginButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField title = new TextField();
        title.setPromptText("Title");
        TextArea description = new TextArea();
        description.setPromptText("Description");


        grid.add(new Label("Title:"), 0, 0);
        grid.add(title, 1, 0);
        Label descriptionLabel = new Label("Description:");
        grid.add(descriptionLabel, 0, 1);
        grid.add(description, 1, 1);
        GridPane.setValignment(descriptionLabel, VPos.TOP);
        Node addButton = dialog.getDialogPane().lookupButton(loginButtonType);
        addButton.setDisable(true);

        description.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(title::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) return new Pair<>(title.getText(), description.getText());
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(titleDesc -> uiHandlers.onNewItem(titleDesc.getKey(), titleDesc.getValue()));
    }

    @Override
    public LayoutContext.LayoutContent<VBox> getContent() {
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(10));
        return () -> vBox;
    }


    @Override
    public void addItem(String title, String description, boolean done) {
        Platform.runLater(() -> {
            TodoItemPanel todoItem = new TodoItemPanel(title, description, done, uiHandlers);
            todoItem.setMinHeight(50);
            todoItem.setMinWidth(vBox.getMinWidth() - 100);
            todoItem.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            todoItem.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(3), BorderStroke.DEFAULT_WIDTHS)));
            vBox.getChildren().add(todoItem);
            uiHandlers.onSuccessCreation(todoItem);
        });
    }

    @Override
    public void remove(TodoItem item) {
        Platform.runLater(() -> vBox.getChildren().remove(item));
    }

}
