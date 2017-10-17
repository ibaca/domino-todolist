package com.progressoft.brix.domino.sample.items.client.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialLabel;

import java.util.function.Consumer;

import static com.progressoft.brix.domino.sample.items.client.ui.views.Bundle.INSTANCE;


public class Item extends Composite implements TodoItem {

    private final Consumer<TodoItem> changeHandler;
    @UiField
    MaterialLabel titleField;

    @UiField
    MaterialLabel descriptionField;

    @UiField
    MaterialCheckBox selector;

    MaterialCollectionItem root;

    @Override
    public boolean isDone() {
        return selector.getValue();
    }

    @Override
    public String getItemDescription() {
        return descriptionField.getText();
    }

    @Override
    public String getItemTitle() {
        return titleField.getText();
    }

    interface ItemUiBinder extends UiBinder<MaterialCollectionItem, Item> {
    }

    private static ItemUiBinder uiBinder = GWT.create(ItemUiBinder.class);

    public Item(Consumer<TodoItem> changeHandler) {
        this.changeHandler = changeHandler;
        root = uiBinder.createAndBindUi(this);
        initWidget(root);

    }

    public static Item create(Consumer<TodoItem> changeHandler) {
        return new Item(changeHandler);
    }

    public Item init(String title, String description, boolean done) {
        titleField.setText(title);
        descriptionField.setText(description);
        selector.setValue(done);
        updateStyles(done);
        return this;
    }

    @UiHandler("selector")
    void onClick(ClickEvent event) {
        updateStyles(selector.getValue());
        changeHandler.accept(this);
    }

    private void updateStyles(Boolean done) {
        if (done) {
            root.addStyleName(style().done());
        } else {
            root.removeStyleName(style().done());
        }
    }

    private ItemsBundle.Style style() {
        return INSTANCE.style();
    }

    @Override
    public void toggle() {
        this.selector.setValue(!this.selector.getValue());
    }
}
