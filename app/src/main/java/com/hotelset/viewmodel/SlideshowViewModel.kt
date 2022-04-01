package com.hotelset.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _mision = MutableLiveData<String>().apply {
        value = "La mision de nosotros es proveer a los turistas en Costa Rica, una via facil y rapida para enterarse sobre los hoteles existentes y noticias de esta área desde la palma de su mano."
    }
    private val _vision = MutableLiveData<String>().apply {
        value = "Deseamos ser una empresa lider en turismo en Costa Rica, reconocida por parte de los usuarios, empleados, clientes y todos los grupos de interés relacionados con la actividad de la compañía"
    }
    private val _valores = MutableLiveData<String>().apply {
        value = "Nuestros valores se basan en el Servicio, la Libertad, la Pasión y la Claridad en todas nuestras actividades."
    }



    val mision: LiveData<String> = _mision
    val vision: LiveData<String> = _vision
    val valores: LiveData<String> = _valores
}