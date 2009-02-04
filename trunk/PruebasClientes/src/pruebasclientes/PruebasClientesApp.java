/*
 * PruebasClientesApp.java
 */

package pruebasclientes;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class PruebasClientesApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new PruebasClientesView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PruebasClientesApp
     */
    public static PruebasClientesApp getApplication() {
        return Application.getInstance(PruebasClientesApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) throws AWTException {
        SystemTray tray;
        Image image;
        final TrayIcon trayIcon;

        if(SystemTray.isSupported()){
            tray = SystemTray.getSystemTray();
            image = Toolkit.getDefaultToolkit().getImage("tray.gif");
            trayIcon = new TrayIcon(image, "Tray Demo", null);
            tray.add(trayIcon);
            
        }
        launch(PruebasClientesApp.class, args);
    }
}
