package com.progressoft.brix.domino.sample.layout.client.ui.views;

import com.vaadin.polymer.app.AppDrawerElement;
import com.vaadin.polymer.paper.PaperFabElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;

@Templated
public abstract class Layout implements IsElement<HTMLElement>{

    @DataElement
    AppDrawerElement drawerPanel;

    @DataElement
    PaperFabElement addButton;

    @DataElement
    HTMLDivElement menuContainer;

    @DataElement
    HTMLDivElement content;

    public static Layout create(){
        return new Templated_Layout();
    }
}
