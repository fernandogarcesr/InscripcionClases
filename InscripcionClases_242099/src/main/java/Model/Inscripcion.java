
package Model;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<Curso> getListaCursosInscritos() { return listaCursosInscritos; }
    public double getMontoTotal() { return montoTotal; }
    public String getIdTransaccion() { return idTransaccion; }
    public String getFechaEmision() { return fechaEmision;
}
}