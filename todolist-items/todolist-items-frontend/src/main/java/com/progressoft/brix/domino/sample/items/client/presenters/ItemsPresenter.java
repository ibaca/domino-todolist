package com.progressoft.brix.domino.sample.items.client.presenters;

import com.progressoft.brix.domino.api.client.annotations.InjectContext;
import com.progressoft.brix.domino.api.client.mvp.presenter.Presentable;
import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutExtensionPoint;

import java.util.List;

import static com.progressoft.brix.domino.sample.items.shared.response.LoadItemsResponse.*;


public interface ItemsPresenter extends Presentable{

    @InjectContext(extensionPoint=LayoutExtensionPoint.class)
    void contributeToLayoutModule(LayoutContext context);

    void onItemAdded(TodoItem item);

    void onItemsLoaded(List<Item> items);

    void onItemsCleared(boolean cleared);

    void onDoneCleared();
}