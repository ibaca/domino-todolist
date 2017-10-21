package com.progressoft.domino.tutorial.items.client.ui.views;

import com.progressoft.brix.domino.sample.items.client.views.ItemsView;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TodoItemPanel extends HBox implements TodoItem {

    private final ItemsView.ItemsUiHandlers uiHandlers;
    private String itemTitle;
    private String itemDescription;
    private VBox container;
    private HBox titleContainer;
    private CheckBox selector;
    private HBox descriptionContainer;
    private Text titleLabel;
    private Text descriptionLabel;

    public TodoItemPanel(String itemTitle, String itemDescription, boolean done, ItemsView.ItemsUiHandlers uiHandlers) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.uiHandlers = uiHandlers;
        container = new VBox();
        container.setSpacing(5);

        initTitleContainer();
        initDescriptionContainer();

        titleLabel = initLabel(itemTitle);
        descriptionLabel = initLabel(itemDescription);

        initSelector(done);
        updateStyles(done);

        titleContainer.getChildren().add(selector);
        titleContainer.getChildren().add(titleLabel);
        descriptionContainer.getChildren().add(descriptionLabel);
        container.getChildren().add(titleContainer);
        container.getChildren().add(descriptionContainer);

        this.getChildren().add(container);
    }

    private void initSelector(boolean done) {
        selector = new CheckBox();
        selector.setSelected(done);
        selector.setOnAction(event -> {
            updateStyles(selector.isSelected());
            uiHandlers.onItemStateChanged(this);
        });
    }

    private void updateStyles(boolean done) {
        descriptionLabel.setStrikethrough(done);
        titleLabel.setStrikethrough(done);
    }

    private void initDescriptionContainer() {
        descriptionContainer = new HBox();
        descriptionContainer.setPadding(new Insets(0, 0, 5, 10));
    }

    private void initTitleContainer() {
        titleContainer = new HBox();
        titleContainer.setPadding(new Insets(10));
        titleContainer.setSpacing(10);
    }

    private Text initLabel(String value) {
        Text titleLabel = new Text(value);
        titleLabel.setFont(Font.font("Sans-serif", FontWeight.BOLD, 14));
        return titleLabel;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public boolean isDone() {
        return selector.isSelected();
    }

    @Override
    public String getItemTitle() {
        return itemTitle;
    }

    @Override
    public String getItemDescription() {
        return itemDescription;
    }

    public void setDone(boolean done) {
        this.selector.setSelected(done);
    }

    @Override
    public void toggle() {
        this.selector.setSelected(!this.selector.isSelected());
    }
}
