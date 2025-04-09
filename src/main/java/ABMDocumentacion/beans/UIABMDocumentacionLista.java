package ABMDocumentacion.beans;

import ABMDocumentacion.ControladorABMDocumentacion;
import ABMDocumentacion.exceptions.DocumentacionException;
import utils.BeansUtils;

import ABMTipoTramite.dtos.DocumentacionDTO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;

@Named("uiabmDocumentacionLista")
@ViewScoped
public class UIABMDocumentacionLista implements Serializable {

    private ControladorABMDocumentacion controladorABMDocumentacion = new ControladorABMDocumentacion();
    private int codigoFiltro = 0;
    private String nombreFiltro = "";
    private String descripcionFiltro = "";

    public ControladorABMDocumentacion getControladorABMDocumentacion() {
        return controladorABMDocumentacion;
    }

    public void setControladorABMDocumentacion(ControladorABMDocumentacion controladorABMDocumentacion) {
        this.controladorABMDocumentacion = controladorABMDocumentacion;
    }

    public int getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(int codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String descripcionFiltro) {
        this.nombreFiltro = descripcionFiltro;
    }

    public String getDescripcionFiltro() {
        return descripcionFiltro;
    }

    public void setDescripcionFiltro(String descripcionFiltro) {
        this.descripcionFiltro = descripcionFiltro;
    }

    public void filtrar() {

    }

    public List<DocumentacionGrillaUI> buscarDocumentaciones() {
        List<DocumentacionGrillaUI> documentacionesGrilla = new ArrayList<>();
        List<DocumentacionDTO> documentacionesDTO = controladorABMDocumentacion.buscarDocumentaciones(codigoFiltro, nombreFiltro, descripcionFiltro);
        for (DocumentacionDTO documentacionDTO : documentacionesDTO) {
            DocumentacionGrillaUI documentacionesGrillaUI = new DocumentacionGrillaUI();
            documentacionesGrillaUI.setCodDocumentacion(documentacionDTO.getCodDocumentacion());
            documentacionesGrillaUI.setNombreDocumentacion(documentacionDTO.getNombreDocumentacion());
            documentacionesGrillaUI.setFechaHoraBajaDocumentacion(documentacionDTO.getFechaHoraBajaDocumentacion());
            documentacionesGrillaUI.setDescripcionDocumentacion(documentacionDTO.getDescripcionDocumentacion());
            documentacionesGrilla.add(documentacionesGrillaUI);
        }
        return documentacionesGrilla;
    }

    public String irAgregarDocumentacion() {
        BeansUtils.guardarUrlAnterior();
        return "abmDocumentacion?faces-redirect=true&codDocumentacion=0"; // Usa '?faces-redirect=true' para hacer una redirección
    }

    public String irModificarDocumentacion(int codDocumentacion) {
        BeansUtils.guardarUrlAnterior();
        return "abmDocumentacion?faces-redirect=true&codDocumentacion=" + codDocumentacion; // Usa '?faces-redirect=true' para hacer una redirección
    }

    public void darDeBajaDocumentacion(int codDocumentacion) {
        try {
            controladorABMDocumentacion.darDeBajaDocumentacion(codDocumentacion);
        } catch (DocumentacionException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }

    }
}
