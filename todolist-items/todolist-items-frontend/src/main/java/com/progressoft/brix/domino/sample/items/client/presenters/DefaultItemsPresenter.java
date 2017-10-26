package com.progressoft.brix.domino.sample.items.client.presenters;

import com.progressoft.brix.domino.api.client.annotations.Presenter;
import com.progressoft.brix.domino.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.brix.domino.sample.items.client.requests.*;
import com.progressoft.brix.domino.sample.items.client.views.ItemsView;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Presenter
public class DefaultItemsPresenter extends BaseClientPresenter<ItemsView> implements ItemsPresenter,
        ItemsView.ItemsUiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultItemsPresenter.class);

    protected List<TodoItem> addedItems = new ArrayList<>();

    @Override
    public void onNewItem(String title, String description) {
        new AddItemServerRequest()
                .onSuccess(response -> {
                    if (response.isAdded()) {
                        view.addItem(title, description, false);
                        LOGGER.info("Todo Items - Item added to view " + title);
                    }
                })
                .onFailed(failedResponse -> {

                }).send(new AddItemRequest(title, description, false));
    }

    @Override
    public void onSuccessCreation(TodoItem item) {
        addedItems.add(item);
    }

    @Override
    public void onItemStateChanged(TodoItem item) {
        new ToggleItemServerRequest().onSuccess(response -> {

        }).send(new ToggleItemRequest(item.getItemTitle()));
    }

    @Override
    public void initView(ItemsView view) {
        view.setUiHandlers(this);
    }

    @Override
    public void contributeToLayoutModule(LayoutContext context) {

        loadItems();
        context.setContent(view.getContent());

        initDeleteAllMenuItem(context);
        initClearDoneMenuItem(context);
        initRefreshMenuItem(context);

        initFakeMenuItems(context);

        context.setOnCreatHandler(() -> view.showAddDialog());

    }

    private void initDeleteAllMenuItem(LayoutContext context) {
        context.addMenuItem(new MenuItem("delete", "Clear All", () -> {
            context.closeMenu();
            clearAll();
            LOGGER.info("Todo Items - Clear All ");
        }));
    }

    private void initClearDoneMenuItem(LayoutContext context) {
        context.addMenuItem(new MenuItem("clear", "Clear Done", () -> {
            context.closeMenu();
            removeDoneItems();
            LOGGER.info("Todo Items - Clear Done ");
        }));
    }

    private void initRefreshMenuItem(LayoutContext context) {
        context.addMenuItem(new MenuItem("refresh", "Refresh", () -> {
            context.closeMenu();
            refreshItems();
            LOGGER.info("Todo Items - Refresh ");
        }));
    }

    private void initFakeMenuItems(LayoutContext context) {
        context.addMenuItem(new MenuItem("settings", "Settings", () -> LOGGER.info("Todo Items - Settings")));
        context.addMenuItem(new MenuItem("help", "Help", () -> LOGGER.info("Todo Items - help")));
    }

    private void loadItems() {
        new LoadItemsServerRequest().onSuccess(response ->
                response.getItems().forEach(item -> view.addItem(item.getItemTitle(), item.getItemDescription(), item.isDone())))
                .send(new LoadItemsRequest());
    }

    private void refreshItems() {
        clearView();
        loadItems();
    }

    private void clearAll() {
        new ClearAllServerRequest().onSuccess(response -> {
            if (response.isCleared()) {
                clearView();
            } else
                LOGGER.error("Error while clearing all items");
        }).send(new RemoveRequest());
    }

    private void removeDoneItems() {
        new ClearDoneServerRequest().onSuccess(
                response -> clearView(addedItems.stream().filter(TodoItem::isDone).collect(Collectors.toList())))
                .send(new RemoveRequest());
    }

    private void clearView(List<TodoItem> items) {
        items.forEach(view::remove);
        addedItems.removeAll(items);
    }

    private void clearView() {
        clearView(addedItems);
    }
}