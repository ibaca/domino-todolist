package com.progressoft.brix.domino.sample.layout.client.ui.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.progressoft.brix.domino.api.client.annotations.UiView;
import com.progressoft.brix.domino.sample.layout.client.presenters.LayoutPresenter;
import com.progressoft.brix.domino.sample.layout.client.views.LayoutView;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.*;
import jsinterop.base.Js;

import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.*;


@UiView(presentable = LayoutPresenter.class)
public class DefaultLayoutView extends Composite implements LayoutView {

    interface DefaultLayoutViewUiBinder extends UiBinder<MaterialPanel, DefaultLayoutView> {
    }

    private static DefaultLayoutViewUiBinder uiBinder = GWT.create(DefaultLayoutViewUiBinder.class);

    @UiField
    MaterialSideNavPush sideNav;

    @UiField
    MaterialContainer mainContent;

    @UiField
    MaterialButton newItemButton;

    public DefaultLayoutView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void show(ShowingHandler showingHandler) {
        RootPanel.get().add(this);
        showingHandler.onShow();
    }

    @Override
    public void setCreateHandler(CreateItemHandler createItemHandler) {
        newItemButton.addClickHandler(event -> createItemHandler.onCreate());
    }

    @Override
    public void closeMenu() {
        if (sideNav.isOpen())
            sideNav.hide();
    }

    @Override
    public void addMenuItem(LayoutMenuItem menuItem) {
        MaterialLink materialLink = new MaterialLink();
        materialLink.setText(menuItem.text());
        materialLink.setIconType(IconType.valueOf(menuItem.icon().toUpperCase()));
        materialLink.addClickHandler(clickEvent -> menuItem.selectHandler().onSelect());
        sideNav.add(materialLink);
    }

    @Override
    public void setContent(LayoutContent content) {
        mainContent.clear();
        mainContent.add(Js.cast(content.get()));
    }
}