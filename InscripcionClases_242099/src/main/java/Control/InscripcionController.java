
package Control;

import Model.Curso;
import Model.Inscripcion;
import Vista.PantallaFichaPago;
import Vista.PantallaInscripcion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author Fernando Garces
 */
public class InscripcionController {
   private Model.Inscripcion inscripcion;
    private PantallaInscripcion ins;
    private javax.swing.JFrame frameFicha;

    public InscripcionController(PantallaInscripcion vista) {
        this.ins = vista;
        this.inscripcion = new Model.Inscripcion();
    }

    public void seleccionarCurso(Model.Curso c) {
        inscripcion.agregarCurso(c);
    int inscritos = inscripcion.getListaCursosInscritos().size();
    int disponibles = 8 - inscritos;
    
    // Llamamos a la vista para actualizar los labels
    ins.actualizarInterfaz(inscritos, disponibles, inscripcion.getMontoTotal());
    }

    public void finalizarProceso() {
        inscripcion.prepararDatosComprobante(); 

        PantallaFichaPago fichaPanel = new PantallaFichaPago();
        fichaPanel.generarRecibo(inscripcion);

        // configuramos el boton nueva inscripcion
        fichaPanel.setAccionReinicio(e -> reiniciarFlujo());

        // intercambio de paneles inmediato
        ins.setContentPane(fichaPanel);
        ins.revalidate();
        ins.repaint();
    }

    private void reiniciarFlujo() {
        this.inscripcion = new Model.Inscripcion();
        ins.getContentPane().removeAll(); // Limpiamos la ficha
        ins.getContentPane().add(ins.getPanelGeneralInscripciones()); // Regresamos el panel original
        ins.limpiarParaNuevaInscripcion();
        ins.revalidate();
        ins.repaint();
    }
}
