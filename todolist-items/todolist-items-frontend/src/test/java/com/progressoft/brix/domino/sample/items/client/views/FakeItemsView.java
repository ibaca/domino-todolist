package com.progressoft.brix.domino.sample.items.client.views;

import com.progressoft.brix.domino.sample.items.client.views.ItemsView;
import com.progressoft.brix.domino.sample.items.client.presenters.ItemsPresenter;
import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;

import java.util.List;

@UiView(presentable=ItemsPresenter.class)
public class FakeItemsView implements ItemsView {

    @Override
    public void showAdd() {

    }

    @Override
    public LayoutContext.LayoutContent getContent() {
        return null;
    }

    @Override
    public void addNewItemHandler(NewItemHandler newItemHandler) {

    }

    @Override
    public void addItem(String title, String description, SuccessAddHandler successAddHandler) {

    }

    @Override
    public void clearAll() {

    }

    @Override
    public void remove(List<TodoItem> doneItems) {

    }
}
