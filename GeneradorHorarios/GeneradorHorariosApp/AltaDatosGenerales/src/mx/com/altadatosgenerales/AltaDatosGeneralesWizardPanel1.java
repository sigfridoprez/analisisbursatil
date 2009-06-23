/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.altadatosgenerales;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;

public class AltaDatosGeneralesWizardPanel1 implements WizardDescriptor.ValidatingPanel {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private Component component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    public Component getComponent() {
        if (component == null) {
            component = new AltaDatosGeneralesVisualPanel1();
        }
        return component;
    }

    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    public boolean isValid() {
        return true;
    }

    public final void addChangeListener(ChangeListener l) {
    }

    public final void removeChangeListener(ChangeListener l) {
    }

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    public void readSettings(Object settings) {
    }

    public void storeSettings(Object settings) {
        ((WizardDescriptor) settings).putProperty("name", ((AltaDatosGeneralesVisualPanel1) getComponent()).getNombreEscuela());
        ((WizardDescriptor) settings).putProperty("address", ((AltaDatosGeneralesVisualPanel1) getComponent()).getDireccion());
    }

    public void validate() throws WizardValidationException {
        String name = ((AltaDatosGeneralesVisualPanel1) getComponent()).getNombreEscuela();
        if (name.equals("")) {
            throw new WizardValidationException(null, "El nombre de la escuela no puede estar vacio", null);
        }
    }
}

