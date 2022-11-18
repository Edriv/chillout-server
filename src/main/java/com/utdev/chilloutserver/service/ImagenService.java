package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Imagen;
import com.utdev.chilloutserver.model.primaryKeys.PKImagen;
import com.utdev.chilloutserver.repository.ImagenRepository;
import com.utdev.chilloutserver.service.interfaces.IImagenService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenService implements IImagenService {

    private final ImagenRepository repository;

    public ImagenService(ImagenRepository repository) {
        this.repository = repository;
    }

    //region region Zona de CREATE
    // Save full Imagen
    @Override
    public Imagen saveImagen(Imagen imagen){ return repository.save(imagen); }
    //endregion

    // Get Image by codBarras
    @Override
    public List<Imagen> findImgByCodBarras(String codBarras){
        Imagen img = new Imagen();
        PKImagen imgPK = new PKImagen();
        img.setImagenPK(imgPK);

        imgPK.setCod_barras(codBarras);
        Example<Imagen> imgExample = Example.of(img);
        return repository.findAll(imgExample);
    }

    // Get Image by uuid
    @Override
    public List<Imagen> findImgByUUID(String uuid){
        Imagen img = new Imagen();
        PKImagen imgPK = new PKImagen();
        img.setImagenPK(imgPK);

        imgPK.setUuid(uuid);
        Example<Imagen> imgExample = Example.of(img);
        return repository.findAll(imgExample);
    }

    // Delete Image by fullID
    @Override
    public void deleteById(PKImagen pkimg){
        repository.deleteById(pkimg);
    }

}
