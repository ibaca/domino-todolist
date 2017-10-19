package com.progressoft.brix.domino.sample.layout.client.presenters;

import com.progressoft.brix.domino.api.client.annotations.Presenter;
import com.progressoft.brix.domino.api.client.mvp.presenter.BaseClientPresenter;
import com.progressoft.brix.domino.api.shared.extension.MainContext;
import com.progressoft.brix.domino.sample.layout.client.views.LayoutView;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext;
import com.progressoft.brix.domino.sample.layout.shared.extension.LayoutExtensionPoint;

@Presenter
public class DefaultLayoutPresenter extends BaseClientPresenter<LayoutView> implements LayoutPresenter {

    @Override
    public void contributeToMainModule(MainContext context) {
        view.show(() -> applyContributions(LayoutExtensionPoint.class, this::context));
    }

    private LayoutContext context() {
        return new DefaultLayoutContext(view);
    }
}