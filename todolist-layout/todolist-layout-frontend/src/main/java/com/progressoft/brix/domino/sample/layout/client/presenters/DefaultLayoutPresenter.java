package com.progressoft.brix.domino.sample.layout.client.presenters;

import com.progressoft.brix.domino.api.client.annotations.Presenter;
import com.progressoft.brix.domino.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.brix.domino.api.shared.extension.MainContext;
import com.progressoft.brix.domino.sample.layout.client.views.LayoutView;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutExtensionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

@Presenter
public class DefaultLayoutPresenter extends BaseClientPresenter<LayoutView> implements LayoutPresenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLayoutPresenter.class);

    @Override
    public void initView(LayoutView view) {
    }

    @Override
    public void contributeToMainModule(MainContext context) {
        view.show(() -> applyContributions(LayoutExtensionPoint.class, () -> new LayoutContext() {
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
        }));
    }

}