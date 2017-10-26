package com.progressoft.brix.domino.sample.items.server.handlers;

import com.progressoft.brix.domino.sample.items.server.TodoItemsStore;
import com.progressoft.brix.domino.sample.items.shared.request.AddItemRequest;
import com.progressoft.brix.domino.sample.items.shared.request.LoadItemsRequest;
import com.progressoft.brix.domino.sample.items.shared.request.RemoveRequest;
import com.progressoft.brix.domino.sample.items.shared.request.ToggleItemRequest;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.progressoft.brix.domino.test.api.DominoTestServer.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(VertxUnitRunner.class)
public class AddItemHandlerTest {

    private Vertx vertx;

    @Rule
    public RunTestOnContext vertxRule = new RunTestOnContext();
    private HttpServerContext serverContext;
    private DominoTestContext dominoContext;

    @Before
    public void setUp(TestContext testContext) throws Exception {
        vertx=vertxRule.vertx();
        vertx(vertx).onBeforeLoad(dominoTestContext -> {
            storeTestItems();
        }).onAfterLoad((dominoTestContext, httpServerContext) -> {
            dominoContext=dominoTestContext;
            serverContext=httpServerContext;
        }).start(testContext);
    }

    private void storeTestItems() {
        for(int i=0;i<10;i++){
            AddItemRequest addItemRequest=new AddItemRequest("item"+i, "itemDesc"+i, i>4);
            TodoItemsStore.store(addItemRequest.getItemTitle(), addItemRequest);
        }
    }

    @Test
    public void whenAddItemRequestIsSent_thenTodoItemShouldBeAddedToTheStore(TestContext testContext) throws Exception {
        Async async=testContext.async();
        AddItemRequest addItemRequest=new AddItemRequest("item1Title", "item1Desc", false);
        serverContext.makeServiceRequest("add")
                .sendJson(addItemRequest, event -> {
                    assertThat(TodoItemsStore.get("item1Title")).isNotNull();
                    assertThat(TodoItemsStore.get("item1Title").isDone()).isFalse();
                    assertThat(TodoItemsStore.get("item1Title").getItemTitle()).isEqualTo("item1Title");
                    assertThat(TodoItemsStore.get("item1Title").getItemDescription()).isEqualTo("item1Desc");
                    async.complete();
                });
    }

    @Test
    public void whenClearAllRequestIsSent_thenTodoItemStoreShouldBeEmpty(TestContext testContext) throws Exception {
        Async async=testContext.async();
        serverContext.makeServiceRequest("clear").putHeader("REQUEST_KEY", RemoveRequest.class.getCanonicalName()+"_clear")
                .sendJson(new RemoveRequest(), event -> {
                    assertThat(TodoItemsStore.all()).isEmpty();
                    async.complete();
                });
    }

    @Test
    public void whenClearDoneRequestIsSent_thenDoneTodoItemsShouldBeRemovedFromStore(TestContext testContext) throws Exception {
        Async async=testContext.async();
        serverContext.makeServiceRequest("removeDone").putHeader("REQUEST_KEY", RemoveRequest.class.getCanonicalName()+"_removeDone")
                .sendJson(new RemoveRequest(), event -> {
                    assertThat(TodoItemsStore.all().size()).isEqualTo(5);
                    TodoItemsStore.all().forEach(item -> assertThat(item.isDone()).isFalse());
                    async.complete();
                });
    }

    @Test
    public void whenLoadItemRequestIsSent_thenShouldReceiveAllItemsInStore(TestContext testContext) throws Exception {
        Async async=testContext.async();
        serverContext.makeServiceRequest("items")
                .sendJson(new LoadItemsRequest(), event -> {
                    assertThat(TodoItemsStore.all().size()).isEqualTo(10);
                    async.complete();
                });
    }

    @Test
    public void whenToggleItemRequestIsSent_thenItemDoneStateShouldChange(TestContext testContext) throws Exception {
        Async async1=testContext.async();
        serverContext.makeServiceRequest("toggle")
                .sendJson(new ToggleItemRequest("item0"), event -> {
                    assertThat(TodoItemsStore.get("item0").isDone()).isTrue();
                    async1.complete();
                });
        Async async2=testContext.async();
        serverContext.makeServiceRequest("toggle")
                .sendJson(new ToggleItemRequest("item9"), event -> {
                    assertThat(TodoItemsStore.get("item9").isDone()).isFalse();
                    async2.complete();
                });
    }

    @After
    public void tearDown() throws Exception {
        TodoItemsStore.clear();
    }
}
