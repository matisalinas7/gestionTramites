
package ABMVersion.dtos;

import java.sql.Timestamp;

public class DTODatosVersionH {
    
    private Timestamp fechaDesdeVersion;
    private Timestamp fechaHastaVersion;
    private Timestamp fechaBajaVersion;
    private int nroVersion;
    private String descripcionVersion;
    private int codTipoTramite;

    public Timestamp getFechaBajaVersion() {
        return fechaBajaVersion;
    }

    public void setFechaBajaVersion(Timestamp fechaBajaVersion) {
        this.fechaBajaVersion = fechaBajaVersion;
    }
    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }
    public Timestamp getFechaDesdeVersion() {
        return fechaDesdeVersion;
    }

    public void setFechaDesdeVersion(Timestamp fechaDesdeVersion) {
        this.fechaDesdeVersion = fechaDesdeVersion;
    }

    public Timestamp getFechaHastaVersion() {
        return fechaHastaVersion;
    }

    public void setFechaHastaVersion(Timestamp fechaHastaVersion) {
        this.fechaHastaVersion = fechaHastaVersion;
    }

    public int getNroVersion() {
        return nroVersion;
    }

    public void setNroVersion(int nroVersion) {
        this.nroVersion = nroVersion;
    }

    public String getDescripcionVersion() {
        return descripcionVersion;
    }

    public void setDescripcionVersion(String descripcionVersion) {
        this.descripcionVersion = descripcionVersion;
    }
    
    
    
}
