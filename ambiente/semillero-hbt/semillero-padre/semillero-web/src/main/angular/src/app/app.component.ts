import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private miObjeto : any;
  constructor(private router : Router) {
    
  }  

  ngOnInit(): void {
    this.router.navigate(['bienvenida']);    
    
  }
}
