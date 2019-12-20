import { Injectable } from '@angular/core';
import { Injector } from "@angular/core";
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from './template.service';
import { HttpClient } from '@angular/common/http';

/**
 * Servicio encargado de llamar a los servicios REST de
 * ejemplo
 */
@Injectable({
  providedIn: 'root'
})
export class GestionarComprasService extends AbstractService {

  /**
   * Constructor
   */
  constructor(injector: Injector, private httpClient : HttpClient) {
    super(injector);
  }
  
  /**
   * Este metodo permite obtener todos los comics registradas
   */
  public consultarComics(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarComic/consultarComics');
  }

  /**
   * Este metodo permite obtener todas las personas registradas
   */
  public consultarPersonas(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarPersonas');
  }

  
}