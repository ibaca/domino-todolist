package com.progressoft.domino.tutorial;

import com.progressoft.brix.domino.api.client.ClientApp;
import com.progressoft.brix.domino.desktop.client.CoreModule;

import java.util.logging.Logger;

public class DesktopClientModule {

    private static final Logger LOGGER = Logger.getLogger(DesktopClientModule.class.getName());

    public static void main(String[] args) {
        CoreModule.init();
        ModuleConfigurationsLoader.load();
        ClientApp.make().run(dominoOptions -> {
            if(args.length>1) {
                String defaultServiceRoot = "http://" + args[0] + ":" + args[1] + "/service";
                dominoOptions.setDefaultServiceRoot(defaultServiceRoot);
                LOGGER.info("Application is now connected to : "+defaultServiceRoot);
            }else {
                LOGGER.info("Application is now connected to : http://localhost:8080");
            }
        });
    }
}
