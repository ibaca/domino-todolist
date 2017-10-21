package com.progressoft.brix.domino.sample.layout.client.ui.views;

import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.layout.client.presenters.LayoutPresenter;
import com.progressoft.brix.domino.sample.layout.client.views.LayoutView;
import elemental2.dom.DomGlobal;
import jsinterop.base.Js;

import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.LayoutContent;
import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.LayoutMenuItem;


@UiView(presentable = LayoutPresenter.class)
public class ElementoLayoutView implements LayoutView {

    private Layout layout;
    private LayoutUiHandlers uiHandlers;

    public ElementoLayoutView() {
        layout = Layout.create();
    }

    @Override
    public void setUiHandlers(LayoutUiHandlers uiHandlers) {
        this.uiHandlers=uiHandlers;
        layout.addButton.addEventListener("tap", event -> this.uiHandlers.onCreate());
    }

    @Override
    public void show() {
        DomGlobal.document.body.appendChild(layout.asElement());
        uiHandlers.onShow();
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