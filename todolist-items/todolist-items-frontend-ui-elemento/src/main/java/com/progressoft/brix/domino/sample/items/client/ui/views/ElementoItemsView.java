package com.progressoft.brix.domino.sample.items.client.ui.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.client.views.ItemsView;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import elemental2.dom.DomGlobal;
import jsinterop.base.Js;

import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.LayoutContent;


@UiView(presentable = ItemsPresenter.class)
public class ElementoItemsView implements ItemsView {

    private NewItem newItem;
    private ItemsList itemsList;
    private ItemsUiHandlers uiHandlers;

    public ElementoItemsView() {
        newItem = NewItem.create();
        newItem.addItemDialog.style.setProperty("width", "70%");
        itemsList = ItemsList.create();
        DomGlobal.document.body.appendChild(this.newItem.addItemDialog);
    }

    @Override
    public void setUiHandlers(ItemsUiHandlers uiHandlers) {
        this.uiHandlers=uiHandlers;
        newItem.confirmAddButton.addEventListener("tap",
                evt -> {
                    this.uiHandlers.onNewItem(newItem.titleInput.getValue(), newItem.descriptionInput.getValue());
                    newItem.titleInput.setValue("");
                    newItem.descriptionInput.setValue("");
                });
    }

    @Override
    public void showAddDialog() {
        this.newItem.addItemDialog.open();
    }

    @Override
    public LayoutContent getContent() {
        return itemsList;
    }

    @Override
    public void addItem(String title, String description, boolean done) {
        Item item = Item.create(uiHandlers).init(title, description, done);
        itemsList.asElement().appendChild(item.asElement());
        uiHandlers.onSuccessCreation(item);
    }

    @Override
    public void remove(TodoItem item) {
        itemsList.asElement().removeChild(((Item) Js.cast(item)).asElement());
    }

}