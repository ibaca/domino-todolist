package com.progressoft.brix.domino.sample.layout.client.ui.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.layout.client.presenters.LayoutPresenter;
import com.progressoft.brix.domino.sample.layout.client.views.LayoutView;
import com.vaadin.polymer.paper.PaperIconButtonElement;
import com.vaadin.polymer.paper.PaperItemElement;
import com.vaadin.polymer.paper.PaperMenuButtonElement;
import elemental2.dom.DomGlobal;
import jsinterop.base.Js;

import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.*;


@UiView(presentable = LayoutPresenter.class)
public class DefaultLayoutView implements LayoutView {

    private Layout layout;

    public DefaultLayoutView() {
        layout = Layout.create();
    }

    @Override
    public void show(ShowingHandler showingHandler) {
        DomGlobal.document.body.appendChild(layout.asElement());
        showingHandler.onShow();
    }

    @Override
    public void setCreateHandler(CreateItemHandler createItemHandler) {
        layout.addButton.addEventListener("tap", event -> createItemHandler.onCreate());
    }

    @Override
    public void closeMenu() {
        layout.drawerPanel.toggle();
    }

    @Override
    public void addMenuItem(LayoutMenuItem menuItem) {
        MenuItem item = MenuItem.create().init(menuItem.icon(), menuItem.text());
        item.asElement().addEventListener("tap", evt -> menuItem.selectHandler().onSelect());
        layout.menuContainer.appendChild(item.asElement());
    }
    @Override
    public void setContent(LayoutContent content) {
        layout.content.innerHTML = "";
        layout.content.appendChild(Js.cast(content.get()));
    }
}