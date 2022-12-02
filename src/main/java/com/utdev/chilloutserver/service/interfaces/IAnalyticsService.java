package com.utdev.chilloutserver.service.interfaces;


import com.utdev.chilloutserver.model.utils.ArticuloCantidad;

import java.util.List;

public interface IAnalyticsService {
    List<ArticuloCantidad> findTopTenArtSales();
}
