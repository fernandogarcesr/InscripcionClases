
package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Fernando Garces
 */
public class Inscripcion {
    private List<Curso> listaCursosInscritos = new ArrayList<>(); 
    private double montoTotal = 0.0; 
    private String idTransaccion;
    private String fechaEmision;

    public void agregarCurso(Curso c) { 
        listaCursosInscritos.add(c);
        recalcularTotal();
    }

    public double recalcularTotal() { 
        this.montoTotal = listaCursosInscritos.stream().mapToDouble(Curso::getCosto).sum();
        return this.montoTotal;
    }

    public void setMetadatos(String id, String fecha) {
        this.idTransaccion = id;
        this.fechaEmision = fecha;
    }
    
    public void prepararDatosComprobante() {
        // Generamos los datos dinámicos solicitados
    String id = "ID-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    String fecha = java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    this.setMetadatos(id, fecha); // Usamos tu método existente
    }
    
    public List<Curso> getListaCursosInscritos() { return listaCursosInscritos; }
    public double getMontoTotal() { return montoTotal; }
    public String getIdTransaccion() { return idTransaccion; }
    public String getFechaEmision() { return fechaEmision;
}
}