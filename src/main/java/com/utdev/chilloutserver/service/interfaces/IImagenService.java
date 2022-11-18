package com.utdev.chilloutserver.service.interfaces;

import com.utdev.chilloutserver.model.Imagen;
import com.utdev.chilloutserver.model.primaryKeys.PKImagen;

import java.util.List;

public interface IImagenService {

    // Save full Imagen
    Imagen saveImagen(Imagen imagen);

    // Get Image by codBarras
    List<Imagen> findImgByCodBarras(String codBarras);

    // Get Image by uuid
    List<Imagen> findImgByUUID(String uuid);

    // Delete Image by fullID
    void deleteById(PKImagen pkimg);
}
