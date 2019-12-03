import { Injector } from "@angular/core";
import { Response, RequestOptionsArgs, Headers } from "@angular/http";
import { Observable } from "rxjs/Observable";
import { RequestOptions } from "@angular/http";
import { Http } from "@angular/http";
import { HttpClient, HttpParams } from "@angular/common/http"

/**
* Clase que permite gestionar las peticiones a los servicios rest
**/
export class AbstractService  {

    private http: HttpClient;
    private headers = { 'Access-Control-Allow-Origin': '*' };
	
    constructor(injector: Injector) {
        
        injector.get(Http);
        this.http = injector.get(HttpClient);
        if (this.isNpmServe()) {
            //this.LogLevel = -1;
        }
    }

    protected isNpmServe():boolean{
        return location.port == "4200";
    }
    
    protected buildURL(module: string, path: string): string {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        if (!module.startsWith("/")) {
            module = "/" + module;
        }
        let url = this.getURL() + module + "/rest" + path;
        //this.logD("Resolviendo url:  Modulo: {0} - Ruta {1} -> {2}", module, path, url);
        return url;
    }

    private getURL() {
        if (this.isNpmServe()) {
            return "http://127.0.0.1:8085";
        }
        
        var url =  location.protocol + '//' + location.hostname;
        if(location.port != "85"){
            url +=  ":" + location.port;
        }
        return url;
    }

    public get<T>(module: string, path: string, params?: { [param: string]: string | string[]; }): Observable<T> {
        let url = this.buildURL(module, path);
        return this.http.get<T>(url, { headers: this.headers, params: params });
    }
    
    public post<T>(module: string, path: string, body: any, options?: RequestOptionsArgs): Observable<T> {
        let url = this.buildURL(module, path);
        return this.http.post<T>(url, body, { headers: this.headers });
    }	
	
    public put<T>(module: string, path: string, body: any, options?: RequestOptionsArgs): Observable<T> {
        let url = this.buildURL(module, path);
        return this.http.put<T>(url, body, { headers: this.headers });
    }

    public delete<T>(module: string, path: string, options?: RequestOptionsArgs): Observable<T> {
        let url = this.buildURL(module, path);
        return this.http.delete<T>(url, { headers: this.headers });
    }
}

