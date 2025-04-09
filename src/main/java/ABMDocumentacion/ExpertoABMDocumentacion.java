package ABMDocumentacion;

import ABMDocumentacion.dtos.ModificarDocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTOIn;
import ABMDocumentacion.dtos.NuevoDocumentacionDTO;
import ABMDocumentacion.exceptions.DocumentacionException;
import ABMTipoTramite.dtos.DocumentacionDTO;
import entidades.Documentacion;
import entidades.TipoTramiteDocumentacion;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.fechaHoraActual;

public class ExpertoABMDocumentacion {

    public List<DocumentacionDTO> buscarDocumentaciones(int codDocumentacion, String nombreDocumentacion, String descripcionDocumentacion) {
        List<DTOCriterio> lCriterio = new ArrayList<DTOCriterio>();
        if (codDocumentacion > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codDocumentacion");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codDocumentacion);
            lCriterio.add(unCriterio);
        }
        if (nombreDocumentacion.trim().length() > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreDocumentacion");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreDocumentacion);
            lCriterio.add(unCriterio);
        }

        if (descripcionDocumentacion.trim().length() > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("descripcionDocumentacion");
            unCriterio.setOperacion("like");
            unCriterio.setValor(descripcionDocumentacion);
            lCriterio.add(unCriterio);
        }
        List objetoList = FachadaPersistencia.getInstance().buscar("Documentacion", lCriterio);
        List<DocumentacionDTO> documentacionesResultado = new ArrayList<>();
        for (Object x : objetoList) {
            Documentacion documentacion = (Documentacion) x;
            DocumentacionDTO documentacionDTO = new DocumentacionDTO();
            documentacionDTO.setCodDocumentacion(documentacion.getCodDocumentacion());
            documentacionDTO.setNombreDocumentacion(documentacion.getNombreDocumentacion());
            documentacionDTO.setDescripcionDocumentacion(documentacion.getDescripcionDocumentacion());
            documentacionDTO.setFechaHoraBajaDocumentacion(documentacion.getFechaHoraBajaDocumentacion());
            documentacionesResultado.add(documentacionDTO);
        }
        return documentacionesResultado;
    }

    public void agregarDocumentacion(NuevoDocumentacionDTO nuevoDocumentacionDTO) throws DocumentacionException {

        validarDocumentacionA(nuevoDocumentacionDTO);
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codDocumentacion");
        dto.setOperacion("=");
        dto.setValor(nuevoDocumentacionDTO.getCodDocumentacion());

        criterioList.add(dto);
        List lDocumentacion = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);

        if (lDocumentacion.size() > 0) {
            throw new DocumentacionException("El código de documentacion ya existe");
        } else {
            Documentacion documentacion = new Documentacion();
            documentacion.setCodDocumentacion(nuevoDocumentacionDTO.getCodDocumentacion());
            documentacion.setNombreDocumentacion(nuevoDocumentacionDTO.getNombreDocumentacion());
            documentacion.setDescripcionDocumentacion(nuevoDocumentacionDTO.getDescripcionDocumentacion());

            FachadaPersistencia.getInstance().guardar(documentacion);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }

    public ModificarDocumentacionDTO buscarDocumentacionAModificar(int codDocumentacion) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codDocumentacion");
        dto.setOperacion("=");
        dto.setValor(codDocumentacion);

