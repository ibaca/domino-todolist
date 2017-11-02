package com.progressoft.brix.domino.sample.layout.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.ScriptInjector;
import com.progressoft.brix.domino.api.client.ModuleConfigurator;
import com.progressoft.brix.domino.api.client.annotations.ClientModule;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.app.*;
import com.vaadin.polymer.iron.IronIconElement;
import com.vaadin.polymer.iron.IronIconsElement;
import com.vaadin.polymer.paper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


@ClientModule(name="LayoutUI")
public class LayoutUIClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(LayoutUIClientModule.class);

	public void onModuleLoad() {
		ScriptInjector.fromUrl(GWT.getModuleBaseURL()+"bower_components/webcomponents-lite.min.js").setWindow(ScriptInjector.TOP_WINDOW).setCallback(
                new Callback<Void, Exception>() {
                    @Override
                    public void onSuccess(Void result) {
						LOGGER.info("injection success");
						importPolymer();
					}

					@Override
					public void onFailure(Exception reason) {

					}
				}).inject();


		LOGGER.info("Initializing Layout client UI module ...");
		new ModuleConfigurator().configureModule(new LayoutUIModuleConfiguration());
	}

	private void importPolymer() {
		Polymer.importHref(Arrays.asList("paper-styles", "iron-icons"), o1 -> {
            Polymer.importHref(Arrays.asList(
                    AppDrawerElement.SRC,
                    AppDrawerLayoutElement.SRC,
                    AppHeaderLayoutElement.SRC,
                    AppHeaderElement.SRC,
                    AppToolbarElement.SRC,
                    AppHeaderElement.SRC,
                    PaperItemElement.SRC,
                    PaperFabElement.SRC,
                    IronIconsElement.SRC,
                    IronIconElement.SRC,
                    PaperIconButtonElement.SRC,
                    PaperBadgeElement.SRC,
                    PaperIconItemElement.SRC,
                    PaperRippleElement.SRC
            ));
            return 0;
        });
	}
}
