package com.progressoft.brix.domino.sample.layout.client.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.layout.client.presenters.LayoutPresenter;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;

import java.util.ArrayList;
import java.util.List;

@UiView(presentable = LayoutPresenter.class)
public class FakeLayoutView implements LayoutView {

    private boolean visible;
    public List<LayoutContext.LayoutMenuItem> layoutMenuItems = new ArrayList<>();
    public LayoutContext.LayoutContent content;
    public LayoutContext.CreateItemHandler createHandler;

    @Override
    public void addMenuItem(LayoutContext.LayoutMenuItem layoutMenuItem) {
        layoutMenuItems.add(layoutMenuItem);
    }

    @Override
    public void show(ShowingHandler showingHandler) {
        this.visible = true;
        showingHandler.onShow();
    }

    @Override
    public void setContent(LayoutContext.LayoutContent content) {
        this.content = content;
    }

    @Override
    public void setCreateHandler(LayoutContext.CreateItemHandler createItemHandler) {
        this.createHandler = createItemHandler;
    }

    @Override
    public void closeMenu() {

    }

    public boolean isVisible() {
        return visible;
    }
}
