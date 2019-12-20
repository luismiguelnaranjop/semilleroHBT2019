import { Injectable } from '@angular/core';
import { Injector } from "@angular/core";
import { Observable } from 'rxjs';
import 'rxjs/add/operator/toPromise';
import { AbstractService } from './template.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PersonaDTO } from '../dto/persona.dto';

/**
 * Servicio encargado de llamar a los servicios REST de
 * ejemplo
 */
@Injectable({
  providedIn: 'root'
})
export class GestionarPersonaService extends AbstractService {

  /**
   * Constructor
   */
  constructor(injector: Injector, private httpClient : HttpClient) {
    super(injector);
  }
  
  public consultarPersonas(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/semillero-servicios/rest/GestionarPersona/consultarPersonas');
  }

  public crearPersona(personaDTO : PersonaDTO): Observable<any> {
    // TODO poner URL correcta
    return this.httpClient.post('http://localhost:8085/semillero-servicios/rest/GestionarPersona/crearPersona',personaDTO);
  }
}