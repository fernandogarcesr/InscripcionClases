
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
        inscripcion.setMetadatos(
        "ID-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
        java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
    );

    javax.swing.JFrame frameFicha = new javax.swing.JFrame("Ficha de Pago - ITSON");
    PantallaFichaPago fichaPanel = new PantallaFichaPago();
    
    fichaPanel.generarRecibo(inscripcion);
    
    // IMPORTANTE: Hacer que el frame de la ficha sea del mismo tamaño que la principal
    frameFicha.setSize(1024, 720); 
    frameFicha.add(fichaPanel);
    frameFicha.setResizable(false);
    frameFicha.setLocationRelativeTo(null);
    
    ins.setVisible(false);
    frameFicha.setVisible(true);
    }

    private void reiniciarFlujo() {
        this.inscripcion = new Model.Inscripcion();
        ins.limpiarParaNuevaInscripcion();
        ins.setVisible(true);
    }
}
