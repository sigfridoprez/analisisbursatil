/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.nucleo;

import javax.swing.JFrame;
import mx.com.servicios.utilerias.UtileriasSrv;
import org.openide.modules.ModuleInstall;
import org.openide.util.Lookup;
import org.openide.windows.WindowManager;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

    private UtileriasSrv utileriasSrv;

    @Override
    public void restored() {
        utileriasSrv = Lookup.getDefault().lookup(UtileriasSrv.class);

        WindowManager.getDefault().invokeWhenUIReady(new Runnable() {

            public void run() {
                JFrame frame = (JFrame) WindowManager.getDefault().getMainWindow();
                frame.setTitle(utileriasSrv.getTituloApp());
            }
        });

    }
}
