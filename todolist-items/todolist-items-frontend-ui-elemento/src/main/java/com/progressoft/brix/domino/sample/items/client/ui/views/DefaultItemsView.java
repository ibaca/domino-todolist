package com.progressoft.brix.domino.sample.items.client.ui.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.sample.items.client.views.ItemsView;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import elemental2.dom.CSSProperties;
import elemental2.dom.DomGlobal;
import jsinterop.base.Js;

import java.util.List;
import java.util.function.Consumer;

import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.LayoutContent;


@UiView(presentable = ItemsPresenter.class)
public class DefaultItemsView implements ItemsView {

    private NewItem newItem;
    private ItemsList itemsList;
    private Consumer<TodoItem> changeHandler;

    public DefaultItemsView() {
        newItem = NewItem.create();
        newItem.addItemDialog.style.setProperty("width", "70%");
        itemsList = ItemsList.create();
        DomGlobal.document.body.appendChild(this.newItem.addItemDialog);
    }

    @Override
    public void showAdd() {
        this.newItem.addItemDialog.open();
    }

    @Override
    public LayoutContent getContent() {
        return itemsList;
    }

    @Override
    public void addNewItemHandler(NewItemHandler newItemHandler) {
        newItem.confirmAddButton.addEventListener("tap",
                evt -> {
                    newItemHandler.onNewItem(newItem.titleInput.getValue(), newItem.descriptionInput.getValue());
                    newItem.titleInput.setValue("");
                    newItem.descriptionInput.setValue("");
                });
    }

    @Override
    public void addItem(String title, String description, boolean done, SuccessAddHandler successAddHandler) {
        Item item = Item.create(changeHandler).init(title, description, done);
        itemsList.asElement().appendChild(item.asElement());
        successAddHandler.onSuccess(item);
    }

    @Override
    public void clearAll() {
        while (itemsList.asElement().hasChildNodes()) {
            itemsList.asElement().removeChild(itemsList.asElement().firstChild);
        }
    }

    @Override
    public void remove(List<TodoItem> doneItems) {
        doneItems.stream().map(todoItem -> ((Item) Js.cast(todoItem)).asElement())
                .forEach(i -> itemsList.asElement().removeChild(i));
    }

    @Override
    public void onItemStateChanged(Consumer<TodoItem> changeHandler) {
        this.changeHandler=changeHandler;
    }
}