        criterioList.add(dto);
        List objetoList = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);
        Documentacion documentacionEncontrado = (Documentacion) objetoList.get(0);

        ModificarDocumentacionDTO modificarDocumentacionDTO = new ModificarDocumentacionDTO();
        modificarDocumentacionDTO.setCodDocumentacion(documentacionEncontrado.getCodDocumentacion());
        modificarDocumentacionDTO.setNombreDocumentacion(documentacionEncontrado.getNombreDocumentacion());
        modificarDocumentacionDTO.setDescripcionDocumentacion(documentacionEncontrado.getDescripcionDocumentacion());
        return modificarDocumentacionDTO;
    }

    public void modificarDocumentacion(ModificarDocumentacionDTOIn modificarDocumentacionDTOIn) throws DocumentacionException {
        
        validarDocumentacionM(modificarDocumentacionDTOIn);
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("codDocumentacion");
        dto.setOperacion("=");
        dto.setValor(modificarDocumentacionDTOIn.getCodDocumentacion());
        
        criterioList.add(dto);
        
        
        
        
        
        
        Documentacion documentacionEncontrado = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);
        
        documentacionEncontrado.setCodDocumentacion(modificarDocumentacionDTOIn.getCodDocumentacion());
        documentacionEncontrado.setNombreDocumentacion(modificarDocumentacionDTOIn.getNombreDocumentacion());
        documentacionEncontrado.setDescripcionDocumentacion(modificarDocumentacionDTOIn.getDescripcionDocumentacion());
        
        FachadaPersistencia.getInstance().guardar(documentacionEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
//
//    public void modificarDocumentacion(ModificarDocumentacionDTOIn modificarDocumentacionDTOIn) throws DocumentacionException{
//        
//        validarDocumentacionM(modificarDocumentacionDTOIn);
//        FachadaPersistencia.getInstance().iniciarTransaccion();
//
//        List<DTOCriterio> criterioList = new ArrayList<>();
//        DTOCriterio dto = new DTOCriterio();
//
//        dto.setAtributo("codDocumentacion");
//        dto.setOperacion("=");
//        dto.setValor(modificarDocumentacionDTOIn.getCodDocumentacion());
//        criterioList.add(dto);
//        DTOCriterio dto2 = new DTOCriterio ();
//        dto2.setAtributo("fechaHoraBajaDocumentacion");
//        dto2.setOperacion("=");
//        dto2.setValor(null);
//        criterioList.add(dto2);
//        
//        
//        List object = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);
//        
//        if (object.isEmpty()){
//        
//        throw new DocumentacionException ("No se puede modificar una documentacion dada de baja");
//        
//        }
//        
//        Documentacion  documentacionEncontrada = (Documentacion) object.get(0);
//        int codDocumentacionEncontrada = documentacionEncontrada.getCodDocumentacion();
//        criterioList.clear();
//        
//        dto = new DTOCriterio();
//        dto.setAtributo("fechaHoraBajaTTD");
//        dto.setOperacion("=");
//        dto.setValor(null);
//        criterioList.add(dto);
//        
//        Timestamp fechaActual = fechaHoraActual.obtenerFechaHoraActual();
//        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramiteDocumentacion", criterioList);
//        
//        for(Object x : objetoList){
//        TipoTramiteDocumentacion tipoTramiteDocumentacion = (TipoTramiteDocumentacion) x;
//        if (tipoTramiteDocumentacion.getDocumentacion() != null){
//        Documentacion documentacion = tipoTramiteDocumentacion.getDocumentacion();
//        int codigoDocumentacion = documentacion.getCodDocumentacion();
//        
//       if (codDocumentacionEncontrada == codigoDocumentacion || tipoTramiteDocumentacion.getFechaHastaTTD().before(fechaActual)) {
//                    throw new DocumentacionException("No puede modificarse esta documentación porque esta asignada a un TipoTramite activo");
//        }
// 
//        
//        }
//        }
//        Documentacion documentacionEncontrado = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);
//
//        documentacionEncontrado.setCodDocumentacion(modificarDocumentacionDTOIn.getCodDocumentacion());
//        documentacionEncontrado.setNombreDocumentacion(modificarDocumentacionDTOIn.getNombreDocumentacion());
//        documentacionEncontrado.setDescripcionDocumentacion(modificarDocumentacionDTOIn.getDescripcionDocumentacion());
//
//        FachadaPersistencia.getInstance().guardar(documentacionEncontrado);
//        FachadaPersistencia.getInstance().finalizarTransaccion();
//    }

//    public void darDeBajaDocumentacion(int codDocumentacion) throws DocumentacionException {
//        FachadaPersistencia.getInstance().iniciarTransaccion();
//
//        List<DTOCriterio> criterioList = new ArrayList<>();
//        DTOCriterio dto = new DTOCriterio();
//
//        dto.setAtributo("codDocumentacion");
//        dto.setOperacion("=");
//        dto.setValor(codDocumentacion);
//        criterioList.add(dto);
//
//        DTOCriterio dto2 = new DTOCriterio();
//        dto2.setAtributo("fechaHoraBajaDocumentacion");
//        dto2.setOperacion("=");
//        dto2.setValor(null);
//        criterioList.add(dto2);
//
//        List object = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);
//
//        if (object.isEmpty()) {
//
//            throw new DocumentacionException("Documentacion seleccionada ya dada de baja");
//
//        }
//
//        Documentacion documentacionEncontrada = (Documentacion) object.get(0);
//        int codDocumentacionEncontrada = documentacionEncontrada.getCodDocumentacion();
//        criterioList.clear();
//
//        dto = new DTOCriterio();
//        dto.setAtributo("fechaHoraBajaTTD");
//        dto.setOperacion("=");
//        dto.setValor(null);
//        criterioList.add(dto);
//
//        Timestamp fechaActual = fechaHoraActual.obtenerFechaHoraActual();
//        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramiteDocumentacion", criterioList);
//
//        for (Object x : objetoList) {
//            TipoTramiteDocumentacion tipoTramiteDocumentacion = (TipoTramiteDocumentacion) x;
//            if (tipoTramiteDocumentacion.getDocumentacion() != null) {
//                Documentacion documentacion = tipoTramiteDocumentacion.getDocumentacion();
//                int codigoDocumentacion = documentacion.getCodDocumentacion();
//
//                if (codDocumentacionEncontrada == codigoDocumentacion && tipoTramiteDocumentacion.getFechaHastaTTD().before(fechaActual)) {
//                    throw new DocumentacionException("No puede darse de baja esta documentación porque esta asignada a un TipoTramite activo");
//                }
//
//     
//            }
//        }
//        documentacionEncontrada.setFechaHoraBajaDocumentacion(fechaHoraActual.obtenerFechaHoraActual());
//
//        FachadaPersistencia.getInstance().guardar(documentacionEncontrada);
//        FachadaPersistencia.getInstance().finalizarTransaccion();
////        Documentacion documentacionEncontrado = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);
////
////        documentacionEncontrado.setFechaHoraBajaDocumentacion(new Timestamp(System.currentTimeMillis()));
////
////        FachadaPersistencia.getInstance().guardar(documentacionEncontrado);
////        FachadaPersistencia.getInstance().finalizarTransaccion();
//    }

    
    
    
   public void darDeBajaDocumentacion(int codDocumentacion) throws DocumentacionException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codDocumentacion");
        dto.setOperacion("=");
        dto.setValor(codDocumentacion);
        criterioList.add(dto);

        DTOCriterio dto2 = new DTOCriterio();
        dto2.setAtributo("fechaHoraBajaDocumentacion");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);

        List object = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);

        if (object.isEmpty()) {

            throw new DocumentacionException("Documentacion seleccionada ya dada de baja");

        }

        Documentacion documentacionEncontrada = (Documentacion) object.get(0);
        int codDocumentacionEncontrada = documentacionEncontrada.getCodDocumentacion();
        criterioList.clear();

        dto = new DTOCriterio();
        dto.setAtributo("fechaHoraBajaTTD");
        dto.setOperacion("=");
        dto.setValor(null);
        criterioList.add(dto);

        Timestamp fechaActual = fechaHoraActual.obtenerFechaHoraActual();
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramiteDocumentacion", criterioList);

        for (Object x : objetoList) {
            TipoTramiteDocumentacion tipoTramiteDocumentacion = (TipoTramiteDocumentacion) x;

            if (tipoTramiteDocumentacion.getDocumentacion() != null) {
                Documentacion documentacion = tipoTramiteDocumentacion.getDocumentacion();
                int codigoDocumentacion = documentacion.getCodDocumentacion();

                // Comprobar si la documentación que intentamos dar de baja está asociada
                // a un TipoTramite con fecha activa
                if (codDocumentacionEncontrada == codigoDocumentacion) {
                    // Verificar si el TipoTramiteDocumentacion está activo
                    if (tipoTramiteDocumentacion.getFechaHastaTTD() != null
                            && tipoTramiteDocumentacion.getFechaHastaTTD().after(fechaActual)) {
                        // Si la fecha de finalización del tipo de trámite es futura, no se puede dar de baja
                        Messages.create("Error").detail("No puede darse de baja esta documentación porque está asignada a un TipoTramite activo").error().add();
                        return; 
                    }
                }
            }
        }


        documentacionEncontrada.setFechaHoraBajaDocumentacion(fechaActual);
        FachadaPersistencia.getInstance().guardar(documentacionEncontrada);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
    
    
    private void validarDocumentacionA(NuevoDocumentacionDTO documentacionDTO) throws DocumentacionException {

        if (documentacionDTO.getCodDocumentacion() <= 0) {
            throw new DocumentacionException("El codigo debe ser un entero mayor a cero.");
        }

        String nombreDocumentacion = documentacionDTO.getNombreDocumentacion();
        if (nombreDocumentacion == null || nombreDocumentacion.trim().isEmpty() || nombreDocumentacion.length() > 255) {
            throw new DocumentacionException("El nombre debe tener entre 1 y 255 caracteres.");
        }

        String descripcionDocumentacion = documentacionDTO.getDescripcionDocumentacion();
        if (descripcionDocumentacion == null || descripcionDocumentacion.trim().isEmpty() || descripcionDocumentacion.length() > 255) {
            throw new DocumentacionException("La descripcion debe tener entre 1 y 255 caracteres.");
        }

        List<DTOCriterio> criterioCodigoDocumentacion = new ArrayList<>();
        DTOCriterio dtoCodigoDoc = new DTOCriterio();
        dtoCodigoDoc.setAtributo("codDocumentacion");
        dtoCodigoDoc.setOperacion("=");
        dtoCodigoDoc.setValor(documentacionDTO.getCodDocumentacion());
        criterioCodigoDocumentacion.add(dtoCodigoDoc);

        List lDocumentacionCodigo = FachadaPersistencia.getInstance().buscar("Documentacion", criterioCodigoDocumentacion);
        if (!lDocumentacionCodigo.isEmpty()) {
            throw new DocumentacionException("El codigo de la documentación ya existe.");
        }

        List<DTOCriterio> criterioNombreDoc = new ArrayList<>();
        DTOCriterio dtoNombreDoc = new DTOCriterio();
        dtoNombreDoc.setAtributo("nombreDocumentacion");
        dtoNombreDoc.setOperacion("=");
        dtoNombreDoc.setValor(nombreDocumentacion);
        criterioNombreDoc.add(dtoNombreDoc);

        List lDocumentacionNombre = FachadaPersistencia.getInstance().buscar("Documentacion", criterioNombreDoc);
        if (!lDocumentacionNombre.isEmpty()) {
            throw new DocumentacionException("El nombre de la documentación ya existe.");
        }

        List<DTOCriterio> criterioDescripcionDoc = new ArrayList<>();
        DTOCriterio dtoDescripcionDoc = new DTOCriterio();
        dtoDescripcionDoc.setAtributo("descripcionDocumentacion");
        dtoDescripcionDoc.setOperacion("=");
        dtoDescripcionDoc.setValor(descripcionDocumentacion);
        criterioDescripcionDoc.add(dtoDescripcionDoc);

        List lDocumentacionDescripcion = FachadaPersistencia.getInstance().buscar("Documentacion", criterioDescripcionDoc);
        if (!lDocumentacionDescripcion.isEmpty()) {
            throw new DocumentacionException("La descripcion de la documentación ya existe.");
        }

    }

    private void validarDocumentacionM(ModificarDocumentacionDTOIn documentacionDTO) throws DocumentacionException {

        String nombreDocumentacion = documentacionDTO.getNombreDocumentacion();
        if (nombreDocumentacion == null || nombreDocumentacion.trim().isEmpty() || nombreDocumentacion.length() > 255) {
            throw new DocumentacionException("El nombre debe tener entre 1 y 255 caracteres.");
        }

        String descripcionDocumentacion = documentacionDTO.getDescripcionDocumentacion();
        if (descripcionDocumentacion == null || descripcionDocumentacion.trim().isEmpty() || descripcionDocumentacion.length() > 255) {
            throw new DocumentacionException("La descripcion debe tener entre 1 y 255 caracteres.");
        }

        List<DTOCriterio> criterioNombreDoc = new ArrayList<>();
        DTOCriterio dtoNombreDoc = new DTOCriterio();
        dtoNombreDoc.setAtributo("nombreDocumentacion");
        dtoNombreDoc.setOperacion("=");
        dtoNombreDoc.setValor(nombreDocumentacion);
        criterioNombreDoc.add(dtoNombreDoc);

        DTOCriterio dtoCodDoc = new DTOCriterio();
        dtoCodDoc.setAtributo("codDocumentacion");
        dtoCodDoc.setOperacion("<>");
        dtoCodDoc.setValor(documentacionDTO.getCodDocumentacion());
        criterioNombreDoc.add(dtoCodDoc);

        List lDocumentacionNombre = FachadaPersistencia.getInstance().buscar("Documentacion", criterioNombreDoc);
        if (!lDocumentacionNombre.isEmpty()) {
            throw new DocumentacionException("El nombre de la documentación ya existe.");
        }

        List<DTOCriterio> criterioDescripcionDoc = new ArrayList<>();
        DTOCriterio dtoDescripcionDoc = new DTOCriterio();
        dtoDescripcionDoc.setAtributo("descripcionDocumentacion");
        dtoDescripcionDoc.setOperacion("=");
        dtoDescripcionDoc.setValor(descripcionDocumentacion);
        criterioDescripcionDoc.add(dtoDescripcionDoc);

        DTOCriterio dtoDescDoc = new DTOCriterio();
        dtoDescDoc.setAtributo("codDocumentacion");
        dtoDescDoc.setOperacion("<>");
        dtoDescDoc.setValor(documentacionDTO.getCodDocumentacion());
        criterioDescripcionDoc.add(dtoDescDoc);

        List lDocumentacionDescripcion = FachadaPersistencia.getInstance().buscar("Documentacion", criterioDescripcionDoc);
        if (!lDocumentacionDescripcion.isEmpty()) {
            throw new DocumentacionException("La descripcion de la documentación ya existe.");
        }

    }
}
