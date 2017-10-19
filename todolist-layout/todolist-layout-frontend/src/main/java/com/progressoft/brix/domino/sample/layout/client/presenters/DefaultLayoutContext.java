package com.progressoft.brix.domino.sample.layout.client.presenters;

import com.progressoft.brix.domino.sample.layout.client.views.LayoutView;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public class DefaultLayoutContext implements LayoutContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLayoutContext.class);
    private final LayoutView view;


    public DefaultLayoutContext(LayoutView view) {
        this.view = view;
    }

    @Override
    public void setContent(LayoutContent content) {
        if (isNull(content))
            throw new ContentConnotBeNullException();
        view.setContent(content);
        LOGGER.info("Layout - setting main content.");
    }

    @Override
    public void closeMenu() {
        view.closeMenu();
        LOGGER.info("Layout - menu closed");
    }

    @Override
    public void addMenuItem(LayoutMenuItem layoutMenuItem) {
        if (isNull(layoutMenuItem))
            throw new MenuItemConnotBeNullException();
        view.addMenuItem(layoutMenuItem);
        LOGGER.info("Layout - adding menu item ["+layoutMenuItem.text()+"]");
    }

    @Override
    public void setShowAddNewItemDialogHandler(CreateItemHandler createItemHandler) {
        if (isNull(createItemHandler))
            throw new HandlerConnotBeNullException();
        view.setCreateHandler(createItemHandler);
        LOGGER.info("Layout - setting add item handler.");
    }
}
