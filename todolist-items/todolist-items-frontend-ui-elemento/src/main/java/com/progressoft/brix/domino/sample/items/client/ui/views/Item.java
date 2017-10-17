package com.progressoft.brix.domino.sample.items.client.ui.views;

import com.progressoft.brix.domino.sample.items.shared.TodoItem;
import com.vaadin.polymer.paper.PaperCheckboxElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;

import java.util.function.Consumer;


@Templated
public abstract class Item implements IsElement<HTMLElement>, TodoItem {

    @DataElement
    HTMLElement title;

    @DataElement
    HTMLDivElement description;

    @DataElement
    PaperCheckboxElement selector;

    abstract Consumer<TodoItem> changeHandler();

    public static Item create(Consumer<TodoItem> changeHandler) {
        return new Templated_Item(changeHandler);
    }

    public Item init(String title, String description, boolean done) {
        this.title.textContent = title;
        this.description.textContent = description;
        this.selector.setChecked(done);
        this.selector.addEventListener("tap", evt -> {
            updateStyles(this.selector.getChecked());
            changeHandler().accept(this);
        });
        updateStyles(done);
        return this;
    }

    private void updateStyles(boolean done) {
        if (done) {
            this.title.classList.add("done");
            this.description.classList.add("done");
        } else {
            this.title.classList.remove("done");
            this.description.classList.remove("done");
        }
    }

    @Override
    public boolean isDone() {
        return selector.getActive();
    }

    @Override
    public String getItemTitle() {
        return title.textContent;
    }

    @Override
    public String getItemDescription() {
        return description.textContent;
    }

    @Override
    public void toggle() {
        this.selector.setChecked(!this.selector.getChecked());
    }
}
