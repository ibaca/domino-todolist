package com.progressoft.brix.domino.sample.layout.client;

import com.google.gwtmockito.GwtMockitoTestRunner;
import com.progressoft.brix.domino.api.client.annotations.ClientModule;
import com.progressoft.brix.domino.sample.layout.client.presenters.LayoutPresenter;
import com.progressoft.brix.domino.sample.layout.client.presenters.LayoutPresenterSpy;
import com.progressoft.brix.domino.sample.layout.client.views.FakeLayoutView;
import com.progressoft.brix.domino.test.api.client.ClientContext;
import com.progressoft.brix.domino.test.api.client.DominoTestClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.progressoft.brix.domino.sample.layout.shared.extension.LayoutContext.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@ClientModule(name = "TestLayout")
@RunWith(GwtMockitoTestRunner.class)
public class LayoutClientModuleTest {

    private LayoutPresenterSpy presenterSpy;
    private FakeLayoutView fakeView;
    private ClientContext clientContext;
    private FakeLayoutContribution fakeLayoutContribution;
    private LayoutMenuItem testLayoutItem;

    @Before
    public void setUp() {
        presenterSpy = new LayoutPresenterSpy();
        DominoTestClient.useModules(new LayoutModuleConfiguration(), new TestLayoutModuleConfiguration())
                .replacePresenter(LayoutPresenter.class, presenterSpy)
                .viewOf(LayoutPresenter.class, view -> fakeView = (FakeLayoutView) view)
                .contributionOf(FakeLayoutContribution.class, contribution -> fakeLayoutContribution = contribution)
                .onStartCompleted(clientContext -> this.clientContext = clientContext)
                .start();

        testLayoutItem = new LayoutMenuItem() {
            @Override
            public String icon() {
                return "icon";
            }

            @Override
            public String text() {
                return "layout item text";
            }

            @Override
            public SelectHandler selectHandler() {
                return () -> {};
            }
        };
    }

    @Test
    public void givenLayoutModule_whenContributingToMainExtensionPoint_thenShouldReceiveMainContextAndShowLayout() {
        assertNotNull(presenterSpy.getMainContext());
        assertThat(fakeView.isVisible()).isTrue();
    }

    @Test
    public void givenLayoutIsVisible_whenContributingToLayout_thenShouldReceiveLayoutContext() throws Exception {
        assertThat(fakeLayoutContribution.context).isNotNull();
    }

    @Test(expected = MenuItemConnotBeNullException.class)
    public void givenLayoutContext_whenAddNullMenuItem_thenShouldThrowException() throws Exception {
        fakeLayoutContribution.context.addMenuItem(null);
    }

    @Test
    public void givenLayoutContext_whenAddMenuItem_thenMenuItemShouldBeAddedToLayoutView() throws Exception {

        fakeLayoutContribution.context.addMenuItem(testLayoutItem);
        assertThat(fakeView.layoutMenuItems).contains(testLayoutItem);
    }

    @Test
    public void givenLayoutContext_whenSetContent_thenContentShouldSetInLayoutView() throws Exception {
        final LayoutContent<Object> content= () -> null;
        fakeLayoutContribution.context.setContent(content);
        assertThat(fakeView.content).isEqualTo(content);
    }

    @Test(expected = ContentConnotBeNullException.class)
    public void givenLayoutContext_whenSetNullContent_thenThrowException() throws Exception {
        fakeLayoutContribution.context.setContent(null);
    }

    @Test
    public void givenLayoutContext_whenSetOnCreateHandlerAndViewCallsOnCreate_thenShouldShouldCallTheOnCreateHandler() throws Exception {

        final boolean[] handlerCalled = {false};
        CreateItemHandler createItemHandler= () -> {
            handlerCalled[0] =true;
        };
        fakeLayoutContribution.context.setOnCreatHandler(createItemHandler);
        fakeView.onCreate();
        assertThat(presenterSpy.receivedCreateEvent).isTrue();
        assertThat(handlerCalled[0]).isTrue();
    }

    @Test(expected = HandlerConnotBeNullException.class)
    public void givenLayoutContext_whenSetNullCreateHandler_thenShouldThrowException() throws Exception {
        fakeLayoutContribution.context.setOnCreatHandler(null);
    }


}
