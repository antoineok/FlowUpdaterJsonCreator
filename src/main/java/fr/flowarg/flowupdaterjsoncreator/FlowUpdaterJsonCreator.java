package fr.flowarg.flowupdaterjsoncreator;

import fr.flowarg.flowlogger.Logger;
import fr.flowarg.flowupdaterjsoncreator.ui.FxApplication;
import fr.flowarg.flowupdaterjsoncreator.ui.PanelManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FlowUpdaterJsonCreator
{
    private static FlowUpdaterJsonCreator instance;
    private final Logger logger;
    private final Processor processor;
    private PanelManager panelManager;

    FlowUpdaterJsonCreator() throws IOException
    {
        instance = this;
        final File logFile = new File(".", "jsoncreator.log");
        logFile.getParentFile().mkdirs();
        logFile.delete();
        logFile.createNewFile();
        this.logger = new Logger("[JsonCreator]", logFile);
        this.processor = new Processor();
        this.logger.info("Starting json creator...");
        try
        {
            Class.forName("javafx.application.Application");
        } catch (ClassNotFoundException e)
        {
            this.logger.err("You must have JavaFX !");
            this.shutdown();
        }
        Application.launch(FxApplication.class);
    }

    public void shutdown()
    {
        this.logger.info("Shutting down...");
        System.exit(0);
    }

    public Logger getLogger()
    {
        return this.logger;
    }

    public PanelManager getPanelManager()
    {
        return this.panelManager;
    }

    public void setPanelManager(Stage stage)
    {
        this.panelManager = new PanelManager(this, stage);
    }

    public Processor getProcessor()
    {
        return this.processor;
    }

    public static FlowUpdaterJsonCreator getInstance()
    {
        return instance;
    }
}
