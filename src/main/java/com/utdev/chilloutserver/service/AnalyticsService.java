package com.utdev.chilloutserver.service;

import com.utdev.chilloutserver.model.Articulo;
import com.utdev.chilloutserver.model.utils.ArticuloCantidad;
import com.utdev.chilloutserver.repository.ArticuloRepository;
import com.utdev.chilloutserver.repository.VentaArticuloRepository;
import com.utdev.chilloutserver.service.interfaces.IAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService implements IAnalyticsService {

    @Autowired
    private final VentaArticuloRepository ventArtRepository;
    @Autowired
    private final ArticuloRepository articuloRepository;

    public AnalyticsService(VentaArticuloRepository ventArtRepository, ArticuloRepository articuloRepository) {
        this.ventArtRepository = ventArtRepository;
        this.articuloRepository = articuloRepository;
    }

    @Override
    public List<ArticuloCantidad> findTopTenArtSales(){
        List<Object[]> result = ventArtRepository.findTopTenArticles();
        List<ArticuloCantidad> list = new ArrayList<ArticuloCantidad>();
        if(result != null && !result.isEmpty()){
            for(Object[] obj: result){
                Articulo art = articuloRepository.findById((String)obj[0]).orElse(null);
                if(art != null){
                    list.add(new ArticuloCantidad(art.getNombre(),(Integer)obj[1]));
                }
            }
            return list;
        }
        return null;
    }

}
